package com.houli.system.config;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.houli.common.config.Constant;
import com.houli.system.shiro.UserRealm;
import com.houli.system.shiro.redis.RedisCacheManager;
import com.houli.system.shiro.redis.RedisSerialize;
import com.houli.system.shiro.redis.RedisSessionDAO;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import net.sf.ehcache.CacheManager;


@Configuration
public class ShiroConfig {
	
	@Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;

	@Value("${spring.cache.type}")
    private String cacheType ;
	
	@Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

	
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login","anon");
        //eureka客户端测试地址排除
        filterChainDefinitionMap.put("/eureka_test/**","anon");
        //Spring Boot actuator服务监控与管理地址排除
        filterChainDefinitionMap.put("/actuator/**","anon");
        //Hystrix熔断器仪表盘地址排除开始
        filterChainDefinitionMap.put("/hystrix/**","anon");
        filterChainDefinitionMap.put("/hystrix.stream/**","anon");
        filterChainDefinitionMap.put("/proxy.stream/**","anon");
        //Hystrix熔断器仪表盘地址排除结束
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        filterChainDefinitionMap.put("/files/**", "anon");
        filterChainDefinitionMap.put("/app/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/blog", "anon");
        filterChainDefinitionMap.put("/blog/open/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 添加ShiroDialect 为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public SecurityManager securityManager(RedisTemplate<String, Object> redisTemplate) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(userRealm());
        
        // 自定义缓存实现 使用redis或者ehcache，注意这里如果使用ehcache,可能会导致使用ehcache报错，报找不到cache name的错误.
        // 注意这里不要设置setSessionManager，如果必须要设置就需要在pom中取消热加载，不然就会出现双类加载的错误，不设置setSessionManager后表示使用spring自带session管理，否则使用shiro内部的session管理
        // 另外我们可以不设置 setCacheManager，表示shiro查询出的权限不缓存，我们可以在请求查询权限字典接口中（UserRealm类中MenuService接口中的listPerms方法）使用缓存注解，这样变相的使用了缓存。
        //@Cacheable(value = "listPerms",key = "#userId")
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            securityManager.setCacheManager(rediscacheManager(redisTemplate));
        } else {
            securityManager.setCacheManager(ehCacheManager());
        }
        //注释掉该代码表示不使用shiro内部的session管理，而使用spring的session管理，如果需要使用如下代码会出现双类加载问题，需要注释掉热加载的配置
//        securityManager.setSessionManager(sessionManager(redisTemplate)); 
        
        return securityManager;
    }

    
    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager(RedisTemplate<String, Object> redisTemplate) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //sessionManager.setGlobalSessionTimeout(sessionTimeout * 1000);
        sessionManager.setSessionDAO(sessionDAO(redisTemplate));
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        listeners.add(sessionListener());
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }
    
    @Bean
    public BDSessionListener sessionListener() {
        return new BDSessionListener();
    }
    
    @Bean
    public FormAuthenticationFilter authenticationFilter(){
    	FormAuthenticationFilter authenticationFilter = new FormAuthenticationFilter();
        return authenticationFilter;
    }
    
    @Bean
    public SessionDAO sessionDAO(RedisTemplate<String, Object> redisTemplate) {
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
        	return redisSessionDAO(redisTemplate);
        } else {
            return new MemorySessionDAO();
        }
    }
    
    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(RedisTemplate<String, Object> redisTemplate) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO(redisTemplate, 180000);
        return redisSessionDAO;
    }
    

    /**
     * retemplate相关配置
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

    	// 1.创建 redisTemplate 模版
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 2.关联 redisConnectionFactory
        template.setConnectionFactory(factory);
        // 7.设置 value 的转化格式和 key 的转化格式，使用自定义的序列化，不然会出现序列化key出错
        template.setValueSerializer(new RedisSerialize<>());
        template.setKeySerializer(new RedisSerialize<>());
        template.afterPropertiesSet();

        return template;
    }
    
    
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }
    
    /**
     * 开启shiro aop注解支持，否则@RequiresPermissions注解将无效
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    
    
    
    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisCacheManager<String, Object> rediscacheManager(RedisTemplate<String, Object> redisTemplate) {
        RedisCacheManager<String, Object> redisCacheManager = new RedisCacheManager<String, Object>();
        redisCacheManager.setRedisTemplate(redisTemplate);
        return redisCacheManager;
    }
    
    /**
     * cacheManager 缓存 EhCache实现
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManager(cacheManager());
        return em;
    }
    
    @Bean("cacheManager2")
    public CacheManager cacheManager(){
        return CacheManager.create();
    }


}

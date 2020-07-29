package com.houli.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description:    TODO(通过该工具类操作spring容器及其中的Bean实例)   
 * @author: jxl     
 * @date:   2018年11月30日 下午3:30:51   
 * @version V1.0
 */
@Component
public class ApplicationContextRegister implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(ApplicationContextRegister.class);
    private static ApplicationContext APPLICATION_CONTEXT;
    /**
     * 设置spring上下文
     * @param applicationContext spring上下文
     * @throws BeansException
     * */
    @Override 
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("ApplicationContext registed-->{}", applicationContext);
        APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * 获取容器
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * 获取容器对象
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> type) {
        return APPLICATION_CONTEXT.getBean(type);
    }
    
    /**
     * 移除Bean（动态添加删除bean）
     * @param beanName bean名称
     */
    public static <T> void removeBean(String beanName) {
    	DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)APPLICATION_CONTEXT.getAutowireCapableBeanFactory();
    	defaultListableBeanFactory.removeBeanDefinition(beanName);
    }
    
    /**
     * 注册Bean（动态添加删除bean）
     * @param bean 需要注册的类
     * @param beanName 注册名称
     */
    public static <T> void registerBean(Class<T> bean, String beanName) {
    	// 将applicationContext转换为ConfigurableApplicationContext
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) APPLICATION_CONTEXT;
    	// 获取bean工厂并转换为DefaultListableBeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
        // 通过BeanDefinitionBuilder创建bean定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(bean);
        // 注册bean
        defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getRawBeanDefinition());
    }

}
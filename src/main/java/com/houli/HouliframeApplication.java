package com.houli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableCircuitBreaker
@EnableHystrixDashboard
//@EnableDiscoveryClient
@SpringBootApplication
//加了这个注解配置文件中设置的session超时时间将无效；需要设置maxInactiveIntervalInSeconds，表示session超时时间，单位为秒，否则将使用默认30分钟。
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1800) 
@EnableCaching//开启缓存，需要显示的指定
public class HouliframeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouliframeApplication.class, args);
	} 
	

}


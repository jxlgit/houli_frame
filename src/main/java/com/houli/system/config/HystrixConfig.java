package com.houli.system.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

/**
 * 
 * @Description:    TODO(Hystrix熔断器仪表盘配置)   
 *  http://192.16.8.100:8080/hystrix.stream
 *  
 * @author: jxl     
 * @date:   2019年1月3日 下午5:37:09   
 * @version V1.0
 */
@Configuration
public class HystrixConfig {

    @Bean
    public HystrixMetricsStreamServlet hystrixMetricsStreamServlet(){
        return new HystrixMetricsStreamServlet();
    }

    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> registration(HystrixMetricsStreamServlet servlet){
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<HystrixMetricsStreamServlet>();
        registrationBean.setServlet(servlet);
        //是否启用该registrationBean
        registrationBean.setEnabled(true);
        registrationBean.addUrlMappings("/hystrix.stream");
        return registrationBean;
    }
}

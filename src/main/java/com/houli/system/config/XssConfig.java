package com.houli.system.config;

import com.google.common.collect.Maps;
import com.houli.system.filter.XssFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class XssConfig{

	/**
	 * xss过滤拦截器
	 */
	@Bean
	public FilterRegistrationBean<XssFilter> xssFilterRegistrationBean() {
		FilterRegistrationBean<XssFilter> filterRegistrationBean = new FilterRegistrationBean<XssFilter>();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = Maps.newHashMap();
		//排除的请求
		initParameters.put("excludes", "/favicon.ico,/img/*,/image/*,/images/*,/javascript/*,/js/*,/css/*,/style/*");
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}
}

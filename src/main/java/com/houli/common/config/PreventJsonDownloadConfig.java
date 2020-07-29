package com.houli.common.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @Description:    TODO(解决IE返回json数据出现下载对话框的问题)   
 * @author: jxl     
 * @date:   2019年2月20日 下午2:37:09   
 * @version V1.0
 */
@Configuration
public class PreventJsonDownloadConfig implements WebMvcConfigurer {

	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		MediaType media = new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8"));
		supportedMediaTypes.add(media);
		jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
		return jsonConverter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customJackson2HttpMessageConverter());
	}

}

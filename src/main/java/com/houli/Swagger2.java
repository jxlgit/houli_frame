package com.houli;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @Description: TODO(swagger2的配置文件，在项目的启动类的同级文件建立)
 * @author: jxl
 * @date: 2018年12月25日 上午9:05:25
 * @version V1.0
 * 
 * 请求地址：
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	// swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				// 为当前包路径
				.apis(RequestHandlerSelectors.basePackage("com.houli.system.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	// 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 页面标题
				.title("接口文档")
				// 创建人
				.contact(new Contact("DMIAES", "http://www.houlidata.com", ""))
				// 版本号
				.version("1.0")
				// 描述
				.description("API 描述")
				.build();
	}

}
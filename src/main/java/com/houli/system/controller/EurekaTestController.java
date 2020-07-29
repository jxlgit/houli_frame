package com.houli.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


/**
 * 
 * @Description:    TODO(Eureka请求远程接口测试)   
 * @author: jxl     
 * @date:   2019年1月3日 下午1:40:37   
 * @version V1.0
 */
@Controller
@RequestMapping("/eureka_test")
public class EurekaTestController {

	@Autowired
    RestTemplate restTemplate;
	
	@ResponseBody
	@GetMapping("/get")
	String test1(Model model) {
		// 注意 服务名称不能用下划线
		String str = restTemplate.getForObject("http://houli-client-test/test/get/1",String.class);
		return str;
	}
	
	@ResponseBody
	@GetMapping("/post")
    //服务断路器模式设置
	@HystrixCommand(
			fallbackMethod = "hystrixFallback",
			commandProperties = {
			//设置熔断时间
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
	})
	String test2(Model model) {
		//	exchange既可以执行POST方法，还可以执行GET
		String url = "http://houli-client-test/test/post";
		HttpHeaders headers = new HttpHeaders();
		//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		//  支持中文
		params.add("username", "用户名");
		params.add("password", "123456");
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		//  执行HTTP请求
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		//  输出结果
		String result = response.getBody();
		return result;
	}
	
	/**
	 *  熔断器降级回调
	 * @param model
	 * @return
	 */
	public String hystrixFallback(Model model){
        return "error";
    }
	
}

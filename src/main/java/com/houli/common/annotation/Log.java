package com.houli.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @Description:    TODO(日志记录自定义注解)   
 * @author: jxl     
 * @date:   2018年11月30日 下午3:26:25   
 * @version V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
	String value() default "";
}

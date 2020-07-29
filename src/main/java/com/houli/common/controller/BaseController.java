package com.houli.common.controller;

import org.springframework.stereotype.Controller;

import com.houli.common.utils.ShiroUtils;
import com.houli.system.domain.UserDO;

/**
 * 
 * @Description: 基础控制层，用于储存公共的对象或者属性
 * @author: jxl     
 * @date:   2018年11月9日 下午4:08:25   
 * @version V1.0
 */
@Controller
public class BaseController {
	public UserDO getUser() {
		UserDO userDO = ShiroUtils.getUser();
		return userDO;
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}
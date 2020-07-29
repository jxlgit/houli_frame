package com.houli;

import com.houli.common.utils.MD5Utils;

public class UserNamePwdMod {

	public static void main(String[] args) {
		
		// 第一个参数为用户名，第二参数为密码
		System.out.println("加密后密码为：" + MD5Utils.encrypt("admin_", "111111")); 
		
	}

}

package com.houli.common.config;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.houli.common.utils.ShiroUtils;
import com.houli.system.domain.UserDO;

/**
 * 
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: jxl     
 * @date:   2018年12月26日 上午9:25:57   
 * @version V1.0
 */
@Service
public class MyHandshake implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
//        	HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
//            // 从session中获取到当前登录的用户信息. 作为socket的账号信息. session的的WEBSOCKET_USERNAME信息,在用户打开页面的时候设置.
//          String userName = (String) servletRequest.getSession().getAttribute("WEBSOCKET_USERNAME");
        	UserDO userDO = ShiroUtils.getUser();
        	if (userDO != null) {
        		attributes.put("WEBSOCKET_USERNAME", userDO.getUsername());
			}
            
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    }
}

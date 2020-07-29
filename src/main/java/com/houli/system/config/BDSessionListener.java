package com.houli.system.config;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;

import com.houli.common.config.MyWebSocketHandler;

/**
 * 
 * @Description:    TODO(Session监听)   
 * @author: jxl     
 * @date:   2019年1月9日 上午9:24:31   
 * @version V1.0
 */
public class BDSessionListener implements SessionListener {
	private Logger logger = LoggerFactory.getLogger(BDSessionListener.class);
	
	@Autowired
    private MyWebSocketHandler webSocketHandler;
	
	private final AtomicInteger sessionCount = new AtomicInteger(0);

	@Override
	public void onStart(Session session) {
		sessionCount.incrementAndGet();
		logger.info("会话创建：" + session.getId());
	}

	@Override
	public void onStop(Session session) {
		sessionCount.decrementAndGet();
		webSocketHandler.sendMessageToUsers(new TextMessage("{\"msg\":\"登录超时，请重新登录!\",\"code\":504}"));
		logger.info("会话停止：" + session.getId());  
	}

	@Override
	public void onExpiration(Session session) {
		sessionCount.decrementAndGet();
		webSocketHandler.sendMessageToUsers(new TextMessage("{\"msg\":\"登录超时，请重新登录!\",\"code\":504}"));
		logger.info("会话过期：" + session.getId());

	}

	public int getSessionCount() {
		return sessionCount.get();
	}

}

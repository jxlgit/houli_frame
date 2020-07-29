package com.houli.common.config;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: jxl     
 * @date:   2018年12月26日 上午9:25:45   
 * @version V1.0
 */
@Service
public class MyWebSocketHandler implements WebSocketHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 为了保存在线用户信息，在方法中新建一个list存储一下【实际项目依据复杂度，可以存储到数据库或者缓存】
     */
    private final static List<WebSocketSession> SESSIONS = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	logger.info("WebSocket链接成功......");
        SESSIONS.add(session);
        String userName = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        if (userName != null) {
        	// 统计一下当前登录系统的用户有多少个并发送到页面
            JSONObject obj = new JSONObject();
            obj.put("count", SESSIONS.size());
            users(obj);
            session.sendMessage(new TextMessage(obj.toJSONString()));
        }
    }

    /**
     * 	用于处理页面发送过来的消息，如：
	 *	var object = {
     *       msg: value,
     *       type: 1
     *   };
     *   //将object转成json字符串发送给服务端
     *   var json = JSON.stringify(object);
     *   websocket.send(json);
     *           
     *  {"to": "张三","msg": "你好！","type": 2};
     *  to:发送给指定用户的标志，可以是用户ID或者用户名；
     *  msg：要发送的信息；
     *  type：发送类型，1表示发送给所有人，2表示发送给“to”指定的用户；
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    	logger.info("WebSocket处理要发送的消息");
        JSONObject msg = JSON.parseObject(message.getPayload().toString());
        JSONObject obj = new JSONObject();
        if (msg.getInteger("type") == 1) {
            //给所有人
            obj.put("msg", msg.getString("msg"));
            sendMessageToUsers(new TextMessage(obj.toJSONString()));
        } else {
            //给个人
            String to = msg.getString("to");
            obj.put("msg", msg.getString("msg"));
            sendMessageToUser(to, new TextMessage(obj.toJSONString()));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.info("WebSocket链接出错，关闭链接......");
        SESSIONS.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    	logger.info("WebSocket链接关闭......" + closeStatus.toString());
        SESSIONS.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : SESSIONS) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : SESSIONS) {
            if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 将系统中的用户传送到前端
     *
     * @param obj
     */
    private void users(JSONObject obj) {
        List<String> userNames = new ArrayList<>();
        for (WebSocketSession webSocketSession : SESSIONS) {
            userNames.add((String) webSocketSession.getAttributes().get("WEBSOCKET_USERNAME"));
        }
        obj.put("users", userNames);
    }
}

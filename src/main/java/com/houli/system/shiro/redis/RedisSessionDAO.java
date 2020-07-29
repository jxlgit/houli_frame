package com.houli.system.shiro.redis;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @version V1.0
 */
public class RedisSessionDAO extends CachingSessionDAO {

    private RedisTemplate<String, Object> redisTemplate;
    private int defaultExpireTime = 3600;


    public RedisSessionDAO(RedisTemplate<String, Object> redisTemplate,int defaultExpireTime) {
        this.redisTemplate = redisTemplate;
        this.defaultExpireTime = defaultExpireTime;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(sessionId.toString(), session);
        redisTemplate.expire(sessionId.toString(), this.defaultExpireTime, TimeUnit.SECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        //此方法不会执行，不用管
        return null;
    }

    @Override
    protected void doUpdate(Session session) {
        //该方法交给父类去执行
    }


    @Override
    protected void doDelete(Session session) {
        Serializable sessionId = session.getId();
        redisTemplate.delete(sessionId.toString());
    }


}
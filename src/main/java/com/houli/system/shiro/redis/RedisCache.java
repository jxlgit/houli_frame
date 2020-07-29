package com.houli.system.shiro.redis;

/**
 * @version V1.0
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCache<K,V> implements Cache<K,V> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The wrapped Jedis instance.
     */
    private RedisTemplate<K, V> redisTemplate;

    /**
     * The Redis key prefix for the sessions
     */
    private String keyPrefix = "shiro_redis_session:";

    /**
     * Returns the Redis session keys
     * prefix.
     * @return The prefix
     */
    public String getKeyPrefix() {
        return keyPrefix;
    }

    /**
     * Sets the Redis sessions key
     * prefix.
     * @param keyPrefix The prefix
     */
    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    /**
     * 通过一个JedisManager实例构造RedisCache
     */
    public RedisCache(RedisTemplate<K, V> redisTemplate){
        if (redisTemplate == null) {
            throw new IllegalArgumentException("redisTemplate argument cannot be null.");
        }
        this.redisTemplate = redisTemplate;
    }

    /**
     * Constructs a cache instance with the specified
     * Redis manager and using a custom key prefix.
     * @param cache The cache manager instance
     * @param prefix The Redis key prefix
     */
    public RedisCache(RedisTemplate<K,V> redisTemplate,  String prefix){
        this( redisTemplate );

        // set the prefix
        this.keyPrefix = prefix;
    }


    @Override
    public V get(K key) throws CacheException {
        logger.debug("根据key从Redis中获取对象 key [" + key + "]");
        try {
            if (key == null) {
                return null;
            }else{
                V rawValue = (V) redisTemplate.opsForValue().get(key);
                return rawValue;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }

    }

    @Override
    public V put(K key, V value) throws CacheException {
        logger.debug("根据key从存储 key [" + key + "]");
        try {
            redisTemplate.opsForValue().set( key, value);
            return value;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        logger.debug("从redis中删除 key [" + key + "]");
        try {
        	V previous = get(key);
            redisTemplate.delete( key);
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public void clear() throws CacheException {
        logger.debug("从redis中删除所有元素");
        try {
            redisTemplate.delete(redisTemplate.keys((K) (this.keyPrefix + "*")));
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public int size() {
        try {
        	@SuppressWarnings("unchecked")
            Long longSize = new Long(redisTemplate.opsForSet().size((K) (this.keyPrefix + "*")));
            return longSize.intValue();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        try {
        	
            Set<K> keys = (Set<K>) redisTemplate.keys((K) (this.keyPrefix + "*"));
            if (CollectionUtils.isEmpty(keys)) {
                return Collections.emptySet();
            }else{
                Set<K> newKeys = new HashSet<K>();
                for(K key:keys){
                    newKeys.add(key);
                }
                return newKeys;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Collection<V> values() {
        try {
        	@SuppressWarnings("unchecked")
			Set<K> keys = (Set<K>) redisTemplate.keys((K) (this.keyPrefix + "*"));
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (K key : keys) {
                    V value = get((K)key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

}

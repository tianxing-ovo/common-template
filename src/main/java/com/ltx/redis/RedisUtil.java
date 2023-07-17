package com.ltx.redis;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    ValueOperations<String, String> stringOperation;
    HashOperations<String, Object, Object> hashOperation;
    ListOperations<String, Object> listOperation;
    SetOperations<String, Object> setOperation;
    ZSetOperations<String, Object> zSetOperation;


    @PostConstruct
    public void init() {
        stringOperation = stringRedisTemplate.opsForValue();
        hashOperation = redisTemplate.opsForHash();
        listOperation = redisTemplate.opsForList();
        setOperation = redisTemplate.opsForSet();
        zSetOperation = redisTemplate.opsForZSet();
    }

    /**
     * String-set
     */
    public void set(String key, String value, long timeout, TimeUnit unit) {
        stringOperation.set(key, value, timeout, unit);
    }

    /**
     * String-get
     */
    public String get(String key) {
        return stringOperation.get(key);
    }

    /**
     * Hash-put
     */
    public void hSet(String key, String field, String value) {
        hashOperation.put(key, field, value);
    }

    /**
     * Hash-get
     */
    public String hGet(String key, String field) {
        return (String) hashOperation.get(key, field);
    }

    /**
     * List-leftPush
     */
    public void lPush(String key, String value) {
        listOperation.leftPush(key, value);
    }

    /**
     * List-leftPop
     */
    public String lPop(String key) {
        return (String) listOperation.leftPop(key);
    }


    /**
     * Set-add
     */
    public void sAdd(String key, String value) {
        setOperation.add(key, value);
    }

    /**
     * ZSet-add
     */
    public void zAdd(String key, String value, double score) {
        zSetOperation.add(key, value, score);
    }


    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }
}

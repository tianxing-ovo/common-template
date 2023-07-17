package com.ltx.redis;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * 缓存配置
 */
@EnableConfigurationProperties(CacheProperties.class)//使绑定属性配置文件生效
@Configuration
@EnableCaching  //开启缓存
public class CacheConfig {

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
        //获取默认配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        //指定key的序列化方式为String,链式调用
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        //指定value的序列化方式为json,链式调用,GenericJackson2JsonRedisSerializer是通用的json序列化器
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();

        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive()); //缓存过期时间
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.computePrefixWith((cacheName) -> redisProperties.getKeyPrefix() + cacheName + ":"); //键前缀
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues(); //禁用缓存空值
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix(); //禁用键前缀
        }
        return config;
    }
}


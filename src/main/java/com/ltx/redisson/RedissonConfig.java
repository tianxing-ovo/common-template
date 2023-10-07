package com.ltx.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * redisson(分布式锁)配置
 */
@Configuration
public class RedissonConfig {


    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson() {
        // 创建配置
        Config config = new Config();
        // 单节点模式
        config.useSingleServer().setAddress("redis://localhost:6379");
        // 根据Config创建出RedissonClient实例
        return Redisson.create(config);
    }
}

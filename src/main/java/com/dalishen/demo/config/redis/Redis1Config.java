package com.dalishen.demo.config.redis;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

/**
 * Redis实例1 配置
 */
@Configuration
@EnableCaching
public class Redis1Config extends RedisConfig {

    @Value("${spring.redis.database}")
    private String dbIndex;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.passwd}")
    private String passwd;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean(name = "lettucePoolConfig")
    public GenericObjectPoolConfig genericObjectPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(8);
        config.setMinIdle(0);
        config.setMaxWaitMillis(1000);
        return config;
    }

    @Bean(name = "lettuceRedisConnectionFactory1")
    LettuceConnectionFactory lettuceConnectionFactory(@Qualifier("lettucePoolConfig") GenericObjectPoolConfig genericObjectPoolConfig) {
        // 单机版配置
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(passwd));
        if(Strings.isNotEmpty(dbIndex)) {
            redisStandaloneConfiguration.setDatabase(Integer.valueOf(dbIndex));
        }
        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(timeout))
                .poolConfig(genericObjectPoolConfig)
                .build();

        return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
    }

    //操作redis客户端
    @Bean(name = "redisTemplate1")
    public RedisTemplate redisTemplate(@Qualifier("lettuceRedisConnectionFactory1") LettuceConnectionFactory factory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(factory);
        //设置序列化工具
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }


    @Bean(name = "stringRedisTemplate1")
    public StringRedisTemplate stringRedisTemplate(@Qualifier("lettuceRedisConnectionFactory1") LettuceConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();
        return template;
    }


}

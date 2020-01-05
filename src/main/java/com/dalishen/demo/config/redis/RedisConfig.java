package com.dalishen.demo.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;

/**
 * Redis多实例配置，父类
 * Redis 做cache缓存用时，配置其 key的生成方式
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${spring.redis.lettuce.pool.max-active}")
    private int redisPoolMaxActive;

    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int redisPoolMaxIdle;

    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int redisPoolMinIdle;

    @Value("${spring.redis.lettuce.pool.max-wait}")
    private int redisPoolMaxWait;

    /**
     * redis key生成策略
     * 当把Redis当做缓存用时，设置Key的生成方式：类名+方法名+各个参数
     * 注意: 该方法只是声明了key的生成策略,还未被使用,需在@Cacheable注解中指定keyGenerator
     * 如: @Cacheable(value = "key", keyGenerator = "keyGenerator")
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getSimpleName());
                sb.append(method.getName());
            Arrays.stream(params).forEach(obj -> sb.append(obj.toString()));
                return sb.toString();
        };
    }

    // 序列化 方式
    public void setSerializer(RedisTemplate template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
    }
}

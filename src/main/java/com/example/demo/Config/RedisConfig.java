package com.example.demo.Config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    //缓存管理器
    @Bean
    @SuppressWarnings("rawtypes")//抑制单类型警告
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory){
        RedisCacheManager cacheManager = RedisCacheManager.create(connectionFactory);
        //设置缓存过期时间，springboot2把RedisCacheManager的构造函数取消了，设置缓存过期时间也取消了
        //cacheManager.setDefaultExpiration(10000);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate template = new StringRedisTemplate(factory);
        setSerializser(template);//设置序列化工具
        template.afterPropertiesSet();
        return template;
    }

    private void setSerializser(StringRedisTemplate template){
        @SuppressWarnings({"rawtypes", "unchecked"})//抑制多类型警告
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }

}

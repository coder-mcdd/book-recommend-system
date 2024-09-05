package jzxy.mcdd.backend.config.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisConfiguration
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/9/5 19:27
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //设置 String 类型的 key 设置序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置 Hash 类型的 key 设置序列化器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //设置 redis 链接 Lettuce 工厂
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }
}

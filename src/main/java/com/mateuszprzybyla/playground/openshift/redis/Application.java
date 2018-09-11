package com.mateuszprzybyla.playground.openshift.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class Application {

    @Bean
    public RedisConnectionFactory redisConnectionFactory(@Autowired RedisProperties redisProperties) {
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(
                redisProperties.getHost(), redisProperties.getPort());
        standaloneConfig.setPassword(RedisPassword.of(redisProperties.getPassword()));
        return new JedisConnectionFactory(standaloneConfig);
    }

    @Bean
    public RedisTemplate<byte[], byte[]> redisTemplate(@Autowired RedisProperties redisProperties) {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory(redisProperties));
        return redisTemplate;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        PersonCacheDao personCacheDao = context.getBean(PersonCacheDao.class);
        personCacheDao.save(new Person("Mateusz", 26, "Poland"));

        System.out.println(personCacheDao.findById("Mateusz"));
    }
}

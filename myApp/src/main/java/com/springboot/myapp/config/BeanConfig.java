package com.springboot.myapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zengJian on 2018/4/5<br>
 * <br>
 */
@Configuration
public class BeanConfig {

    @Value("${spring.redis.host}")
    private String host;

//    @Value("${spring.redis.password}")
//    private String password;

    @Value("${spring.redis.timeout}")
    private Integer timeout;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.pool.min-idle}")
    private Integer minIdle;

    @Value("${spring.redis.pool.max-idle}")
    private Integer maxIdle;

    @Value("${spring.redis.pool.max-active}")
    private Integer maxTotal;

    @Value("${spring.redis.pool.max-wait}")
    private Long maxWaitMillis;

    /*
     * JedisPool
     */
    @Bean //此处返回的是一个Spring的配置Bean，与xml的<bean>等价
    public JedisPool getJedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setTestOnCreate(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout/*, password*/);
        return jedisPool;
    }
}

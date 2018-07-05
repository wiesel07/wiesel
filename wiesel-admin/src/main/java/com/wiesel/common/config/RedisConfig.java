//package com.wiesel.common.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import lombok.extern.java.Log;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// *
// * @ClassName 类名：RedisConfig
// * @Description 功能说明：
// *              <p>
// *              TODO
// *              </p>
// ************************************************************************
// * @date 创建日期：2018年7月4日
// * @author 创建人：wuj
// * @version 版本号：V1.0
// *          <p>
// ***************************          修订记录*************************************
// * 
// *          2018年7月4日 wuj 创建该类功能。
// *
// ***********************************************************************
// *          </p>
// */
//@Log
//@Configuration
//@EnableCaching
//public class RedisConfig extends CachingConfigurerSupport {
//
//	
//
//	@Value("${spring.redis.host}")
//	private String host = "127.0.0.1";
//
//	@Value("${spring.redis.port}")
//	private int port = 6379;
//
//	// timeout for jedis try to connect to redis server, not expire time! In
//	// milliseconds
//	@Value("${spring.redis.timeout}")
//	private int timeout = 0;
//
//	@Value("${spring.redis.pool.max-idle}")
//	private int maxIdle = 8;
//
//	@Value("${spring.redis.pool.max-wait}")
//	private long maxWaitMillis = -1;
//	
////	@Bean
////	public JedisPool redisPoolFactory() {
////		log.info("JedisPool注入成功！！！"+timeout);
////		log.info("redis地址：" + host + ":" + port);
////		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
////		jedisPoolConfig.setMaxIdle(maxIdle);
////		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
////		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
////		return jedisPool;
////	}
//}

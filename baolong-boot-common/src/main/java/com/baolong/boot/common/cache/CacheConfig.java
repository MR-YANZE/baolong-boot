package com.baolong.boot.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 缓存配置类
 *
 * @author Baolong 2024年06月13 21:20
 * @version 1.0
 * @since 1.8
 */
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
@Configuration
public class CacheConfig {
	private final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

	@Bean("redisCacheConfiguration")
	RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
		config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
		//从所以配置中取出redis的配置
		CacheProperties.Redis redisProperties = cacheProperties.getRedis();
		//将配置文件中所有的配置都生效（之间从源码里面拷 RedisCacheConfiguration）
		if (redisProperties.getTimeToLive() != null) {
			config = config.entryTtl(redisProperties.getTimeToLive());
		}
		if (redisProperties.getKeyPrefix() != null) {
			config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
		}
		if (!redisProperties.isCacheNullValues()) {
			config = config.disableCachingNullValues();
		}
		if (!redisProperties.isUseKeyPrefix()) {
			config = config.disableKeyPrefix();
		}
		return config;
	}

	/**
	 * 该方法执行，说明上面所有bean都已经初始化了
	 */
	@Bean("cacheConfigInitSuccess")
	@DependsOn({"redisCacheConfiguration"})
	public void cacheConfigInitSuccess() {
		logger.info("Cache配置: redisCacheConfiguration [加载完成]");
	}
}

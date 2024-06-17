package com.baolong.boot.common.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Redisson 配置类
 *
 * @author Baolong 2024/6/6 21:29
 * @version 1.0
 * @since 1.8
 */
@Configuration
@ConditionalOnBean(RedissonProperties.class)
@ConditionalOnProperty(prefix = "redisson", name = "enable", havingValue = "true", matchIfMissing = false)
public class RedissonConfig {
	private final Logger logger = LoggerFactory.getLogger(RedissonConfig.class);
	private final RedissonProperties redissonProperties;

	public RedissonConfig(RedissonProperties redissonProperties) {
		this.redissonProperties = redissonProperties;
	}

	@Bean(name = "redissonClient")
	public RedissonClient redissonClient() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://" + redissonProperties.getHost() + ":" + redissonProperties.getPort());
		//等待节点回复命令的时间。该时间从命令发送成功时开始计时。
		config.useSingleServer().setTimeout(redissonProperties.getTimeout());
		config.useSingleServer().setDatabase(redissonProperties.getDatabase());
		if (redissonProperties.getPassword() != null && !redissonProperties.getPassword().isEmpty()) {
			config.useSingleServer().setPassword(redissonProperties.getPassword());
		}
		RedissonClient redissonClient = Redisson.create(config);
		return redissonClient;
	}

	/**
	 * 该方法执行，说明上面所有bean都已经初始化了
	 */
	@Bean("redissonConfigInitSuccess")
	@DependsOn({"redissonClient"})
	public void redissonConfigInitSuccess() {
		logger.info("Redisson配置: redissonClient [加载完成]");
	}
}

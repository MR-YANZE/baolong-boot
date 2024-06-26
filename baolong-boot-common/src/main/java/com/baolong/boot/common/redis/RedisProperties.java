package com.baolong.boot.common.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Redis 属性类
 *
 * @author Baolong 2024/6/6 21:00
 * @version 1.0
 * @since 1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
	private String host;
	private String password;
	private Integer port;
	private Integer database;
	private Integer timeout;
}

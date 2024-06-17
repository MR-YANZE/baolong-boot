package com.baolong.boot.common.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Redisson 属性类
 *
 * @author Baolong 2024/6/6 21:28
 * @version 1.0
 * @since 1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {
	private String enable;
	private String host;
	private String password;
	private String port;
	private Integer database;
	private Integer timeout;
}

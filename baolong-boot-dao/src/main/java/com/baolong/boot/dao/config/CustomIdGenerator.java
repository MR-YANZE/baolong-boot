package com.baolong.boot.dao.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义主键生成器
 *
 * @author Baolong 2024年06月13 21:54
 * @version 1.0
 * @since 1.8
 */
public class CustomIdGenerator implements IdentifierGenerator {
	private static final AtomicLong counter = new AtomicLong(0);

	/**
	 * 生成Id
	 *
	 * @param entity 实体
	 * @return id
	 */
	@Override
	public Number nextId(Object entity) {
		// 这里以时间戳 + 随机数为例，你可以根据需求调整生成策略
		long timestamp = System.currentTimeMillis();
		long randomPart = counter.getAndIncrement();
		// 这里简单地将时间戳和计数器值组合起来
		return timestamp << 32 | randomPart;
	}
}

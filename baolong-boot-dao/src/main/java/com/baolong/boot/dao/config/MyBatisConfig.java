package com.baolong.boot.dao.config;

import com.baolong.boot.dao.config.interceptor.SqlInterceptor;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * MyBatis配置类
 *
 * @author Baolong 2024年06月13 21:55
 * @version 1.0
 * @since 1.8
 */
@Configuration
public class MyBatisConfig {
	private final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

	@Bean("mybatisConfigurationCustomizer")
	@ConditionalOnProperty(prefix = "baolong.mybatis", name = "enable-custom-log", havingValue = "true")
	public ConfigurationCustomizer mybatisConfigurationCustomizer() {
		return configuration -> {
			configuration.addInterceptor(new SqlInterceptor());
		};
	}

	/**
	 * 注入自定义 ID 生成器
	 *
	 * @return IdentifierGenerator
	 */
	@Bean("plusPropertiesCustomizer")
	@ConditionalOnProperty(prefix = "baolong.mybatis-plus", name = "enable-custom-id", havingValue = "true")
	public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
		return plusProperties -> plusProperties.getGlobalConfig().setIdentifierGenerator(new CustomIdGenerator());
	}

	/**
	 * 注入分页插件
	 *
	 * @return MybatisPlusInterceptor
	 */
	@Bean("paginationInterceptor")
	@ConditionalOnProperty(prefix = "baolong.mybatis-plus", name = "enable-pagination", havingValue = "true")
	public MybatisPlusInterceptor paginationInterceptor() {
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
		return mybatisPlusInterceptor;
	}

	/**
	 * 该方法执行，说明上面所有bean都已经初始化了
	 */
	@Bean("mybatisPlusConfigInitSuccess")
	@DependsOn({"plusPropertiesCustomizer", "paginationInterceptor"})
	public void mybatisPlusConfigInitSuccess() {
		logger.info("MyBatis插件: 自定义SQL输出 [加载完成]");
		logger.info("MyBatisPlus配置: 自定义ID策略 [加载完成]");
		logger.info("MyBatisPlus配置: 分页插件 [加载完成]");
	}
}

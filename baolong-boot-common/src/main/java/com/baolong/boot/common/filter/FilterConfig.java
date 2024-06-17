package com.baolong.boot.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 过滤器 配置类
 *
 * @author Baolong 2024/6/6 23:40
 * @version 1.0
 * @since 1.8
 */
@Configuration
public class FilterConfig {
	private final Logger logger = LoggerFactory.getLogger(FilterConfig.class);

	@Bean("encodingFilter")
	public FilterRegistrationBean<EncodingFilter> encodingFilter() {
		//注册过滤器
		FilterRegistrationBean<EncodingFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new EncodingFilter());
		//过滤路径，允许通过的
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}

	@Bean("corsFilter")
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new CorsFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}

	/**
	 * 该方法执行，说明上面所有bean都已经初始化了
	 */
	@Bean("filterConfigInitSuccess")
	@DependsOn({"redisTemplate", "redisScript"})
	public void filterConfigInitSuccess() {
		logger.info("Filter配置: encodingFilter [加载完成]");
		logger.info("Filter配置: corsFilter [加载完成]");
	}
}

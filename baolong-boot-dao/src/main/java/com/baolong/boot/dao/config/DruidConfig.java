package com.baolong.boot.dao.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Druid配置类
 *
 * @author Baolong 2024年06月15 22:43
 * @version 1.0
 * @since 1.8
 */
@Configuration
public class DruidConfig {
	private final Logger logger = LoggerFactory.getLogger(DruidConfig.class);
	@Value("${baolong.druid.allow}")
	private String allow;
	@Value("${baolong.druid.deny}")
	private String deny;
	@Value("${baolong.druid.username}")
	private String username;
	@Value("${baolong.druid.password}")
	private String password;
	@Value("${baolong.druid.reset-enable}")
	private String resetEnable;
	@Value("${baolong.druid.url-patterns}")
	private String urlPatterns;
	@Value("${baolong.druid.exclusions}")
	private String exclusions;

	/**
	 * 配置Druid监控启动页面
	 *
	 * @return servletRegistrationBean
	 */
	@Bean("druidStartViewServlet")
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "baolong.druid", name = "enable-monitor", havingValue = "true")
	public ServletRegistrationBean<StatViewServlet> druidStartViewServlet() {
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean =
				new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
		// 白名单
		servletRegistrationBean.addInitParameter("allow", allow);
		// 黑名单
		servletRegistrationBean.addInitParameter("deny", deny);
		// 登录查看信息的账密，用于登录Druid监控后台
		servletRegistrationBean.addInitParameter("loginUsername", username);
		servletRegistrationBean.addInitParameter("loginPassword", password);
		// 是否能够重置数据
		servletRegistrationBean.addInitParameter("resetEnable", resetEnable);
		return servletRegistrationBean;
	}

	/**
	 * Druid监控过滤器配置规则
	 *
	 * @return filterFilterRegistrationBean
	 */
	@Bean("filterRegistrationBean")
	@DependsOn({"druidStartViewServlet"})
	public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
		FilterRegistrationBean<WebStatFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
		filterFilterRegistrationBean.setFilter(new WebStatFilter());
		// 添加过滤规则
		filterFilterRegistrationBean.addUrlPatterns(urlPatterns);
		// 添加忽略的URL
		filterFilterRegistrationBean.addInitParameter("exclusions", exclusions);
		return filterFilterRegistrationBean;
	}

	/**
	 * 该方法执行，说明上面所有bean都已经初始化了
	 */
	@Bean("druidConfigInitSuccess")
	@DependsOn({"druidStartViewServlet", "filterRegistrationBean"})
	public void druidConfigInitSuccess() {
		logger.info("Druid配置: Druid监控 [加载完成]");
		logger.info("Druid配置: Druid过滤规则 [加载完成]");
	}
}

package com.baolong.boot.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Swagger 配置类
 *
 * @author Baolong 2024/6/6 23:46
 * @version 1.0
 * @since 1.8
 */
@Configuration
public class SwaggerConfig {
	private final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

	@Bean("customOpenAPI")
	public OpenAPI customOpenAPI() {
		Contact contact = new Contact();
		contact.setName("暴龙");
		contact.setEmail("510132075@qq.com");
		contact.setUrl("https://yanzeyi.icu");
		return new OpenAPI()
				.info(new Info()
						.title("baolong-boot项目 - 接口文档")
						.description("项目简介，支持Markdown格式：`这里是代码标签哦`，**这里是强调哦**")
						.version("1.0.0")
						.contact(contact)
				);
	}
	/*
	* 这里可以不用配置类的方式，直接通过下面这个注解实现上面bean中的配置内容
	* @OpenAPIDefinition(
				info = @Info(
						title = "baolong-boot项目 - 接口文档",
						version = "1.0.0",
						description = "项目简介，支持Markdown格式：`这里是代码标签哦`，**这里是强调哦**",
						contact = @Contact(url = "https://yanzeyi.icu", name = "暴龙", email = "510132075@qq.com")
				)
		)
	* */

	@Bean("testOpenApi")
	public GroupedOpenApi testOpenApi() {
		return GroupedOpenApi.builder()
				.group("测试")
				.pathsToMatch("/test/**")
				.build();
	}

	@Bean("appOpenApi")
	public GroupedOpenApi appOpenApi() {
		return GroupedOpenApi.builder()
				.group("app")
				.pathsToMatch("/app/**")
				.build();
	}
	@Bean("adminOpenApi")
	public GroupedOpenApi adminOpenApi() {
		return GroupedOpenApi.builder()
				.group("admin")
				.pathsToMatch("/system/**")
				.build();
	}

	/**
	 * 该方法执行，说明上面所有bean都已经初始化了
	 */
	@Bean("swaggerConfigInitSuccess")
	@DependsOn({"customOpenAPI", "testOpenApi", "appOpenApi"})
	public void swaggerConfigInitSuccess() {
		logger.info("Swagger: customOpenAPI [加载完成]");
	}
}

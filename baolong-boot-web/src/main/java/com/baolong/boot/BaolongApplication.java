package com.baolong.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动类
 *
 * @author Baolong 2024/6/5 19:46
 * @version 1.0
 * @since 1.8
 */
@ConfigurationPropertiesScan
@SpringBootApplication
public class BaolongApplication {
	public static void main(String[] args) throws UnknownHostException {
		ConfigurableApplicationContext application = SpringApplication.run(BaolongApplication.class, args);
		Environment env = application.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		String port = env.getProperty("server.port");
		String path = env.getProperty("server.servlet.context-path");
		System.out.println(" ____              _                   \n" +
				"|  _ \\            | |                  \n" +
				"| |_) | __ _  ___ | | ___  _ __   __ _ \n" +
				"|  _ < / _` |/ _ \\| |/ _ \\| '_ \\ / _` |\n" +
				"| |_) | (_| | (_) | | (_) | | | | (_| |\n" +
				"|____/ \\__,_|\\___/|_|\\___/|_| |_|\\__, |\n" +
				"                                  __/ |\n" +
				"                                 |___/ ");
		System.out.println("baolong-boot 启动成功...");
		System.err.println("----------------------------------------------------------\n\t" +
				"Application baolong-Boot is running!\n\t" +
				"Local: \t\thttp://localhost:" + port + path + "/\n\t" +
				"External: \thttp://" + ip + ":" + port + path + "/\n\t" +
				"Druid: \t\thttp://127.0.0.1"  + ":" + port + path + "/druid/index.html\n\t" +
				"Swagger文档: http://" + ip + ":" + port + path + "/doc.html\n" +
				"----------------------------------------------------------");
	}
}

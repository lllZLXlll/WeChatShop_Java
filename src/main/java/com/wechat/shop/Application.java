package com.wechat.shop;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Spring Boot 应用启动类
 *
 */

// 启用注解事务管理  
@EnableTransactionManagement

// 启用定时任务
@EnableScheduling

// 接口类扫描包配置
@MapperScan("com.wechat.shop.mapper")

// Spring Boot 应用的标识 (scanBasePackages="com.wechat.shop.config")
@SpringBootApplication
public class Application {

	private static Logger logger = Logger.getLogger(Application.class);
	
	public static void main(String[] args) {
		// 程序启动入口
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(Application.class, args);
		logger.info("---!!!---项目启动成功---!!!---");
		
	}
}

package com.springboot.bootservice.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
//springboot注解 = @EnableAutoConfiguration + 扫当前包目录下的类 + @Configuration

//@EnableJpaRepositories("com.springboot.bootservice.dao")//jpa扫包配置
//@EntityScan("com.springboot.bootservice.entity")//jpa扫包配置

@MapperScan(basePackages = {
		"com.springboot.bootservice.mapper"})//mybatis Mapper扫描

@ComponentScan(basePackages = {
		"com.springboot.bootservice.service",
		"com.springboot.bootservice.web",
		"com.springboot.bootservice.handler",
		"com.springboot.bootservice.config",
		"com.springboot.bootservice.aop",
		"com.springboot.base"})
//注意这里扫包不能直接扫，com.springboot.bootservice，否则
//启动报错 If you want an embedded database please put a supported one on the classpath

//@EnableTransactionManagement //spring事务：编程事务，声明事务（xml,注解配置）


@EnableSwagger2//开启Swagger，然后通过http://localhost:端口/swagger-ui.html访问

//@EnableAsync//开启异步

@EnableAutoConfiguration
//@EnableDiscoveryClient
public class ServiceApp {
	public static void main(String[] args) {
		SpringApplication.run(ServiceApp.class, args);
	}
}



package com.springboot.bootSchedule.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {
		"com.springboot.bootSchedule",
        "com.springboot.base"})
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

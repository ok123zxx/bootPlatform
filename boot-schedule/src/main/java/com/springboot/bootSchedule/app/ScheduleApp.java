package com.springboot.bootSchedule.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan(basePackages = {
		"com.springboot.bootSchedule",
        "com.springboot.base"})
@EnableScheduling
public class ScheduleApp {
	public static void main(String[] args) {
		SpringApplication.run(ScheduleApp.class, args);
	}
}

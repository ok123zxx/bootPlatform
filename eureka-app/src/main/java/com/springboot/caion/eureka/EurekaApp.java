package com.springboot.caion.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by zengJian on 2018/5/3<br>
 * <br>
 */
@EnableEurekaServer
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class EurekaApp {
    //java -jar .\eureka-app-1.0-SNAPSHOT.jar --server.port=8001
    //java -jar .\eureka-app-1.0-SNAPSHOT.jar --server.port=8000
    public static void main(String[] args) {
        SpringApplication.run(EurekaApp.class, args);
    }
}

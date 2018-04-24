package com.springboot.bootRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by zengJian on 2018/4/19<br>
 * <br>
 */

//报错：Cannot determine embedded database driver class for database type NONE
//原因是：springboot启动时会自动注入数据源和配置jpa
//解决：在@SpringBootApplication中排除其注入
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})

@ComponentScan(basePackages = {
        "com.springboot.bootRedis"})
public class RedisApp {

    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class,args);
    }
}

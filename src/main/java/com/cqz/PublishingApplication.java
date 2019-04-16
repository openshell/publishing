package com.cqz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.cqz.dao")
/**
 * 项目启动类
 * @Author: openshell
 * @Description:
 */

public class PublishingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublishingApplication.class, args);
    }

}

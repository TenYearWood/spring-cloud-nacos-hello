package com.csii;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description com.csii
 * @author: chengyu
 * @date: 2026-01-06 11:55
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.csii.common.dao"})
public class SecurityStarter {

    public static void main(String[] args) {
        SpringApplication.run(SecurityStarter.class, args);
    }
}

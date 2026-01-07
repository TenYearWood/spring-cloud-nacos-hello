package com.csii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description com.csii
 * @author: chengyu
 * @date: 2026-01-06 11:55
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderStarter {

    public static void main(String[] args) {
        SpringApplication.run(ProviderStarter.class, args);
    }
}

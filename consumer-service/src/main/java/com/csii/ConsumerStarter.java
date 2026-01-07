package com.csii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @description com.csii
 * @author: chengyu
 * @date: 2026-01-06 19:19
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerStarter {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerStarter.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

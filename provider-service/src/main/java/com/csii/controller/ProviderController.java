package com.csii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description com.csii.controller
 * @author: chengyu
 * @date: 2026-01-06 13:15
 */
@RestController
@RefreshScope
public class ProviderController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${custom.config}")
    private String configValue;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Provider Service!";
    }

    @GetMapping("/services")
    public List<String> services() {
        List<String> serverList =  discoveryClient.getServices();
        return serverList;
    }

    @GetMapping("/getConfigValue")
    public String getConfigValue() {
        return configValue;
    }
}

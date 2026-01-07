package com.csii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description com.csii.controller
 * @author: chengyu
 * @date: 2026-01-06 19:20
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/call")
    public String callService() {
        return restTemplate.getForObject("http://provider-service/hello", String.class);
    }
}

package com.csii.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 测试Controller
 * @author: chengyu
 * @date: 2026-01-06 19:20
 */
@RestController
public class TestController {


    @GetMapping("/testGlobalException")
    public String testGlobalException() {
        int a = 1/0;
        return "success";
    }
}

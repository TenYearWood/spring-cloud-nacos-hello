package com.csii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    // 重定向到登录页面
    @RequestMapping("/login")
    public String index() {
        return "redirect:login.html";
    }

    // 重定向到登录成功页面
    @RequestMapping ("/toMain")
    public String toMain() {
        return "redirect:main.html";
    }

    // 重定向到登录成功页面
    @RequestMapping ("/toError")
    public String toError() {
        int a = 1/0;
        return "redirect:error.html";
    }
}
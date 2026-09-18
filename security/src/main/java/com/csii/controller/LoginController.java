package com.csii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    // 重定向到登录成功页面
    @RequestMapping ("/toMain")
    public String toMain() {
        return "redirect:main.html";
    }

    // 重定向到登录成功页面
    @RequestMapping ("/toError")
    public String toError() {
        return "redirect:error.html";
    }

    @GetMapping("findAll")
    @ResponseBody
    public String findAll() {
        return "findAll";
    }

    @GetMapping("find")
    @ResponseBody
    public String find() {
        return "find";
    }
}
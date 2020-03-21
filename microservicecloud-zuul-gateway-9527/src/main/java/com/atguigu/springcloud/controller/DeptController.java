package com.atguigu.springcloud.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @GetMapping("/hello")
    public String hello() {
        return "你好。。。。";
    }

}

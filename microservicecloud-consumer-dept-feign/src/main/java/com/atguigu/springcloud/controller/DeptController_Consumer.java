package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class DeptController_Consumer {

    // 调用基础模块的feign 接口
    @Autowired
    private DeptClientService deptFeign;


    @GetMapping("/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptFeign.get(id);
    }


    @GetMapping("/list")
    public List<Dept> list() {
        //注意是  调用接口的方法，服务端口路径写在了接口上
        return deptFeign.list();


    }
}
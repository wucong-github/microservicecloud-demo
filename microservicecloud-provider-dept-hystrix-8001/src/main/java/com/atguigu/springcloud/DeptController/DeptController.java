package com.atguigu.springcloud.DeptController;


import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;


    @GetMapping("/hello")
    public String hello() {
        return "你好。。。。";
    }


    @GetMapping("/get/{id}")
    // 一旦调用服务方法失败并抛出错误，会自动调用@HystrixCommand 中标注好的后备方法
    @HystrixCommand(fallbackMethod = "ProcessHystrix_Get")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.get(id);

        if (null == dept) {
            throw new RuntimeException("id名:" + id + "没有对应信息");
        }
        return dept;
    }


    public Dept ProcessHystrix_Get(@PathVariable("id") Long id) {
        return new Dept().setDeptno(id)
                .setDname("id名:" + id + "没有对应信息")
                .setDb_source("no mysql");


    }


}

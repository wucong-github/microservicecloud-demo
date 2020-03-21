package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class DeptController_Consumer {

    //private static final String REST_URL_PREFIX = "http://localhost:8001";
    // microservicecloud-dept   MICROSERVICECLOUD-DEPT 不区分大小写
    private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";
    /**
     * 使用 RestTemplate 访问restful接口非常简单
     * （url,        requestMap, ResponseBean.class）
     * rest请求路径  请求参数      返回的对象类型
     */
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() {
        return "你好。。。。";
    }

    @GetMapping("/hello02")
    public String hello02() {
        return  restTemplate.getForObject(REST_URL_PREFIX +"/dept/hello",String.class) ;
    }


    @PostMapping("/add")
    public boolean add(Dept dept) {
        Dept dept1 = restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Dept.class);
        return true;
    }

    @GetMapping("/get/{id}")
    public Dept get(@PathVariable("id") int id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class, id);

    }


    @GetMapping("/list")
    public List<Dept> list() {
        //注意是 List.class
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

    // 测试@EnableDiscoveryClient ,消费端可以调用服务发现
    @GetMapping("/dept/discovery")
    public Object discovery() {

        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);

    }


}

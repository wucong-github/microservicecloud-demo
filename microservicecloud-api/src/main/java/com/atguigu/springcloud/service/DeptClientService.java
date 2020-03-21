package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
// Feign 负载均衡接口
//@FeignClient("microservicecloud-dept")
@FeignClient(value = "microservicecloud-dept",fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {


    @GetMapping("dept/get/{id}")
    public Dept get(@PathVariable("id") Long id);


    @GetMapping("/dept/list")
    public List<Dept> list();

}

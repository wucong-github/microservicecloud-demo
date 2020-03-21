package com.atguigu.springcloud.DeptController;


import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;


    @GetMapping("/hello")
    public String hello() {
        return "你好。。。。";
    }


    @PostMapping("/add")
    public boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @GetMapping("/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return deptService.get(id);
    }

    @GetMapping("/list")
    public List<Dept> list() {
        return deptService.list();
    }


    @GetMapping("/discovery")
    public Object discovery() {

        // getServices() 取出注册中心的所有服务名称
        List<String> list = client.getServices();
        list.forEach(System.out::println);

        // 获取一个名称为 "MICROSERVICECLOUD-DEPT" 的服务实例
        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
         // 将这个实例的相关信息 依次打印出来
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t"
                    + element.getHost() + "\t"
                    + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }


}

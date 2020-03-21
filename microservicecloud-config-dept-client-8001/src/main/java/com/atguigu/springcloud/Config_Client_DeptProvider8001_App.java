package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// 本服务启动后会作为eureka的客户端，因为application没有禁止，所以会自动注册进eureka 注册服务中心
@EnableEurekaClient
// 服务发现，让其他服务能够发现本服务
@EnableDiscoveryClient
public class Config_Client_DeptProvider8001_App {

    public static void main(String[] args) {
        SpringApplication.run(Config_Client_DeptProvider8001_App.class,args);
    }
}

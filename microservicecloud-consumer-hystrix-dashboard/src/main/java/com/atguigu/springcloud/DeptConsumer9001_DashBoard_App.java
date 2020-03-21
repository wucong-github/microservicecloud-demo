package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


@SpringBootApplication
@EnableHystrixDashboard
@EnableFeignClients(basePackages="com.atguigu.springcloud")
public class DeptConsumer9001_DashBoard_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer9001_DashBoard_App.class, args);
    }

}

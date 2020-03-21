package com.atguigu.springcloud.cfgbeans;

import com.atguigu.myrule.RandomRule_ZY;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced // Ribbon 是一套客户端 负载均衡工具
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    @Bean
    public IRule myRule(){
        // return new RandomRule();  // 用我们重新选择的随机算法，替代轮询
        //  return new RetryRule();   // 重试算法

        return new RandomRule_ZY();
    }

}

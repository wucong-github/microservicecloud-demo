package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import sun.dc.path.FastPathProducer;

import java.util.List;

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {

        return new DeptClientService() {
            @Override
            public Dept get(Long id) {

                return  new Dept().setDeptno(id)
                        .setDname("id名:" + id + "没有对应信息")
                        .setDb_source("no mysql");

            }

            @Override
            public List<Dept> list() {

                return null;
            }
        };

    }
}

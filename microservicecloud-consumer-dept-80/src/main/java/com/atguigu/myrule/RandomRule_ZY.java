package com.atguigu.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomRule_ZY extends AbstractLoadBalancerRule {


    // total = 0 // 当total==5以后，我们的指针才能往下走
    // index =0  //当前对外提供服务的服务器地址
    // total 需要重新置为0 ，但是已经达到过一个5次。我们的index =1
    //当index =3 时 重新设置为 index =0


    private int total = 0; // 总共调用的次数。目前要求每台被调用5次
    private int currentIndex = 0;  // 当前提供服务的机器号
    // 返回一个你要访问的服务器，8001，8002，8003
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        // 只要server 没有被赋值就会继续执行
        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            /**
             *   80微服务启动时，会去读取eureka上的注册表信息，RestTemplate 进行负载均衡
             *   lb就是负载均衡器,它保存了注册表信息 ，从中获取服务器列表  getReachableServers
             *
             */
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }


            // 服务器访问总次数 小于5 就继续访问这台服务器
            if (total < 5) {
                server = upList.get(currentIndex);
                total++;

            } else {
                // 大于5 的话，置0，访问下一台服务器  currentIndex++;
                total = 0;
                currentIndex++;
                // 当 服务器全部轮了一遍后 ，服务器下标置0
                // 此时 server 没有被赋值 还会继续从头执行
                if (currentIndex >= upList.size()) {
                    currentIndex = 0;
                }
            }


            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }
            // 如果这个服务还活着在运行 ，那么返回这个服务
            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        // 获得一个 0 到 serverCount 的随机整数
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}

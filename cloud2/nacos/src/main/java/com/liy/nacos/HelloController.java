package com.liy.nacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
public class HelloController {

    @Autowired
    DiscoveryClient dc;
    @Value("${liy}")
    String liy;

    @GetMapping("/hello")
    public String hello(){
        List<ServiceInstance> instances = dc.getInstances("hello-nacos");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getHost()+":"+instance.getPort());
        }
        return liy;
    }
}

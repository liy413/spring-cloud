package com.liy.nacosclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloNacosClientController {

    @Autowired
    RestTemplate rs;

    @GetMapping("/hello")
    public String hello(){
        return rs.getForObject("http://hello-nacos/hello",String.class);
    }
}

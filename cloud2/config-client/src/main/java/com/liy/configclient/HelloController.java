package com.liy.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${liy}")
    String liy;

    @GetMapping("/hello")
    public String hello(){
        return liy;
    }
}

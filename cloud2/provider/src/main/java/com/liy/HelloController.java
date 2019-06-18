package com.liy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${server.port}")
    String port;
    @GetMapping("/hello")
    public String hello(){
        return "hello>>"+port;
    }
}

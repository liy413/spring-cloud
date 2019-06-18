package com.liy.sentinel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSentinelController {

    @GetMapping("/hello")
    public String hello(){
        return "hello sentinel";
    }
}

package com.liy.hellostream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    MyChannel mc;

    @GetMapping("/hello")
    public void hello() {
        mc.output().send(MessageBuilder.withPayload("hello steam!!").build());
    }
}

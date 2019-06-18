package com.liy.bus;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class sender {

    @Autowired
    AmqpTemplate at;

    public void hello(String msg){
        this.at.convertAndSend("liy",msg);
    }
}

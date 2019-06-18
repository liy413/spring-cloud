package com.liy.bus;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "liy")
public class Recevier {

    @RabbitHandler
    public void get(String msg){
        System.out.println("recevier:"+msg);
    }
}

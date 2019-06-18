package com.liy.hellostream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(MyChannel.class)
public class MyRecevier {
    @Value("${server.port}")
    String port;

    @StreamListener(MyChannel.INPUT)
    public void say(Object obj){
        System.out.println("myRecevier:"+obj+":"+port);
    }
}

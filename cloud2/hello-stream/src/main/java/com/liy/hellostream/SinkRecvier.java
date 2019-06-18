package com.liy.hellostream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class SinkRecvier {

    @StreamListener(Sink.INPUT)
    public void say(Object obj){
        System.out.println("recevier:"+obj);
    }
}

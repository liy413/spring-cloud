package com.liy.hellostream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyChannel {
    String INPUT="INPUT1";
    String OUTPUT="OUTPUT";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();

}

package com.liy.bus;


import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusApplication.class, args);
    }

      @Bean
       Queue queue(){
        return new  Queue("liy");
      }
}

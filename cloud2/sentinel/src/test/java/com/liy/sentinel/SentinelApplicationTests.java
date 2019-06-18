package com.liy.sentinel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SentinelApplicationTests {

    @Test
    public void contextLoads() {
        RestTemplate rs = new RestTemplate();

        for (int i = 0 ; i < 10 ; i++){
            String s = rs.getForObject("http://localhost:8087/hello", String.class);
            System.out.println(s+">>"+new Date());
        }


    }


}

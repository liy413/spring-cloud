package com.liy;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    DiscoveryClient ds;

    @Autowired
    @Qualifier("rs")
    RestTemplate rs;

    @Autowired
    @Qualifier("rs2")
    RestTemplate rs1;

    int count = 0;


    @GetMapping("/hello")
    public String hello() throws  Exception {
        List<ServiceInstance> instances = ds.getInstances("provider");
        ServiceInstance instance = instances.get(count++ % instances.size());

        String s = "http://" + instance.getHost() + ":" + instance.getPort() + "/hello";

        //String s1 = rs.getForObject(s, String.class);
        URL url = new URL(s);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        if (conn.getResponseCode()==200){
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String s1 = reader.readLine();

            return s1;
        }

        return  "";

    }


    @GetMapping("/hello2")
    public String hello2(){
        String object = rs1.getForObject("http://provider/hello", String.class);
        return object;

    }

}

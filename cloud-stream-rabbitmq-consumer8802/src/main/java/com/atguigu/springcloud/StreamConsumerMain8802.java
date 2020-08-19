package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableEurekaClient
public class StreamConsumerMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerMain8802.class,args);
    }
}

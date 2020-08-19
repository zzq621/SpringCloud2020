package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.config.RestTempleteConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String SERVER_URL;

    @GetMapping("/order/consumer/get/{id}")
    public String orderConsumer(@PathVariable("id") Integer id){
        return restTemplate.getForObject(SERVER_URL+"/payment/get/"+id,String.class);
    }

}

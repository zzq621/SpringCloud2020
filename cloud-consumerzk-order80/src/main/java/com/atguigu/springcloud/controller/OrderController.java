package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String INVOKE_URL="http://cloud-provider-payment";
    @GetMapping("/consumer/zk")
    public String paymentOrder(){
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
    }
}

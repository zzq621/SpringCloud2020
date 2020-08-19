package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Sink.class)  //绑定输入对象
public class RecivesController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)  //监听输入对象
    public void reciveMessage(Message<String> message){
        System.out.println("消费者1号："+message.getPayload()+"  serverPort:"+serverPort);
    }

}

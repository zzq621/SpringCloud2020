package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 在容器中注入RestTemplate实现服务之间的相互调用
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced    //开启微服务访问的负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}

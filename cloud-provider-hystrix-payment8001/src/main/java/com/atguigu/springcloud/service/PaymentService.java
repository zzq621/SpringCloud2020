package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String payment_info_ok(Integer id){
        return Thread.currentThread().getName()+"payment_info_OK";
    }
    /**
     * 出现服务端超时的情况，实现备份响应
     * fallbackMethod:设置超时后的跳转方法
     * commandProperties：设置超时属性
     *      HystrixProperty:本请求响应时间不得超过values值3000，否则调用fallbackMethod
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "payment_info_handler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String payment_info_timeOut(Integer id){
       //模拟请求超时
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int age = 10/0; //模拟服务端异常
        return Thread.currentThread().getName()+"payment_info_TimeOut";
    }

    public String payment_info_handler(Integer id){
        return Thread.currentThread().getName()+"我是服务端8001payment_info_TimeOut_Handler等待是一门艺术";
    }


    //////////熔断////////////////
    @HystrixCommand(fallbackMethod = "payment_info_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String payment_info_roll(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("参数不能为负数。。。。。。。。");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+uuid;
    }

    public String payment_info_fallback(@PathVariable("id") Integer id){
        return Thread.currentThread().getName()+"服务8001发生熔断机制";
    }

}

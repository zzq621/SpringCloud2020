package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")  //全局服务降级
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment/feign/hystrix/ok/{id}")
    public String PaymentInfo_Ok(@PathVariable("id") Integer id){
        return paymentService.PaymentInfo_Ok(id);
    }

    public String payment_info_handler(Integer id){
        return Thread.currentThread().getName()+"我是消费端80payment_info_TimeOut_Handler等待是一门艺术";
    }
    /**
     * 出现服务端超时的情况，实现备份响应
     * fallbackMethod:设置超时后的跳转方法
     * commandProperties：设置超时属性
     *      HystrixProperty:本请求响应时间不得超过values值3000，否则调用fallbackMethod
     * @param id
     * @return
     */
   /* @HystrixCommand(fallbackMethod = "payment_info_handler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })*/
    @GetMapping("/payment/feign/hystrix/timeout/{id}")
    @HystrixCommand
    public String PaymentInfo_timeout(@PathVariable("id") Integer id){
        //模拟请求超时
       /* try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       //int age = 10/0;
        return paymentService.PaymentInfo_timeout(id);
    }

    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }

}

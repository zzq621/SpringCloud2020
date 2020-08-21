package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.handler.CustomeBlockHandler;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    //按资源名称限流
    @GetMapping("/blockResource")
    @SentinelResource(value = "blockResource", blockHandler = "handlerException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流", new Payment(2020L, "sentinel"));
    }

    public CommonResult handlerException(BlockException exception) {
        return new CommonResult(404, exception.getClass().getCanonicalName() + "服务不可用");
    }

    //按请求路径限流
    @GetMapping("/resource/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按url限流",new Payment(2020L,"Sentinel"));
    }


    //全局限流处理
    @GetMapping("/byCustome")
    @SentinelResource(value = "byCustome",
            blockHandlerClass = CustomeBlockHandler.class,
            blockHandler = "blockHandler01")
    public CommonResult byCustome(){
        return new CommonResult(200,"自定义限流",new Payment(2020L,"Sentinel"));
    }
}

package com.atguigu.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * 自定义全局限流处理方法
 */
public class CustomeBlockHandler {

    public static CommonResult blockHandler01(BlockException exception){
        return new CommonResult(400,"用户自定义的限流处理方法");
    }

    public static CommonResult blockHandler02(BlockException exception){
        return new CommonResult(500,"用户自定义的限流处理方法");
    }
}

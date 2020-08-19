package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 降级处理方法
 */
@Component
public class PaymentFallbackService implements PaymentService{

    @Override
    public String PaymentInfo_Ok(Integer id) {
        return "PaymentInfo_Ok 出故障了。。。○( ＾皿＾)っ";
    }

    @Override
    public String PaymentInfo_timeout(Integer id) {
        return "PaymentInfo_timeout 出故障了。。。○( ＾皿＾)っ";
    }
}

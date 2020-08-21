package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.domain.CommonResult;
import com.atguigu.springcloud.alibaba.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/decreate")
    public CommonResult decreate(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        accountService.decreate(userId,money);
        return new CommonResult(200,"扣钱成功");
    }


}

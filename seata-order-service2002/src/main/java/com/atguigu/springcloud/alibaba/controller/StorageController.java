package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.domain.CommonResult;
import com.atguigu.springcloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/storage/decreate")
    public CommonResult decreate(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        storageService.decreate(productId,count);
        return new CommonResult(200,"库存扣减成功");
    }

}

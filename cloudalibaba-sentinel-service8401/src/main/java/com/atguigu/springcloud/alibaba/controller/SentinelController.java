package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SentinelController {

    @GetMapping("/testA")
    public String testA(){
        return "TestA，Hi~ o(*￣▽￣*)ブ";
    }

    @GetMapping("/testB")
    public String testB(){
        return "TestB，Hi~ o(*￣▽￣*)ブ";
    }

    @GetMapping("/testD")
    public String testD(){
       /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       int age= 10/0;
        return "TestD，Hi~ o(*￣▽￣*)ブ";
    }

    @GetMapping("/testE")
    public String testE(){
       /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       log.info("测试异常数");
        int age= 10/0;
        return "TestE，Hi~ o(*￣▽￣*)ブ";
    }

    /**
     * 热点规则限流
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_hotKey")
    public String  testHotKey(@RequestParam(value = "p1",required = false) String p1,
                              @RequestParam(value = "p2",required = false) String p2){
        return "successfully O(∩_∩)O";
    }

    //兜底方法
    public String deal_hotKey(String p1, String p2, BlockException exception){
        return "fail ┭┮﹏┭┮";
    }

}

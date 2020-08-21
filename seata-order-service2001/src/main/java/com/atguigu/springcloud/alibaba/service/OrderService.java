package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
    void create(Order order); //添加订单
}

package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    void create(Order order); //添加订单

    void update(@Param("userId") Long userId,@Param("status") Integer status); //修改订单状态

}

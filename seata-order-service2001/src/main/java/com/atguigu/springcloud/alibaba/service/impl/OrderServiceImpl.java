package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountService accountService;


    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {

        log.info("***********添加订单***********");
        orderDao.create(order);

        log.info("*********产品库存数扣减***********");
        storageService.decreate(order.getProductId(),order.getCount());
        log.info("*********产品库存数扣减成功***********");

        log.info("*********账户扣减***********");
        accountService.decreate(order.getUserId(),order.getMoney());
        log.info("*********账户扣减成功***********");

        log.info("********修改订单状态**********");
        orderDao.update(order.getUserId(),0);
        log.info("********修改订单状态成功**********");

        log.info("***********添加订单成功***********");
    }


}

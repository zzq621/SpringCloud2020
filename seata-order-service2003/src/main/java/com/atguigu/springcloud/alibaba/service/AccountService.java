package com.atguigu.springcloud.alibaba.service;

import java.math.BigDecimal;

public interface AccountService {
    void decreate(Long userId, BigDecimal money);
}

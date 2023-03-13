package com.zqi.admin.service.impl;

import com.zqi.admin.service.OrderService;
import com.zqi.clients.OrderClient;
import com.zqi.param.PageParam;
import com.zqi.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderClient orderClient;

    @Override
    public R list(PageParam pageParam) {
        R r = orderClient.list(pageParam);
        log.info("OrderServiceImpl.list业务结束，结果：{}",r);
        return r;
    }
}

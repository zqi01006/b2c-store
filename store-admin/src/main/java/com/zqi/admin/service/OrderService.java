package com.zqi.admin.service;


import com.zqi.param.PageParam;
import com.zqi.utils.R;

public interface OrderService {
    /**
     * 查询订单数据
     * @param pageParam
     * @return
     */
    R list(PageParam pageParam);
}

package com.zqi.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zqi.param.OrderParam;
import com.zqi.param.PageParam;
import com.zqi.pojo.Order;
import com.zqi.utils.R;

public interface OrderService extends IService<Order> {

    /**
     * 进行订单数据保存业务
     * @param orderParam
     * @return
     */
    R save(OrderParam orderParam);

    /**
     * 分组查询订单数据
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 检查订单中是否有商品引用
     * @param productId
     * @return
     */
    R check(Integer productId);

    /**
     * 后台管理查询订单数据
     * @param pageParam
     * @return
     */
    R adminList(PageParam pageParam);
}

package com.zqi.cart.service;

import com.zqi.param.CartSaveParam;
import com.zqi.pojo.Cart;
import com.zqi.utils.R;

import java.util.List;

public interface CartService {
    /**
     * 购物车数据添加方法
     * @param cartSaveParam
     * @return 001成功 002已经存在 003库存
     */
    R save(CartSaveParam cartSaveParam);

    /**
     * 返回购物车数据
     * @param userId
     * @return 确保返回一个数组
     */
    R list(Integer userId);

    /**
     * 更新购物车业务
     * @param cart
     * @return
     */
    R update(Cart cart);

    /**
     * 删除购物车数据
     * @param cart
     * @return
     */
    R remove(Cart cart);

    /**
     * 清空对应id的购物车项
     * @param cartIds
     */
    void clearIds(List<Integer> cartIds);

    /**
     * 查询购物车项
     * @param productId
     * @return
     */
    R check(Integer productId);
}

package com.zqi.collect.service;

import com.zqi.pojo.Collect;
import com.zqi.utils.R;

public interface CollectService {
    /**
     * 添加收藏的方法
     * @param collect
     * @return 001 004
     */
    R save(Collect collect);

    /**
     * 根据用户id查询商品信息集合
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 根据用户id和商品id删除收藏数据
     * @param collect userId productId
     * @return 001 004
     */
    R remove(Collect collect);

    /**
     * 根据商品id删除
     * @param productId
     * @return
     */
    R removeByPid(Integer productId);
}

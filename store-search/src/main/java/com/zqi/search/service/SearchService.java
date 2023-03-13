package com.zqi.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zqi.param.ProductSearchParam;
import com.zqi.pojo.Product;
import com.zqi.utils.R;

import java.io.IOException;

public interface SearchService {

    /**
     * 根据关键字和分页进行数据库查询
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 商品同步：插入和更新
     * @param product
     * @return
     */
    R save(Product product) throws IOException;

    /**
     * 进行es数据库的商品删除
     * @param productId
     * @return
     */
    R remove(Integer productId) throws IOException;
}

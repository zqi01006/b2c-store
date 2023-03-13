package com.zqi.admin.service.impl;

import com.zqi.admin.service.ProductService;
import com.zqi.clients.ProductClient;
import com.zqi.clients.SearchClient;
import com.zqi.param.ProductSaveParam;
import com.zqi.param.ProductSearchParam;
import com.zqi.pojo.Product;
import com.zqi.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品服务实现类
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private ProductClient productClient;

    @Override
    public R search(ProductSearchParam productSearchParam) {
        R search = searchClient.search(productSearchParam);
        log.info("ProductServiceImpl.search业务结束，结果：{}",search);
        return search;
    }

    @Override
    public R save(ProductSaveParam productSaveParam) {
        //保存商品和图片
        R r = productClient.adminCount(productSaveParam);
        log.info("ProductServiceImpl.save业务结束，结果：{}",r);
        return r;
    }

    @Override
    public R update(Product product) {
        R r = productClient.adminUpdate(product);
        log.info("ProductServiceImpl.update业务结束，结果：{}",r);
        return r;
    }

    @Override
    public R remove(Integer productId) {
        R r = productClient.adminRemove(productId);
        log.info("ProductServiceImpl.remove业务结束，结果：{}",r);
        return r;
    }
}

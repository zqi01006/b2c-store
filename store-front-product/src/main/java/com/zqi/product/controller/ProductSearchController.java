package com.zqi.product.controller;

import com.zqi.pojo.Product;
import com.zqi.product.serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 搜索服务调用的Controller
 */
@RestController
@RequestMapping("product")
public class ProductSearchController {

    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public List<Product> allList(){
        return productService.allList();
    }

}

package com.zqi.admin.controller;

import com.zqi.admin.service.ProductService;
import com.zqi.admin.utils.AliyunOSSUtils;
import com.zqi.param.ProductSaveParam;
import com.zqi.param.ProductSearchParam;
import com.zqi.pojo.Product;
import com.zqi.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 商品后台管理controller
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;

    @GetMapping("list")
    public R adminList(ProductSearchParam productSearchParam){

        return productService.search(productSearchParam);
    }

    @PostMapping("upload")
    public R adminUpload(@RequestParam("img") MultipartFile img) throws Exception {

        String filename = img.getOriginalFilename();
        filename = UUID.randomUUID().toString().replaceAll("-","")+filename;
        String contentType = img.getContentType();
        byte[] content = img.getBytes();

        int hours = 1000;

        String url = aliyunOSSUtils.uploadImage(filename, content, contentType, hours);
        System.out.println("url="+url);
        return R.ok("图片上传成功！",url);
    }

    @PostMapping("save")
    public R adminSave(ProductSaveParam productSaveParam){
        return productService.save(productSaveParam);
    }

    @PostMapping("update")
    public R adminUpdate(Product product){
        return productService.update(product);
    }

    @PostMapping("remove")
    public R adminRemove(Integer productId){
        return productService.remove(productId);
    }
}

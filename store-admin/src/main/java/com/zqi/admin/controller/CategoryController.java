package com.zqi.admin.controller;

import com.zqi.admin.service.CategoryService;
import com.zqi.param.PageParam;
import com.zqi.pojo.Category;
import com.zqi.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类别controller
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public R pageList(PageParam pageParam){

        return categoryService.pageList(pageParam);
    }

    @PostMapping("save")
    public R pageList(Category category){

        return categoryService.save(category);
    }

    @PostMapping("remove")
    public R remove(Integer categoryId){

        return categoryService.remove(categoryId);
    }

    @PostMapping("update")
    public R update(Category category){

        return categoryService.update(category);
    }
}

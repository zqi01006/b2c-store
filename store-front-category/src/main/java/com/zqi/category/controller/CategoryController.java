package com.zqi.category.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zqi.category.service.CategoryService;
import com.zqi.param.ProductHotParam;
import com.zqi.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 类别控制器
 */

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R byName(@PathVariable String categoryName) {
        if (StringUtils.isEmpty(categoryName)){
            return R.fail("类别名称不能为空");
        }

        return categoryService.byName(categoryName);
    }

    /**
     * 热门类别id查询
     * @param result
     * @return
     */
    @PostMapping("/hots")
    public R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result) {
        if (result.hasErrors()){
            return R.fail("类别集合查询失败");
        }
        return categoryService.hotsCategory(productHotParam);
    }

    @GetMapping("list")
    public R list() {
        return categoryService.list();
    }


}

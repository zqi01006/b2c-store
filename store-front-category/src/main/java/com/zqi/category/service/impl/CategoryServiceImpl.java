package com.zqi.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zqi.category.mapper.CategoryMapper;
import com.zqi.category.service.CategoryService;
import com.zqi.clients.ProductClient;
import com.zqi.param.PageParam;
import com.zqi.param.ProductHotParam;
import com.zqi.pojo.Category;
import com.zqi.pojo.Product;
import com.zqi.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductClient productClient;

    @Override
    public R byName(String categoryName) {
        //1、封装查询参数
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name",categoryName);
        //2、查询数据库
        Category category = categoryMapper.selectOne(categoryQueryWrapper);
        //3、封装返回结果
        if (category == null){
            log.info("CategoryServiceImpl.byName业务结束，结果：类别查询失败");
            return R.fail("类别查询失败");
        }
        log.info("CategoryServiceImpl.byName业务结束，结果：类别查询成功");
        return R.ok("类别查询成功",category);
    }

    @Override
    public R hotsCategory(ProductHotParam productHotParam) {
        //封装查询参数
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_name",productHotParam.getCategoryName());
        queryWrapper.select("category_id");

        //查询数据库
        List<Object> ids = categoryMapper.selectObjs(queryWrapper);

        R ok = R.ok("类别集合查询成功", ids);
        log.info("CategoryServiceImpl.hotsCategory业务结束，结果：{}",ok);
        return ok;
    }

    @Override
    public R list() {
        List<Category> categoryList = categoryMapper.selectList(null);
        R ok = R.ok("类别集合查询成功", categoryList);
        log.info("CategoryServiceImpl.list业务结束，结果：{}",ok);

        return ok;
    }

    @Override
    public R listPage(PageParam pageParam) {
        IPage<Category> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());

        page = categoryMapper.selectPage(page,null);

        return R.ok("类别分页数据查询成功！",page.getRecords(),page.getTotal());

    }

    @Override
    public R save(Category category) {

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",category.getCategoryName());
        Long count = categoryMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("类别已经存在，添加失败");
        }
        int insert = categoryMapper.insert(category);

        return R.ok("类别添加成功!");
    }

    @Override
    public R adminRemove(Integer categoryId) {

        Long aLong = productClient.adminCount(categoryId);
        if (aLong>0) {
            return R.fail("类别删除失败"+aLong+"商品正在被引用");
        }
        int i = categoryMapper.deleteById(categoryId);
        return R.ok("类别数据删除成功！");
    }

    /**
     * 类修改功能
     * @param category
     * @return
     */
    @Override
    public R adminUpdate(Category category) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",category.getCategoryName());
        Long count = categoryMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("类别已经存在，修改失败！");
        }

        int i = categoryMapper.updateById(category);
        log.info("CategoryServiceImpl.adminUpdate业务结束，结果：{}",i);

        return R.ok("类别数据修改成功！");
    }
}

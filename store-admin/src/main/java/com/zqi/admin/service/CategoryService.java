package com.zqi.admin.service;

import com.zqi.param.PageParam;
import com.zqi.pojo.Category;
import com.zqi.utils.R;

/**
 *
 */
public interface CategoryService {

    /**
     * 分页查询方法
     * @param pageParam
     * @return
     */
    R pageList(PageParam pageParam);

    /**
     * 进行分类数据添加
     * @param category
     * @return
     */
    R save(Category category);

    /**
     * 根据id删除类别数据
     * @param categoryId
     * @return
     */
    R remove(Integer categoryId);

    /**
     * 修改类别信息
     * @param category
     * @return
     */
    R update(Category category);
}

package com.zqi.admin.service.impl;

import com.zqi.admin.service.CategoryService;
import com.zqi.clients.CategoryClient;
import com.zqi.param.PageParam;
import com.zqi.pojo.Category;
import com.zqi.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 业务类别实现
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryClient categoryClient;

    @Cacheable(value = "list.category",key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    @Override
    public R pageList(PageParam pageParam) {

        R r = categoryClient.adminPageList(pageParam);
        log.info("CategoryServiceImpl.pageList业务实现，结果：{}",r);

        return r;
    }

    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R save(Category category) {

        R r = categoryClient.adminSave(category);
        log.info("CategoryServiceImpl.save业务实现，结果：{}",r);
        return r;
    }

    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R remove(Integer categoryId) {

        R r = categoryClient.adminRemove(categoryId);
        log.info("CategoryServiceImpl.remove业务方法结束，结果：{}",r);
        return r;
    }

    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R update(Category category) {

        R r = categoryClient.adminUpdate(category);
        log.info("CategoryServiceImpl.update业务方法结束，结果：{}",r);
        return r;
    }
}

package com.zqi.admin.service;

import com.zqi.param.CartListParam;
import com.zqi.param.PageParam;
import com.zqi.pojo.User;
import com.zqi.utils.R;

public interface UserService {
    /**
     * 用户展示业务的方法
     * @param pageParam
     * @return
     */
    R userList(PageParam pageParam);

    /**
     * 删除用户数据
     * @param cartListParam
     * @return
     */
    R userRemove(CartListParam cartListParam);

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    R userUpdate(User user);

    /**
     * 添加用户数据
     * @param user
     * @return
     */
    R save(User user);
}

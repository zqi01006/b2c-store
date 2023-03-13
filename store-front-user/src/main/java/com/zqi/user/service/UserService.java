package com.zqi.user.service;

import com.zqi.param.PageParam;
import com.zqi.param.UserCheckParam;
import com.zqi.param.UserLoginParam;
import com.zqi.pojo.User;
import com.zqi.utils.R;

public interface UserService {

    /**
     * 检查账号是否可用业务
     * @param userCheckParam 账号参数 已经校验完毕
     * @return 检查结果
     */
    R check(UserCheckParam userCheckParam);

    /**
     * 注册业务
     * @param user 参数已经校验，但是密码还是明文
     * @return 结果001，004
     */
    R register(User user);

    /**
     * 登录业务
     * @param userLoginParam 账号和密码 已经校验 但是密码是明文
     * @return 结果是001，004
     */
    R login(UserLoginParam userLoginParam);

    /**
     * 后台管理调用，查询全部用户数据
     * @param pageParam
     * @return
     */
    R listPage(PageParam pageParam);

    /**
     * 根据用户id删除数据
     * @param userId
     * @return
     */
    R remove(Integer userId);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    R update(User user);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    R save(User user);
}

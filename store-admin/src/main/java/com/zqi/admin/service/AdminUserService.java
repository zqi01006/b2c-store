package com.zqi.admin.service;


import com.zqi.admin.param.AdminUserParam;
import com.zqi.admin.pojo.AdminUser;

public interface AdminUserService {
    /**
     * 登录业务方法
     * @param adminUserParam
     * @return
     */
    AdminUser login(AdminUserParam adminUserParam);


}

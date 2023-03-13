package com.zqi.admin.inteceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录保护拦截器
 * 进来的，都是要拦截的
 * 检查session中是否有数据 userInfo 有，放行！ 没有跳转到登录界面即可！
 */
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object userInfo = request.getSession().getAttribute("userInfo");
        if (userInfo != null) {
            //放行，已经登录
            return true;
        }else {
            response.sendRedirect(request.getContextPath()+"/index.html");
            return false;
        }

        //false拦截 | true放行

    }
}

package com.zqi.user.controller;

import com.zqi.param.UserCheckParam;
import com.zqi.param.UserLoginParam;
import com.zqi.pojo.User;
import com.zqi.user.service.UserService;
import com.zqi.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块的控制器类
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 检查账号是否可用的接口
     * @param userCheckParam 接收检查的账号实体，内部有参数校验注解
     * @param result 获取校验结果的实体对象
     * @return 返回封装结果R对象即可
     */
    @PostMapping("check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result) {
        // 检查是否符合检验注解的规则，符合false，不符合true
        boolean b = result.hasErrors();
        if(b){
            return R.fail("账号为null，不可使用！");
        }
        return userService.check(userCheckParam);
    }

    @PostMapping("register")
    public R register(@RequestBody @Validated User user,BindingResult result){
        if (result.hasErrors()){
            //如果存在异常,证明请求参数不符合注解要求
            return  R.fail("参数异常,不可注册!");
        }
        return userService.register(user);
    }

    @PostMapping("login")
    public R Login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result) {
        if (result.hasErrors()){
            // 如果存在异常，证明请求参数不符合注解要求
            return R.fail("参数异常，不可登录");
        }
        return userService.login(userLoginParam);
    }
}

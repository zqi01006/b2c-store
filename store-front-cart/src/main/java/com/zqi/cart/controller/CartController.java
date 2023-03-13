package com.zqi.cart.controller;

import com.zqi.cart.service.CartService;
import com.zqi.param.CartListParam;
import com.zqi.param.CartSaveParam;
import com.zqi.pojo.Cart;
import com.zqi.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("save")
    public R save(@RequestBody @Validated CartSaveParam cartSaveParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("核心参数为null，添加失败！");
        }
        return cartService.save(cartSaveParam);
    }

    @PostMapping("list")
    public R list(@RequestBody @Validated CartListParam cartListParam,BindingResult result){
        if (result.hasErrors()){
            return R.fail("购物车数据查询失败!");
        }
        return cartService.list(cartListParam.getUserId());
    }

    @PostMapping("update")
    public R update(@RequestBody Cart cart){

        return cartService.update(cart);
    }

    @PostMapping("remove")
    public R remove(@RequestBody Cart cart){
        return cartService.remove(cart);
    }

    @PostMapping("remove/check")
    public R removeCheck(@RequestBody Integer productId){
        return cartService.check(productId);
    }

}

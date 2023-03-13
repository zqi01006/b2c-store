package com.zqi.user.controller;

import com.zqi.param.AddressListParam;
import com.zqi.param.AddressParam;
import com.zqi.param.AddressRemoveParam;
import com.zqi.pojo.Address;
import com.zqi.user.service.AddressService;
import com.zqi.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("list")
    public R list(@RequestBody @Validated AddressListParam addressListParam, BindingResult result) {
        if (result.hasErrors()){
            return R.ok("参数异常，查询失败！");
        }
        return addressService.list(addressListParam.getUserId());
    }

    @PostMapping("save")
    public R save(@RequestBody @Validated AddressParam addressParam, BindingResult result) {
        if (result.hasErrors()){
            return R.ok("参数异常，保存失败！");
        }
        Address address = addressParam.getAdd();
        address.setUserId(addressParam.getUserId());
        return addressService.save(address);
    }

    @PostMapping("remove")
    public R delete(@RequestBody @Validated AddressRemoveParam addressRemoveParam, BindingResult result) {
        if (result.hasErrors()){
            return R.ok("参数异常，删除失败！");
        }
        return addressService.remove(addressRemoveParam.getId());
    }
}

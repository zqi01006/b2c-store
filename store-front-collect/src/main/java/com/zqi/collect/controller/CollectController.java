package com.zqi.collect.controller;

import com.zqi.collect.service.CollectService;
import com.zqi.pojo.Collect;
import com.zqi.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @PostMapping("save")
    public R save(@RequestBody Collect collect){
        return collectService.save(collect);
    }

    @PostMapping("list")
    public R list(@RequestBody Collect collect){
        return collectService.list(collect.getUserId());
    }

    @PostMapping("remove")
    public R remove(@RequestBody Collect collect) {
        return collectService.remove(collect);
    }

    @PostMapping("remove/product")
    public R removeByPid(@RequestBody Integer productId) {
        return collectService.removeByPid(productId);
    }
}

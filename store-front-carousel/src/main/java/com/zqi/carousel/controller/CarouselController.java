package com.zqi.carousel.controller;

import com.zqi.carousel.service.CarouselService;
import com.zqi.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    /**
     * 查询轮播图数据，只要优先级最高的6条数据
     * @return
     */
    @PostMapping("list")
    public R list(){
        return carouselService.list();
    }
}

package com.zqi.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 热门商品参数接收对象
 */
@Data
public class ProductHotParam extends PageParam {
    @NotEmpty
    private List<String> categoryName;
}

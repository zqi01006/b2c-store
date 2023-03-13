package com.zqi.param;

import lombok.Data;

@Data
public class PageParam {
    private int currentPage = 1; // 默认值1
    private int pageSize = 15; // 默认值15
}

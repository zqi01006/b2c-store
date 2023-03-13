package com.zqi.to;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单发送商品服务的实体
 */
@Data
public class OrderToProduct implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer productId;
    private Integer num;
}

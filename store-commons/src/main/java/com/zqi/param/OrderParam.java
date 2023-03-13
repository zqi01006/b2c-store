package com.zqi.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zqi.vo.CartVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 订单发送商品服务参数
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderParam implements Serializable {
    public static final Long serialVersionUID = 1L;
    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVo> products;

}

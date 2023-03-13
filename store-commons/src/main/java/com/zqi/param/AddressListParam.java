package com.zqi.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 地址集合参数接收
 */
@Data
public class AddressListParam {
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}

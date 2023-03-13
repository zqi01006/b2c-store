package com.zqi.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zqi.pojo.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 地址接收值的param
 */
@Data
public class AddressParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    private Address add;
}

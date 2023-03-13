package com.zqi.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 接收前端参数的param
 * TODO：要使用jsr 303 的注解进行参数校验
 * @NotBlank 字符串不能为null，和空字符串""
 * @NotNUll 字符串不能为null
 * @NotEmpty 集合类型，长度不能为0
 */
@Data
public class UserCheckParam {
    @NotBlank
    private String userName; // 参数名称要等于前端传递的json key的名称！
}

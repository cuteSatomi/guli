package com.zzx.center.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author zzx
 * @date 2021-04-22 13:52:37
 */
@Data
public class RegisterVO {
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;
}

package com.zzx.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zzx
 * @date 2021-04-10 21:13
 */
@Data
public class ResultTO<T> {
    /** 是否操作成功 */
    @ApiModelProperty(value = "是否操作成功", required = true, example = "true")
    private boolean success;

    /** 错误码 */
    @ApiModelProperty(value = "错误码", required = true, example = "0001")
    private Integer errorCode;

    /** 错误提示 */
    @ApiModelProperty(value = "错误提示", required = true, example = "系统错误")
    private String message;

    /** 操作结果 */
    @ApiModelProperty(value = "操作结果", required = true)
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public static <K> ResultTO<K> buildSuccess(K data) {
        ResultTO<K> result = new ResultTO<>();
        result.setSuccess(true);
        result.setErrorCode(0);
        result.setData(data);
        return result;
    }

    public static <K> ResultTO<K> buildSuccess(K data, String message) {
        ResultTO<K> result = new ResultTO<>();
        result.setSuccess(true);
        result.setMessage(message);
        result.setErrorCode(0);
        result.setData(data);
        return result;
    }

    public static <K> ResultTO<K> buildFailed(Integer code, String message) {
        ResultTO<K> result = new ResultTO<>();
        result.setSuccess(false);
        result.setErrorCode(code);
        result.setMessage(message);
        return result;
    }
}

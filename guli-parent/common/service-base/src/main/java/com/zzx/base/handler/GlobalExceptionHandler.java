package com.zzx.base.handler;

import com.zzx.base.exception.GuliException;
import com.zzx.utils.ResultTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zzx
 * @date 2021-04-11 11:56
 * 全局的异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(GuliException.class)
    public ResultTO error(GuliException e) {
        return ResultTO.buildFailed(e.getMsg());
    }
}

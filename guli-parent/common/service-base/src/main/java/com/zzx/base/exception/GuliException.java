package com.zzx.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzx
 * @date 2021-04-13 20:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {
    private Integer code;
    private String msg;
}

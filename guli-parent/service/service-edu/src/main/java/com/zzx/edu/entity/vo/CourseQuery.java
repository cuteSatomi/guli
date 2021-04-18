package com.zzx.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zzx
 * @date 2021-04-11 10:47
 */
@Data
public class CourseQuery {
    @ApiModelProperty(value = "课程名称，模糊查询")
    private String title;
    @ApiModelProperty(value = "课程状态 Normal：已发布；Draft：未发布")
    private String status;
    @ApiModelProperty(value = "查询开始时间",example = "2020-04-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "查询结束时间",example = "2020-04-01 10:10:10")
    private String end;
}

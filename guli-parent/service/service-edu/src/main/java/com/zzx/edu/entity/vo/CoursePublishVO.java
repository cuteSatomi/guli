package com.zzx.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * step3中课程发布所需的VO
 *
 * @author zzx
 * @date 2021-04-17 12:04
 */
@Data
@ApiModel(value = "课程发布信息")
public class CoursePublishVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String cover;
    private String teacherName;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String price;
    private Integer lessonNum;
}

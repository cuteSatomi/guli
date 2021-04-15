package com.zzx.edu.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author zzx
 * @date 2021-04-15 19:55
 */
@Data
public class ChapterVO {
    private String id;
    private String title;
    private List<VideoVO> children;
}

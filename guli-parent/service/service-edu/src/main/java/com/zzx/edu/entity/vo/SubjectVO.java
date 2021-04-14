package com.zzx.edu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzx
 * @date 2021-04-14 19:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectVO {
    private String id;
    private String title;
    private List<SubjectVO> children = new ArrayList<SubjectVO>();
}

package com.zzx.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author zzx
 * @date 2021-04-13 20:25
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}

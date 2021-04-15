package com.zzx.edu.controller;


import com.zzx.edu.entity.vo.ChapterVO;
import com.zzx.edu.service.ChapterService;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2021-04-14
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/service/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/getChapterVideo/{courseId}")
    public ResultTO getChapterVideo(@PathVariable String courseId) {
        List<ChapterVO> list = chapterService.getChapterVideoByCourseId(courseId);
        return ResultTO.buildSuccess(list);
    }
}


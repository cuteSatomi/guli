package com.zzx.edu.controller;


import com.zzx.edu.entity.Chapter;
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

    @PostMapping("/addChapter")
    public ResultTO addChapter(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return ResultTO.buildSuccess("新增章节成功");
    }

    @PostMapping("/updateChapter")
    public ResultTO updateChapter(@RequestBody Chapter chapter) {
        chapterService.updateById(chapter);
        return ResultTO.buildSuccess("修改章节成功");
    }

    @GetMapping("/getChapter/{chapterId}")
    public ResultTO getChapterById(@PathVariable String chapterId) {
        Chapter chapter = chapterService.getById(chapterId);
        return ResultTO.buildSuccess(chapter);
    }

    @DeleteMapping("/{chapterId}")
    public ResultTO deleteChapterById(@PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        return flag ? ResultTO.buildSuccess("删除成功") : ResultTO.buildFailed("该章节内还存在所属小节，删除失败");
    }
}


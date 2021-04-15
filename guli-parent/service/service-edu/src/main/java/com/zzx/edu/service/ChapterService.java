package com.zzx.edu.service;

import com.zzx.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.edu.entity.vo.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zzx
 * @since 2021-04-14
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 得到章节及其小节
     * @param courseId
     * @return
     */
    List<ChapterVO> getChapterVideoByCourseId(String courseId);
}

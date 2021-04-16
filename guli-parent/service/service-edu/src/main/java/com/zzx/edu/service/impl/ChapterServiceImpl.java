package com.zzx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzx.edu.entity.Chapter;
import com.zzx.edu.entity.Video;
import com.zzx.edu.entity.vo.ChapterVO;
import com.zzx.edu.entity.vo.VideoVO;
import com.zzx.edu.mapper.ChapterMapper;
import com.zzx.edu.mapper.VideoMapper;
import com.zzx.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2021-04-14
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Resource
    private VideoMapper videoMapper;

    @Override
    public List<ChapterVO> getChapterVideoByCourseId(String courseId) {
        // 首先得到所有章节列表
        QueryWrapper<Chapter> chapterWrapper = new QueryWrapper<Chapter>();
        chapterWrapper.eq("course_id", courseId);
        List<Chapter> chapterList = this.list(chapterWrapper);
        // 得到小节列表
        QueryWrapper<Video> videoWrapper = new QueryWrapper<Video>();
        videoWrapper.eq("course_id", courseId);
        List<Video> videoList = videoMapper.selectList(videoWrapper);

        List<ChapterVO> result = chapterList.stream().map(chapter -> {
            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(chapter, chapterVO);
            return chapterVO;
        }).collect(Collectors.toList());
        for (ChapterVO chapterVO : result) {
            List<VideoVO> videoVOList = videoList.stream().filter(video -> video.getChapterId().equals(chapterVO.getId()))
                    .map(video -> {
                        VideoVO videoVO = new VideoVO();
                        BeanUtils.copyProperties(video, videoVO);
                        return videoVO;
                    }).collect(Collectors.toList());
            chapterVO.setChildren(videoVOList);
        }
        return result;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        // 根据chapterId查询小节
        QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("chapter_id", chapterId);
        Integer count = videoMapper.selectCount(videoWrapper);
        if (count > 0) {
            return false;
        }
        return this.removeById(chapterId);
    }
}

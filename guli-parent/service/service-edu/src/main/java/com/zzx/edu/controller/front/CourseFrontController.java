package com.zzx.edu.controller.front;

import com.zzx.edu.client.OrderClient;
import com.zzx.edu.entity.frontvo.FrontCourseDetailsVO;
import com.zzx.edu.entity.frontvo.FrontCourseVO;
import com.zzx.edu.entity.vo.ChapterVO;
import com.zzx.edu.service.ChapterService;
import com.zzx.edu.service.CourseService;
import com.zzx.utils.JwtUtils;
import com.zzx.utils.ResultTO;
import com.zzx.utils.ordervo.OrderCourse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzx
 * @date 2021-04-24 13:06
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/service/front/course")
public class CourseFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    /**
     * 查询前台课程列表
     *
     * @param current
     * @param size
     * @param frontCourseVO
     * @return
     */
    @PostMapping("/getFrontCourseList/{current}/{size}")
    public ResultTO getFrontCourseList(@PathVariable long current, @PathVariable long size,
                                       @RequestBody(required = false) FrontCourseVO frontCourseVO) {
        Map<String, Object> map = courseService.getFrontCourseList(current, size, frontCourseVO);
        return ResultTO.buildSuccess(map);
    }

    @GetMapping("/getFrontCourseInfo/{courseId}")
    public ResultTO getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        FrontCourseDetailsVO courseDetailsVO = courseService.getBaseCourseInfo(courseId);

        // 根据课程id查询出章节以及小节信息
        List<ChapterVO> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        // 根据课程id和用户id查询当前课程是否已经购买
        boolean isBuy = orderClient.courseIsBuy(courseId, JwtUtils.getMemberIdByJwtToken(request));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("courseDetailsVO", courseDetailsVO);
        map.put("chapterVideoList", chapterVideoList);
        map.put("isBuy", isBuy);
        return ResultTO.buildSuccess(map);
    }

    /**
     * 根据课程id查询课程相关信息
     *
     * @param courseId
     * @return
     */
    @GetMapping("/getOrderCourseInfo/{courseId}")
    public OrderCourse getOrderCourseInfo(@PathVariable String courseId) {
        FrontCourseDetailsVO courseDetails = courseService.getBaseCourseInfo(courseId);
        OrderCourse orderCourse = new OrderCourse();
        BeanUtils.copyProperties(courseDetails, orderCourse);
        return orderCourse;
    }
}

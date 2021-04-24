import request from '@/utils/request'

export default {

  /**
   * 前台分页查询课程列表
   * @param current
   * @param size
   */
  getCourseList(current, size, frontCourseVO) {
    return request({
      url: `/edu/service/front/course/getFrontCourseList/${current}/${size}`,
      method: 'post',
      data: frontCourseVO
    });
  },
  /**
   * 得到所有课程分类
   */
  getAllSubjects() {
    return request({
      url: `/edu/service/subject/getAllSubjects`,
      method: 'get'
    });
  },
  /**
   * 得到课程信息
   * @param courseId
   */
  getCourseInfo(courseId) {
    return request({
      url: `/edu/service/front/course/getFrontCourseInfo/${courseId}`,
      method: 'get'
    });
  }
}

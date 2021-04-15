import request from '@/utils/request'

export default {
  /**
   * 添加课程信息
   * @param courseInfo
   */
  addCourseInfo(courseInfo) {
    return request({
      url: '/edu/service/course/addCourseInfo',
      method: 'post',
      data: courseInfo
    });
  },
  /**
   * 查询所有讲师
   */
  getTeacherList() {
    return request({
      url: '/edu/service/teacher/findAll',
      method: 'get',
    });
  },
  /**
   * 根据课程id得到课程信息
   * @param courseId
   */
  getCourseInfo(courseId) {
    return request({
      url: `/edu/service/course/getCourseInfo/${courseId}`,
      method: 'get'
    });
  },
  /**
   * 修改课程信息
   * @param courseInfo
   */
  updateCourseInfo(courseInfo) {
    return request({
      url: `/edu/service/course/updateCourseInfo`,
      method: 'post',
      data: courseInfo
    });
  }

}

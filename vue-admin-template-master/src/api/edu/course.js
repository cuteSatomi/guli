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
  }
}

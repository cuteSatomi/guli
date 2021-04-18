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
  },
  /**
   * 根据课程id查询发布页的课程信息
   * @param courseId
   */
  getPublishCourseInfo(courseId) {
    return request({
      url: `/edu/service/course/getPublishCourseInfo/${courseId}`,
      method: 'get'
    });
  },
  /**
   * 课程最终发布
   * @param courseId
   */
  publishCourse(courseId) {
    return request({
      url: `/edu/service/course/publishCourse/${courseId}`,
      method: 'post'
    });
  },
  /**
   * 查询课程列表
   */
  listAllCourse(){
    return request({
      url: `/edu/service/course/findAll`,
      method: 'get'
    });
  },
  /**
   * 分页查询课程列表
   * @param current       当前页
   * @param size          每页大小
   * @param teacherQuery  查询条件
   */
  getCourseListPage(current, size, courseQuery) {
    return request({
      url: `/edu/service/course/pageCourseCondition/${current}/${size}`,
      method: 'post',
      // data表示将teacherQuery转换为JSON传递到后端接口中
      data: courseQuery
    });
  },
}

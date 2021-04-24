import request from '@/utils/request'

export default {

  /**
   * 前台分页查询讲师列表
   * @param current
   * @param size
   */
  getTeacherList(current, size) {
    return request({
      url: `/edu/service/front/teacher/getFrontTeacherList/${current}/${size}`,
      method: 'get'
    });
  },
  /**
   * 讲师详情页查询讲师和所授课程信息
   * @param teacherId
   */
  getTeacherInfo(teacherId) {
    return request({
      url: `/edu/service/front/teacher/getFrontTeacherInfo/${teacherId}`,
      method: 'get'
    });
  }
}

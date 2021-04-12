import request from '@/utils/request'

export default {

  /**
   * 分页查询讲师列表
   * @param current       当前页
   * @param size          每页大小
   * @param teacherQuery  查询条件
   */
  getTeacherListPage(current, size, teacherQuery) {
    return request({
      url: `/edu/teacher/pageTeacherCondition/${current}/${size}`,
      method: 'post',
      // data表示将teacherQuery转换为JSON传递到后端接口中
      data: teacherQuery
    });
  },

  /**
   * 根据id删除讲师
   * @param id  讲师的id
   */
  deleteTeacherById(id) {
    return request({
      url: `/edu/teacher/${id}`,
      method: 'delete'
    });
  },

  /**
   * 新增讲师
   * @param teacher
   */
  addTeacher(teacher) {
    return request({
      url: `/edu/teacher/addTeacher`,
      method: 'post',
      data: teacher
    });
  },

  /**
   * 根据id查询讲师
   * @param id
   */
  getTeacherById(id) {
    return request({
      url: `/edu/teacher/getTeacherById/${id}`,
      method: 'get'
    });
  },

  /**
   * 更新讲师
   * @param teacher
   */
  updateTeacher(teacher) {
    return request({
      url: `/edu/teacher/updateTeacher`,
      method: 'post',
      data: teacher
    });
  }
}

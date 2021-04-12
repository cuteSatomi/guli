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
    })
  }
}

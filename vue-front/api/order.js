import request from '@/utils/request'

export default {
  /**
   * 得到首页课程和讲师数据
   */
  getIndex() {
    return request({
      url: '/edu/service/front/index',
      method: 'get'
    });
  }
}

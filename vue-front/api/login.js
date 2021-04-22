import request from '@/utils/request'

export default {
  /**
   * 用户登录
   * @param userInfo
   */
  login(userInfo) {
    return request({
      url: `/center/member/login`,
      method: 'post',
      data: userInfo
    });
  },
  getMemberInfo() {
    return request({
      url: `/center/member/getMemberInfo`,
      method: 'get',
    });
  }
}

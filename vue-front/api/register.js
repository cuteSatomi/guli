import request from '@/utils/request'

export default {
  /**
   * 根据手机号发送验证码
   * @param phone
   */
  sendCode(phone) {
    return request({
      url: `/edu/msm/send/${phone}`,
      method: 'get'
    });
  },
  /**
   * 用户注册
   * @param member
   */
  registerMember(formMember) {
    return request({
      url: `/center/member/register`,
      method: 'post',
      data: formMember
    });
  }
}

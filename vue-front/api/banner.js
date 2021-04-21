import request from '@/utils/request'

export default {
  /**
   * 得到Banner首页数据
   */
  getBannerList() {
    return request({
      url: '/cms/banner/front/listBanner',
      method: 'get'
    });
  }
}

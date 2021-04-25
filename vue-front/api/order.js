import request from '@/utils/request'

export default {
  /**
   * 生成订单
   */
  createOrder(courseId) {
    return request({
      url: `/edu/order/createOrder/${courseId}`,
      method: 'get'
    });
  },
  /**
   * 根据订单号查询订单
   * @param orderNo
   */
  getOrderInfo(orderNo) {
    return request({
      url: `/edu/order/getOrderInfo/${orderNo}`,
      method: 'get'
    });
  }
}

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
  },
  /**
   * 根据订单号生成微信支付二维码
   * @param orderNo
   */
  createQR(orderNo){
    return request({
      url: `/edu/order/paylog/createQR/${orderNo}`,
      method: 'get'
    });
  },
  /**
   * 根据订单号查询订单状态
   * @param orderNo
   */
  queryPayStatus(orderNo){
    return request({
      url: `/edu/order/paylog/queryPayStatus/${orderNo}`,
      method: 'get'
    });
  }
}

import request from '@/utils/request'

export default {
  getPlayAuth(videoId) {
    return request({
      url: `/edu/vod/getPlayAuth/${videoId}`,
      method: 'get'
    });
  }
}

import request from '@/utils/request'
import vi from "element-ui/src/locale/lang/vi";

export default {
  /**
   * 新增小节
   * @param video
   */
  addVideo(video) {
    return request({
      url: '/edu/service/video/addVideo',
      method: 'post',
      data: video
    });
  },
  /**
   * 根据id查询小节
   * @param chapterId
   */
  getVideoById(videoId) {
    return request({
      url: `/edu/service/video/getVideo/${videoId}`,
      method: 'get',
    });
  },
  /**
   * 更新小节
   * @param video
   */
  updateVideo(video) {
    return request({
      url: '/edu/service/video/updateVideo',
      method: 'post',
      data: video
    });
  },
  /**
   * 根据id删除小节
   * @param videoId
   */
  deleteVideoById(videoId) {
    return request({
      url: `/edu/service/video/${videoId}`,
      method: 'delete'
    });
  }
}

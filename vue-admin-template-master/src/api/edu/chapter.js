import request from '@/utils/request'

export default {
  /**
   * 根据课程id得到所有的章节和小节
   * @param courseId
   */
  getChapterVideo(courseId) {
    return request({
      url: `/edu/service/chapter/getChapterVideo/${courseId}`,
      method: 'get'
    });
  }
}

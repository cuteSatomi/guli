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
  },
  /**
   * 添加章节
   * @param chapter
   */
  addChapter(chapter) {
    return request({
      url: `/edu/service/chapter/addChapter`,
      method: 'post',
      data: chapter
    });
  },
  /**
   * 修改章节
   * @param chapter
   */
  updateChapter(chapter) {
    return request({
      url: `/edu/service/chapter/updateChapter`,
      method: 'post',
      data: chapter
    });
  },
  /**
   * 根据id查询章节
   * @param chapterId
   */
  getChapterById(chapterId) {
    return request({
      url: `/edu/service/chapter/getChapter/${chapterId}`,
      method: 'get',
    });
  },
  /**
   * 根据id删除章节
   * @param chapterId
   */
  deleteChapter(chapterId) {
    return request({
      url: `/edu/service/chapter/${chapterId}`,
      method: 'delete',
    });
  }
}

import request from '@/utils/request'

export default {
  getAllSubjects(){
    return request({
      url: `/edu/service/subject/getAllSubjects`,
      method: 'get'
    });
  }
}

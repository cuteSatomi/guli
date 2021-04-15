<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>
    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>

    <el-form label-width="130px">
      <el-form-item label="课程标题">
        <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>
      </el-form-item>

      <!-- 所属分类 TODO -->
      <el-form-item label="课程分类">
        <el-select v-model="courseInfo.subjectParentId" placeholder="一级分类" @change="subjectOneChanged">
          <el-option v-for="subject in subjectOneList" :key="subject.id" :label="subject.title" :value="subject.id"/>
        </el-select>
        <!-- 二级分类 -->
        <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
          <el-option v-for="subject in subjectTwoList" :key="subject.id" :label="subject.title" :value="subject.id"/>
        </el-select>
      </el-form-item>

      <!-- 课程讲师 TODO -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option v-for="teacher in teacherList" :key="teacher.id" :label="teacher.name" :value="teacher.id"/>
        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
      </el-form-item>

      <!-- 课程简介 TODO -->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description"/>
      </el-form-item>

      <!-- 课程封面 TODO -->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API+'/edu/oss/upload'"
          class="avatar-uploader">
          <img :src="courseInfo.cover" style="width: 300px"/>
        </el-upload>
      </el-form-item>

      <el-form-item label="课程价格">
        <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/>
        元
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import course from "@/api/edu/course";
  import subject from "@/api/edu/subject";
  import Tinymce from "@/components/Tinymce";

  export default {
    name: "info",
    components: {
      Tinymce,   // 声明组件
    },
    data() {
      return {
        saveBtnDisabled: false,
        courseInfo: {
          title: '',
          subjectId: '',         //二级分类id
          subjectParentId: '',   //一级分类id
          teacherId: '',
          lessonNum: 0,
          description: '',
          cover: '/static/01.jpg',
          price: 0
        },
        BASE_API: process.env.BASE_API,
        teacherList: [],
        subjectOneList: [],
        subjectTwoList: []
      }
    },
    created() {
      // 得到讲师列表
      this.getTeacherList();
      // 得到所有分类列表
      this.getOneSubject();
    },
    methods: {
      saveOrUpdate() {
        course.addCourseInfo(this.courseInfo)
          .then(response => {
            // 提示
            this.$message({
              type: 'success',
              message: '添加课程信息成功'
            });
            // 跳转到第二页
            this.$router.push({path: '/course/chapter/' + response.data});
          });
      },
      getTeacherList() {
        course.getTeacherList()
          .then(response => {
            this.teacherList = response.data;
          });
      },
      getOneSubject() {
        subject.getAllSubjects()
          .then(response => {
            this.subjectOneList = response.data;
          });
      },
      // 一级分类切换，value是当前一级分类的id值
      subjectOneChanged(value) {
        for (let i = 0; i < this.subjectOneList.length; i++) {
          let subjectOne = this.subjectOneList[i];
          if (subjectOne.id === value) {
            this.subjectTwoList = subjectOne.children;
            this.courseInfo.subjectId = '';
          }
        }
      },
      // 上传封面之前会被调用的方法
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt1M = file.size / 1024 / 1024 < 1;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt1M) {
          this.$message.error('上传头像图片大小不能超过 1MB!');
        }
        return isJPG && isLt1M;
      },
      // 上传成功的回调方法
      handleAvatarSuccess(res, file) {
        this.courseInfo.cover = res.data;
      }
    }
  }
</script>

<style scoped>
.tinymce-container {
  line-height: 29px;
}
</style>

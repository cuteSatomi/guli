<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>

    <!-- 章节 -->
    <ul class="chapterList">
      <li v-for="chapter in chapterList" :key="chapter.id">
        <p>
          {{chapter.title}}
          <span class="acts">
            <el-button type="text">添加课时</el-button>
            <el-button type="text">编辑</el-button>
            <el-button type="text">删除</el-button>
          </span>
        </p>

        <!-- 视频 -->
        <ul class="chapterList videoList">
          <li v-for="video in chapter.children" :key="video.id">
            <p>{{video.title}}</p>
          </li>
        </ul>
      </li>
    </ul>

    <el-form>
      <el-form-item align="center">
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import chapter from "@/api/edu/chapter";

  export default {
    name: "chapter",
    data() {
      return {
        saveBtnDisabled: false,
        chapterList: [],
        courseId: '',
      }
    },
    created() {
      if (this.$route.params && this.$route.params.id) {
        this.courseId = this.$route.params.id;
      }
      this.getChapterVideo();
    },
    methods: {
      previous() {
        this.$router.push({path: '/course/info/' + this.courseId});
      },
      next() {
        this.$router.push({path: '/course/publish/1'});
      },
      // 得到所有章节以及小节
      getChapterVideo() {
        chapter.getChapterVideo(this.courseId)
          .then(response => {
            this.chapterList = response.data;
          });
      }
    }
  }
</script>

<style scoped>
  .chapterList {
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
  }

  .chapterList li {
    position: relative;
  }

  .chapterList p {
    float: left;
    font-size: 20px;
    margin: 10px 0;
    padding: 10px;
    height: 70px;
    line-height: 50px;
    width: 100%;
    border: 1px solid #DDD;
  }

  .chapterList .acts {
    float: right;
    font-size: 14px;
  }

  .videoList {
    padding-left: 50px;
  }

  .videoList p {
    float: left;
    font-size: 14px;
    margin: 10px 0;
    padding: 10px;
    height: 50px;
    line-height: 30px;
    width: 100%;
    border: 1px dotted #DDD;
  }
</style>

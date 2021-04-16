<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>

    <el-button type="primary" icon="el-icon-edit" @click="openChapterDialog">添加章节</el-button>


    <!-- 章节 -->
    <ul class="chapterList">
      <li v-for="chapter in chapterList" :key="chapter.id">
        <p>
          {{chapter.title}}
          <span class="acts">
            <el-button type="success" @click="openVideo(chapter.id)">新增</el-button>
            <el-button type="primary" @click="openEditDialog(chapter.id)">修改</el-button>
            <el-button type="danger" @click="deleteChapter(chapter.id)">删除</el-button>
          </span>
        </p>

        <!-- 视频 -->
        <ul class="chapterList videoList">
          <li v-for="video in chapter.children" :key="video.id">
            <p>
              {{video.title}}
              <span class="acts">
                <el-button size="mini" type="primary" icon="el-icon-edit" circle @click="openEditVideoDialog(video.id)"></el-button>
                <el-button size="mini" type="danger" icon="el-icon-delete" circle @click="deleteVideo(video.id)"></el-button>
              </span>
            </p>
          </li>
        </ul>
      </li>
    </ul>

    <div align="center">
      <el-button @click="previous">上一步</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="next">保存并下一步</el-button>
    </div>

    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" :title="dialogTitle">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title"/>
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
        </el-form-item>
      </el-form>
      <el-form slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
      </el-form>
    </el-dialog>

    <!-- 添加和修改小节表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" :title="dialogVideoTitle">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title"/>
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.isFree">
            <el-radio :label="true">免费</el-radio>
            <el-radio :label="false">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <!-- TODO -->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import chapter from "@/api/edu/chapter";
  import video from "@/api/edu/video";

  export default {
    name: "chapter",
    data() {
      return {
        saveBtnDisabled: false,
        dialogChapterFormVisible: false,
        dialogVideoFormVisible: false,
        dialogTitle: '',
        dialogVideoTitle:'',
        chapterList: [],
        courseId: '',
        chapter: {
          id: '',
          title: '',
          sort: 0
        },
        video: {
          title: '',
          sort: 0,
          isFree: 0,
          videoSourceId: ''
        }
      }
    },
    created() {
      if (this.$route.params && this.$route.params.id) {
        this.courseId = this.$route.params.id;
      }
      this.getChapterVideo();
    },
    methods: {
      /***************************************小节方法***************************************/
      // 点击弹出修改小节dialog
      openEditVideoDialog(videoId){
        this.dialogTitle = '修改小节';
        video.getVideoById(videoId)
          .then(response => {
            this.video = response.data;
            this.dialogVideoFormVisible = true;
          });
      },
      deleteVideo(videoId){
        this.$confirm('此操作将永久删除小节记录，是否继续？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 点击确定调用删除方法
          video.deleteVideoById(videoId)
            .then(response => {
              // 删除成功显示提示信息
              this.$message({
                type: 'success',
                message: '删除小节成功'
              });
              // 回到列表页面
              this.getChapterVideo();
            });
        });
      },
      // 添加小节按钮调用的方法
      openVideo(chapterId) {
        this.dialogVideoFormVisible = true;
        this.video.chapterId = chapterId;
        // 清空数据
        this.video.title = '';
        this.video.sort = 0;
        this.video.isFree = 0;
      },
      // 添加或修改小节
      saveOrUpdateVideo() {
        if(!this.video.id){
          this.addVideo();
        }else {
          this.updateVideo();
        }
      },
      // 添加小节方法
      addVideo() {
        // 设置课程id
        this.video.courseId = this.courseId;
        this.video.id = '';
        video.addVideo(this.video)
          .then(response => {
            // 关闭弹框
            this.dialogVideoFormVisible = false;
            // 新增成功显示提示信息
            this.$message({
              type: 'success',
              message: '添加小节成功'
            });
            // 回到列表页面
            this.getChapterVideo();
          });
      },
      updateVideo(){
        video.updateVideo(this.video)
          .then(response=>{
            // 关闭弹框
            this.dialogVideoFormVisible = false;
            // 显示提示信息
            this.$message({
              type: 'success',
              message: '更新小节成功'
            });
            // 更新成功清空videoId
            this.video.id = '';
            // 回到列表页面
            this.getChapterVideo();
          });
      },
      /***************************************章节方法***************************************/
      // 删除章节
      deleteChapter(chapterId) {
        this.$confirm('此操作将永久删除章节记录，是否继续？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 点击确定调用删除方法
          chapter.deleteChapter(chapterId)
            .then(response => {
              // 删除成功显示提示信息
              this.$message({
                type: 'success',
                message: '删除章节成功'
              });
              // 回到列表页面
              this.getChapterVideo();
            });
        });
      },
      // 点击编辑章节，根据id查询chapter，并弹出dialog框
      openEditDialog(chapterId) {
        this.dialogTitle = '修改章节';
        chapter.getChapterById(chapterId)
          .then(response => {
            this.chapter = response.data;
            this.dialogChapterFormVisible = true;
          });
      },
      // 弹出新增章节页面
      openChapterDialog() {
        this.dialogTitle = '添加章节';
        this.chapter.title = '';
        this.chapter.sort = 0;
        this.dialogChapterFormVisible = true;
      },

      saveOrUpdate() {
        if (!this.chapter.id) {
          this.addChapter();
        } else {
          this.updateChapter();
        }

      },
      // 添加章节
      addChapter() {
        // 设置courseId
        this.chapter.courseId = this.courseId;
        chapter.addChapter(this.chapter)
          .then(response => {
            // 关闭弹框
            this.dialogChapterFormVisible = false;
            // 显示提示消息
            this.$message({
              type: 'success',
              message: '新增章节成功'
            });
            // 重新请求数据
            this.getChapterVideo();
          });
      },
      // 更新章节
      updateChapter() {
        chapter.updateChapter(this.chapter)
          .then(response => {
            // 关闭弹框
            this.dialogChapterFormVisible = false;
            // 显示提示消息
            this.$message({
              type: 'success',
              message: '更新章节成功'
            });
            // 修改成功清空chapterId
            this.chapter.id = '';
            // 重新请求数据
            this.getChapterVideo();
          });
      },
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

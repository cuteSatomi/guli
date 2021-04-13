<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" :min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>

      <!-- 讲师头像 -->
      <el-form-item label="讲师头像">
        <!-- 头衔缩略图 -->
        <pan-thumb :image="teacher.avatar"/>
        <!-- 文件上传按钮 -->
        <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像
        </el-button>

        <!--
          v-show：是否显示上传组件
          :key：类似于id，如果一个页面多个图片上传控件，可以做区分
          :url：后台上传的url地址
          @close：关闭上传组件
          @crop-upload-success：上传成功后的回调
          <input type="file" name="file"/>
          -->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API+'/edu/oss/upload'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"/>
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import teacherApi from "@/api/edu/teacher";
  import ImageCropper from '@/components/ImageCropper';
  import PanThumb from '@/components/PanThumb';

  export default {
    name: "save",
    components: {
      ImageCropper,
      PanThumb
    },
    data() {
      return {
        teacher: {
          name: '',
          sort: 0,
          level: 1,
          career: '',
          intro: '',
          avatar: ''
        },
        imagecropperShow: false,        // 上传弹框组件是否显示
        imagecropperKey: 0,             // 上传组件key值
        BASE_API: process.env.BASE_API, // 获取dev.env.js里面地址

        saveBtnDisabled: false    // 保存按钮是否禁用
      }
    },
    created() {
      this.init();
    },
    watch: {
      // 每次监听到路由变化的时候，清空表单，即把teacher对象置为空
      $route(to, from) {
        this.init();
      }
    },
    methods: {
      // 关闭头像上传框
      close() {
        this.imagecropperShow = false;
        this.imagecropperKey = this.imagecropperKey + 1;
      },
      // 头像上传成功后回调的方法
      cropSuccess(data) {
        this.teacher.avatar = data;
        this.imagecropperShow = false;
        this.imagecropperKey = this.imagecropperKey + 1;
      },
      // 页面的初始化方法
      init() {
        // 假如路径中包含id，则说明是更新讲师，需要根据id查询出讲师的信息
        if (this.$route.params && this.$route.params.id) {
          const id = this.$route.params.id;
          this.getTeacherById(id);
        } else {
          this.teacher = {};
        }
      },
      // 根据id查询讲师
      getTeacherById(id) {
        teacherApi.getTeacherById(id)
          .then(response => {
            this.teacher = response.data;
          });
      },
      // 新增或更新讲师
      saveOrUpdate() {
        if (this.teacher.id) {
          // 当前teacher对象有id，则说明是更新
          this.updateTeacher();
        } else {
          this.saveTeacher();
        }
      },
      // 新增讲师
      saveTeacher() {
        teacherApi.addTeacher(this.teacher)
          .then(response => {
            this.$message({
              type: 'success',
              message: '新增成功'
            });
            // 路由跳转，回到列表页面
            this.$router.push({path: '/teacher/list'});
          });
      },
      // 更新讲师
      updateTeacher() {
        teacherApi.updateTeacher(this.teacher)
          .then(response => {
            this.$message({
              type: 'success',
              message: '更新成功'
            });
            // 路由跳转，回到列表页面
            this.$router.push({path: '/teacher/list'});
          });
      }
    }
  }
</script>

<style scoped>

</style>

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

      <!-- 讲师头像：TODO -->
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import teacherApi from "@/api/edu/teacher";

  export default {
    name: "save",
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

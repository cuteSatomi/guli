<template>
  <div class="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.title" placeholder="课程名称"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="courseQuery.status" clearable placeholder="课程状态">
          <el-option value="Normal" label="已发布"/>
          <el-option value="Draft" label="未发布"/>
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker v-model="courseQuery.begin" type="datetime" placeholder="选择开始时间"
                        value-format="yyyy-MM-dd HH:mm:ss" default-time="00:00:00"/>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="courseQuery.end" type="datetime" placeholder="选择截止时间"
                        value-format="yyyy-MM-dd HH:mm:ss" default-time="00:00:00"/>
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table :data="list" border fit highlight-current-row>
      <el-table-column label="序号" width="50" align="center">
        <template slot-scope="scope">
          {{ (current - 1) * size + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="title" label="课程名称" width="300"/>
      <el-table-column label="课程状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status==='Normal'?'已发布':'未发布' }}
        </template>
      </el-table-column>
      <el-table-column prop="lessonNum" label="课程课时" width="100"/>
      <el-table-column prop="price" label="课程价格" width="130"/>
      <el-table-column prop="buyCount" label="销售数量" width="100"/>
      <el-table-column prop="gmtCreate" label="添加时间" width="160"/>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程基本信息</el-button>
          </router-link>
          <router-link :to="'/course/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程大纲</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除课程
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination :current-page="current" :page-size="size" :total="total" background
                   style="padding: 30px 0; text-align: center;"
                   layout="prev, pager, next" @current-change="getList"/>
  </div>
</template>

<script>
  import course from "@/api/edu/course";

  export default {
    data() {
      return {
        list: [],         // 用于接受请求到的数据
        current: 1,       // 当前页
        size: 10,         // 每页显示条数
        total: 0,         // 总记录数
        courseQuery: {}  // 条件查询对象
      }
    },
    created() {
      this.getList();
    },
    methods: {
      // 条件分页查询课程列表
      getList(current = 1) {
        this.current = current;
        course.getCourseListPage(this.current, this.size, this.courseQuery)
          .then(response => {
            this.list = response.data.records;
            this.total = response.data.total;
          });
      },
      // 清空表单数据，并进行一次查询
      resetData() {
        this.courseQuery = {};
        this.getList();
      }
    }
  }
</script>

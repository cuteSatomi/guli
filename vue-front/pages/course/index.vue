<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部课程</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">课程类别</span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li :class="{current: subjectIdx == -1}">
                  <a @click="searchAll" title="全部" href="#">全部</a>
                </li>
                <li :class="{current: subjectIdx == index}"
                    v-for="(subject,index) in subjectList" :key="index">
                  <a @click="searchOne(index,subject.id)" :title="subject.title" href="#">{{subject.title}}</a>
                </li>
              </ul>
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14"></span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li :class="{current: subSubjectIdx == index}" v-for="(subSubject,index) in subSubjectList"
                    :key="index">
                  <a @click="searchTwo(index,subSubject.id)" :title="subSubject.title" href="#">{{subSubject.title}}</a>
                </li>
              </ul>
            </dd>
          </dl>
          <div class="clear"></div>
        </section>
        <div class="js-wrap">
          <section class="fl">
            <ol class="js-tap clearfix">
              <li :class="{'current bg-orange':buyCountSort!=''}">
                <a title="销量" href="javascript:void(0);" @click="searchBuyCount()">销量
                  <span :class="{hide:buyCountSort==''}">↓</span>
                </a>
              </li>
              <li :class="{'current bg-orange':gmtCreateSort!=''}">
                <a title="最新" href="javascript:void(0);" @click="searchGmtCreate()">最新
                  <span :class="{hide:gmtCreateSort==''}">↓</span>
                </a>
              </li>
              <li :class="{'current bg-orange':priceSort!=''}">
                <a title="价格" href="javascript:void(0);" @click="searchPrice()">价格&nbsp;
                  <span :class="{hide:priceSort==''}">↓</span>
                </a>
              </li>
            </ol>
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="data.total==0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article class="comm-course-list" v-if="data.total>0">
            <ul class="of" id="bna">
              <li v-for="course in data.records" :key="course.id">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :src="course.cover" class="img-responsive" :alt="course.title">
                    <div class="cc-mask">
                      <a :href="'/course/'+course.id" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :href="'/course/'+course.id" :title="course.title" class="course-title fsize18 c-333">{{course.title}}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span class="fr jgTag bg-green" v-if="Number(course.price)==0">
                      <i  class="c-fff fsize12 f-fA">免费</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">9634人学习</i>
                      |
                      <i class="c-999 f-fA">9634评论</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
        <!-- 公共分页 开始 -->
        <div>
          <div class="paging">
            <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
            <a :class="{undisable: !data.hasPrevious}"
               href="#"
               title="首页"
               @click.prevent="gotoPage(1)">首</a>
            <a :class="{undisable: !data.hasPrevious}"
               href="#"
               title="前一页"
               @click.prevent="gotoPage(data.current-1)">&lt;</a>
            <a v-for="page in data.pages"
               :key="page"
               :class="{current: data.current == page, undisable: data.current == page}"
               :title="'第'+page+'页'"
               href="#"
               @click.prevent="gotoPage(page)">{{ page }}</a>
            <a :class="{undisable: !data.hasNext}"
               href="#"
               title="后一页"
               @click.prevent="gotoPage(data.current+1)">&gt;</a>
            <a :class="{undisable: !data.hasNext}"
               href="#"
               title="末页"
               @click.prevent="gotoPage(data.pages)">末</a>
            <div class="clear"/>
          </div>
        </div>
        <!-- 公共分页 结束 -->
      </section>
    </section>
    <!-- /课程列表 结束 -->
  </div>
</template>
<script>
  import courseApi from "@/api/course";

  export default {
    data() {
      return {
        current: 1,
        frontCourseVO: {},
        data: {},             // 课程信息对象
        subjectIdx: -1,       // 一级分类index
        subjectList: [],      // 一级分类列表
        subSubjectList: [],   // 二级分类列表
        subSubjectIdx: -1,    // 二级分类index
        buyCountSort: '',
        gmtCreateSort: '',
        priceSort: ''
      }
    },
    created() {
      this.getCourseList();
      this.getAllSubjects();
    },
    methods: {
      // 根据销量排序
      searchBuyCount() {
        this.buyCountSort = '1';
        this.gmtCreateSort = '';
        this.priceSort = '';

        this.frontCourseVO.buyCountSort = this.buyCountSort;
        this.frontCourseVO.gmtCreateSort = this.gmtCreateSort;
        this.frontCourseVO.priceSort = this.priceSort;
        this.gotoPage(1);
      },
      // 根据发布时间排序
      searchGmtCreate(){
        this.buyCountSort = '';
        this.gmtCreateSort = '1';
        this.priceSort = '';

        this.frontCourseVO.buyCountSort = this.buyCountSort;
        this.frontCourseVO.gmtCreateSort = this.gmtCreateSort;
        this.frontCourseVO.priceSort = this.priceSort;
        this.gotoPage(1);
      },
      // 根据价格排序
      searchPrice(){
        this.buyCountSort = '';
        this.gmtCreateSort = '';
        this.priceSort = 'zzx';

        this.frontCourseVO.buyCountSort = this.buyCountSort;
        this.frontCourseVO.gmtCreateSort = this.gmtCreateSort;
        this.frontCourseVO.priceSort = this.priceSort;
        this.gotoPage(1);
      },
      // 点击全部查询全部课程
      searchAll() {
        // 将subjectIdx置为-1
        this.subjectIdx = -1;
        // 将subSubjectList清空
        this.subSubjectList = [];
        // 清空查询条件再查一次
        this.frontCourseVO = {};
        this.gotoPage(1);
      },
      // 点击一级分类显示相应的二级分类列表，并且会查询该分类下的所有课程
      searchOne(index, subjectId) {
        // 查询，将二级分类id置空
        this.frontCourseVO.subjectId = '';
        this.frontCourseVO.subjectParentId = subjectId;
        this.gotoPage(1);

        // 将二级分类的id置空
        this.subSubjectIdx = -1;
        this.subjectIdx = index;
        this.subSubjectList = this.subjectList[index].children;
      },
      // 点击二级分类，查询相应的课程
      searchTwo(index, subSubjectId) {
        // 查询
        this.frontCourseVO.subjectId = subSubjectId;
        this.gotoPage(1);

        this.subSubjectIdx = index;
      },
      // 查询课程列表
      getCourseList() {
        courseApi.getCourseList(this.current, 8, this.frontCourseVO)
          .then(response => {
            this.data = response.data.data;
          });
      },

      // 查询所有分类
      getAllSubjects() {
        courseApi.getAllSubjects()
          .then(response => {
            this.subjectList = response.data.data;
            console.log(response);
          });
      },

      // 分页切换
      gotoPage(page) {
        //if (page > this.data.pages) return;
        courseApi.getCourseList(page, 8, this.frontCourseVO)
          .then(response => {
            this.data = response.data.data;
          });
      }
    }
  };
</script>

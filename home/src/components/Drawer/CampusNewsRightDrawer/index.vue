<!--校园新闻右侧边抽屉-->
<template>
  <el-button type="danger" plain style="margin-left: 16px" @click="drawer = true">
    今日热点
  </el-button>

  <el-drawer v-model="drawer" title="I am the title" :with-header="false">
    <el-divider>今日热点</el-divider>
    <!--新闻无限列表-->
    <NewsList :news-list="newsList"/>
  </el-drawer>
</template>

<script lang="js" setup>
import {ref, onMounted} from 'vue'
// 新闻列表组件
import NewsList from './NewsList'
// 新闻API对象
import News from '@/api/news'

const drawer = ref(false)
const newsList = ref([])
// 获取新闻列表并赋值
onMounted(async () => {
  newsList.value = await News.getNewsList()
})
</script>

<style scoped>
@import "./index.less";
</style>
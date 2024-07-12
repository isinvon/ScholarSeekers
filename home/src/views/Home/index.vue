<template>
  <el-divider>失物招领统计报表</el-divider>
  <div class="home-container">
    <div class="chart-container">
      <div ref="chartRef" class="chart"></div>
    </div>
  <div class="container">
    <!--第一行-->
    <el-row :gutter="15">
      <el-col :span="16">
        <div class="grid-content ep-bg-purple"/>
        <!-- 轮播图 -->
        <Carousel class="home-element carousel" :carouse-list="carouseList"/>
      </el-col>
      <el-col :span="8">
        <div class="grid-content ep-bg-purple"/>
      </el-col>
    </el-row>
    <!--第二行-->
    <el-row :gutter="15">
      <el-col :span="16">
        <div class="grid-content ep-bg-purple"/>
        <!-- 最近事件 -->
        <RecentEvent class="home-element recent-event"/>
      </el-col>
      <el-col :span="8">
        <div class="grid-content ep-bg-purple"/>
      </el-col>
    </el-row>
    <!--第三行-->
    <el-row :gutter="15">
      <el-col :span="4">
        <div class="grid-content ep-bg-purple"/>
      </el-col>
      <el-col :span="16">
        <div class="grid-content ep-bg-purple"/>
      </el-col>
      <el-col :span="4">
        <div class="grid-content ep-bg-purple"/>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';

const chartRef = ref(null);
import './index.less'
// 轮播图
import Carousel from './components/Carousel'
// 最近事件
import RecentEvent from './components/RecentEvent'
import Home from "@/api/home";
import {onMounted, ref} from "vue";

const carouseList = ref([])

onMounted(
    async () => {
      carouseList.value = await Home.getCarouselList()
    }
)
</script>

<style lang="less" scoped>

</style>
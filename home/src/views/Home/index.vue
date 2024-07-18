<template>
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
        <!--柱状图-->
        <BarChart id="barChart" class="home-element bar-chart" style="height: 250px;width: 300px;"/>
      </el-col>
    </el-row>
    <!--第二行-->
    <el-row :gutter="15">
      <el-col :span="12">
        <div class="grid-content ep-bg-purple"/>
        <!-- 最近事件 -->
        <RecentEvent class="home-element recent-event"/>
      </el-col>
      <el-col :span="12">
        <div class="grid-content ep-bg-purple"/>
        <!--最近丢失-->
        <RecentLost class="home-element recent-lost" :table-data="recentLostData"/>
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
import './index.less'
// 柱状图
import BarChart from '@/components/Echart/BarChart'
// 轮播图
import Carousel from './components/Carousel'
// 最近事件
import RecentEvent from './components/RecentEvent'
// 访客统计
import RecentLost from "./components/RecentLost";
import Home from "@/api/home";
import {onMounted, ref} from "vue";

const carouseList = ref([])
const recentLostData = ref([])

onMounted(
    async () => {
      carouseList.value = await Home.getCarouselList()
      recentLostData.value = await Home.getRecentLost()
    }
)
</script>

<style lang="less" scoped>

</style>
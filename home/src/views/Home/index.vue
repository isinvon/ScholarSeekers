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
      </el-col>
      <el-col :span="8">
        <div class="grid-content ep-bg-purple"/>
      </el-col>
    </el-row>
    <!--第二行-->
    <el-row :gutter="15">
      <el-col :span="16">
        <div class="grid-content ep-bg-purple"/>
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
import Home from "@/api/home";
import {onMounted, ref} from "vue";

// 模拟数据
const imitateDate = [
  { category: '手机', count: 35 },
  { category: '钱包', count: 20 },
  { category: '钥匙', count: 15 },
  { category: '书籍', count: 25 },
];

onMounted(() => {
  const chartInstance = echarts.init(chartRef.value);
  const option = {
    tooltip: {
      trigger: 'item',
    },
    legend: {
      top: '5%',
      left: 'center',
    },
    series: [
      {
        name: '失物类别',
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2,
        },
        label: {
          show: false,
          position: 'center',
        },
        emphasis: {
          label: {
            show: true,
            fontSize: '30',
            fontWeight: 'bold',
          },
        },
        data: imitateDate.map(item => ({ value: item.count, name: item.category })),
      },
    ],
  };

  chartInstance.setOption(option);
  window.addEventListener('resize', () => {
    chartInstance.resize();
  });
});
</script>

<style lang="less" scoped>

</style>
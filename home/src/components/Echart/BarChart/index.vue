<template>
  <div ref="echartsRef" style="height: 400px; width: 500px"></div>
</template>
<script setup lang="js">
import { ref, onMounted } from "vue"
import echarts from "@/utils/echarts"
const echartsRef = ref()
onMounted(() => {
  const myChart = echarts.init(echartsRef.value)
  // 指定图表的配置项和数据
  const option = {
    title: {
      text: ""
    },
    tooltip: {
      trigger: "axis",
    },
    legend: {
      data: ["丢失"]
    },
    xAxis: {
      // name: "物品",
      type: "category",
      data: ["鞋子", "衣物", "数码设备", "皮包", "运动道具", "饰品"]
    },
    yAxis: {},
    series: [
      {
        name: "丢失",
        type: "bar",
        data: [5, 20, 36, 10, 10, 20]
      }
    ]
  }

  // 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option)
})
</script>


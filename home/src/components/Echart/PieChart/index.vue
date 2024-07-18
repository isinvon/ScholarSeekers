<!--饼图-->
<template>
  <div class="home-container">
    <div class="chart-container">
      <div ref="chartRef" class="chart"></div>
    </div>
  </div>
</template>

<script setup>
import * as echarts from 'echarts';
import { onMounted, ref } from 'vue';

const chartRef = ref(null);

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

<style lang="scss" scoped>
.home-container {
  display: flex;
  //flex-direction: column;
   //align-items: center;
  //justify-content: center;
  //height: 100vh;
  //padding: 20px;
}

.chart-container {
  width: 80%;
  max-width: 800px;
  //margin-top: 30px;
}

.chart {
  width: 100%;
  height: 400px;
}
</style>

<!-- 分类预览 -->
<script setup>
import {ref} from 'vue'
// 滚动认领记录表格
import LostAndFoundRecord from "./components/LostAndFoundRecord";
// 筛选栏
import SelectBar from "./components/SelectBar";
// 卡片列表
import CartList from "./components/List";


const filters = ref({
  search: '',
  category: null,
  tags: []
})

// 处理来自 SelectBar 的筛选条件变化
const handleFilterChange = (newFilters) => {
  filters.value = {...newFilters}
}
</script>

<template>
  <div>
    <el-divider style="margin-top: 0px">领取记录</el-divider>
    <!-- 滚动认领记录表格 -->
    <LostAndFoundRecord/>
    <!-- 换行 -->
    <br/>
    <!-- 筛选栏 -->
    <SelectBar @filter-change="handleFilterChange"/>
    <el-divider>已认领</el-divider>
    <!-- 卡片列表，根据filters过滤展示内容 -->
    <CartList :filters="filters"/>
  </div>
</template>

<style scoped lang="less">
</style>

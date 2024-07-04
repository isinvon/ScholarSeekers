<!-- 分类预览 -->
<script setup>
// 筛选栏
import SelectBar from './components/SelectBar'
// 卡片列表
import CartList from './components/List'
</script>

<template>
  <div>
    <!--筛选栏-->
    <SelectBar/>
    <!--分割线-->
    <el-divider/>
    <!--卡片列表-->
    <CartList/>
  </div>
</template>

<style scoped lang="scss"></style>

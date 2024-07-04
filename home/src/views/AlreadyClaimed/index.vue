<!-- 分类预览 -->
<script setup>
// 滚动认领记录表格
import LostAndFoundRecord from "./components/LostAndFoundRecord";
// 筛选栏
import SelectBar from "./components/SelectBar";
// 卡片列表
import CartList from "./components/List";
</script>

<template>
  <div>
    <el-divider style="margin-top: 0px">领取记录</el-divider>
    <!-- 滚动认领记录表格 -->
    <LostAndFoundRecord />
    <!-- 换行 -->
    <br />
    <!--筛选栏-->
    <SelectBar />
    <!--分割线-->
    <el-divider>已认领</el-divider>
    <!--卡片列表-->
    <CartList />
  </div>
</template>

<style scoped lang="scss"></style>

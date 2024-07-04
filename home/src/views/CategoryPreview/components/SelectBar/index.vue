<script setup>
import SearchBar from './components/SearchBar'
import SingleTreeSelect from "./components/SingleTreeSelect";
import MultiTreeSelect from './components/MultiTreeSelect'
</script>

<template>
  <!--用el-space调整内部组件间距-->
  <el-space wrap>
    <!--搜索框-->
    <SearchBar size=""/>
    <!--搜索按钮-->
    <!--<el-button type="primary" size="default">-->
    <!--  <i class="el-icon-search"></i>-->
    <!--  搜索-->
    <!--</el-button>-->
    <!--单选下拉框-->
    <SingleTreeSelect/>
    <!--多选下拉框-->
    <MultiTreeSelect/>
  </el-space>
</template>

<style scoped lang="scss">

</style>
<script setup>
import SearchBar from './components/SearchBar'
import SingleTreeSelect from "./components/SingleTreeSelect";
import MultiTreeSelect from './components/MultiTreeSelect'

// 搜索功能 ----开始----
import {ref} from 'vue'

const searchInput = ref('')
const selectedCategory = ref(null)
const selectedTags = ref([])

const handleSearch = () => {
  // 此处处理搜索逻辑，将搜索条件传递到父组件或状态管理
  console.log('搜索关键词:', searchInput.value, '分类:', selectedCategory.value, '标签:', selectedTags.value)
}
// 搜索功能 ----结束----
</script>

<template>
  <!--用el-space调整内部组件间距-->
  <el-space wrap>
    <!--搜索框-->
    <SearchBar size=""/>
    <!-- 搜索按钮 -->
    <el-button type="primary" size="default" @click="handleSearch">
      <i class="el-icon-search"></i> 搜索
    </el-button>
    <!--单选下拉框-->
    <SingleTreeSelect/>
    <!--多选下拉框-->
    <MultiTreeSelect/>
  </el-space>
</template>

<style scoped lang="scss">

</style>
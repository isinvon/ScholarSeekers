<!--<template>-->
<!--  <el-pagination-->
<!--    background-->
<!--    layout="prev, pager, next"-->
<!--    :total="total"-->
<!--    :page-size="pageSize"-->
<!--    :current-page="currentPage"-->
<!--    @current-change="handlePageChange"-->
<!--  ></el-pagination>-->
<!--</template>-->

<!--<script setup>-->
<!--import { ref, defineProps, defineEmits } from 'vue';-->

<!--const props = defineProps({-->
<!--  total: Number,-->
<!--  pageSize: Number,-->
<!--  currentPage: Number,-->
<!--});-->

<!--const emit = defineEmits(['page-change']);-->

<!--const handlePageChange = (val) => {-->
<!--  emit('page-change', val);-->
<!--};-->
<!--</script>-->

<!--<style scoped>
@import url('./index.less');-->
<!--/* 样式 */-->
<!--</style>-->
<template>
  <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :page-size="pageSize"
      :current-page="currentPage"
      @current-change="handlePageChange"
  />
</template>

<script setup>


const props = defineProps({
  total: Number,
  pageSize: Number,
  currentPage: Number,
});

const emit = defineEmits(['page-change']);

const handlePageChange = (val) => {
  emit('page-change', val);
};
</script>

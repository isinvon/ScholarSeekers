<template>
  <div>
    <!--反馈表格记录-->
    <FeedbackTable/>
    <!--<br>-->
    <!--分页-->
    <!--<MyPagination/>-->
  </div>
</template>


<script setup>

import FeedbackTable from './components/FeedbackTable'
import {ref} from "vue";
// import MyPagination from "@/components/MyPagination.vue";

</script>

<style lang="less" scoped>
@import url('./index.less');

</style>
<template>
  <div>
    <!--反馈表格记录-->
    <FeedbackTable/>

  </div>
</template>


<script setup>

import FeedbackTable from './components/FeedbackTable'
import {ref} from "vue";

</script>

<style lang="scss" scoped>
@import url('./index.less');

</style>
<!-- 走马灯/轮播图 -->
<template>
  <div class="block text-center">
    <el-carousel height="250px" motion-blur>
      <el-carousel-item v-for="(item, index) in props.carouseList" :key="item.id">
        <div>
          <!--<h3 class="small justify-center" text="2xl">{{ item }}</h3>-->
          <img :src="item.img" @click="UrlUtils.openUrl(item.url)" :alt="item.title"/>
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script lang="js" setup>

import UrlUtils from '@/utils/UrlUtils'

const props = defineProps({
  carouseList: {
    type: Array,
    default: () => []
  }
})

</script>

<style scoped>
@import url('./index.less');

</style>
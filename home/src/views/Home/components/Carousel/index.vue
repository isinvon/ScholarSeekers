<!-- 走马灯/轮播图 -->
<template>
  <div class="block text-center">
    <el-carousel :height="height" motion-blur>
      <el-carousel-item v-for="(item, index) in props.carouseList" :key="item.id">
        <div>
          <!--<h3 class="small justify-center" text="2xl">{{ item }}</h3>-->
          <img class="carousel-img" :src="item.img" @click="UrlUtils.openUrl(item.url)" :alt="item.title"/>
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
  },
  height: {
    type: String,
    default: '300px'
  }
})

</script>

<style lang="less" scoped>
@import url('./index.less');
</style>
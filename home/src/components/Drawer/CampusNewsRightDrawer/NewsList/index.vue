<!-- 新闻列表 -->
<template>
  <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto">
    <li
        v-for="(item, index) in newsList"
        :key="item.id"
        class="infinite-list-item"
    >
      <!--前三个新闻序号用红色标签-->
      <span v-if="index === 0 || index === 1|| index === 2" class="news-index">
          <el-tag type="danger">{{ index + 1 }}</el-tag>
      </span>
      <!--其他新闻序号用绿色标签-->
      <span v-else class="news-index">
        <el-tag type="success">{{ index + 1 }}</el-tag>
      </span>
      <div class="flex-grow"/>

      <!--新闻标题+链接新标签跳转-->
      <el-text class="w-150px mb-2" size="small" truncated>
        <el-link type="default" :href="item.link" target="_blank" class="news-title">
          <!--鼠标移入查看详情(气泡提示)-->
          <el-popover
              placement="top"
              width="200"
              trigger="hover"
              :content="item.title"
          >
            <template #reference>
              <el-link type="default" :href="item.link" target="_blank" class="news-title">
                {{ item.title}}
              </el-link>
            </template>
          </el-popover>
        </el-link>
      </el-text>

      <div class="flex-grow"/>
      <!--<img :src="item.img" :alt="item.title+'图片'" class="news-img">-->
    </li>
  </ul>
</template>


<script lang="js" setup>

import {ref} from 'vue'
import {defineProps} from 'vue';

const count = ref(0)
const load = () => {
  count.value += 2
}

const {newsList} = defineProps({
  newsList: {
    type: Array,
    required: true,
  }
})



</script>

<style lang="less" scoped>
@import url('./index.less');

</style>


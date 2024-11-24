<!-- 新闻列表 -->

<template>
  <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto">
    <li
        v-for="(item, index) in newsList"
        :key="item.id"
        class="infinite-list-item"
    >
      <span v-if="index < 3" class="news-index">
        <el-tag type="danger">{{ index + 1 }}</el-tag>
      </span>
      <span v-else class="news-index">
        <el-tag type="success">{{ index + 1 }}</el-tag>
      </span>

      <div class="flex-grow"/>

      <el-text class="w-150px mb-2" size="small" truncated>
        <el-link type="default" :href="item.link" target="_blank" class="news-title" tabindex="0">
          <el-popover placement="top" width="200" trigger="hover" :content="item.title">
            <template #reference>
              <el-link type="default" :href="item.link" target="_blank" class="news-title">
                {{ item.title }}
              </el-link>
            </template>
          </el-popover>
        </el-link>
      </el-text>

      <!--<p class="news-info">{{ item.date }} - 来源: {{ item.source }}</p>-->
      <!--分割线-->
      <el-divider direction="vertical"/>
      <img :src="item.img" width="80" height="40" alt="新闻图片" class="news-img"/>
    </li>

    <li v-if="loading" class="loading-item">
      <el-spinner size="20" />
      正在加载...
    </li>
  </ul>
</template>

<script lang="js" setup>
import { ref } from 'vue'

const count = ref(0)
const loading = ref(false)

const load = () => {
  if (loading.value) return
  loading.value = true
  setTimeout(() => {
    count.value += 2
    loading.value = false
  }, 500)
}

const { newsList } = defineProps({
  newsList: {
    type: Array,
    required: true,
  }
})
</script>

<style lang="less" scoped>


.infinite-list {
  padding: 10px;
  background-color: #f9f9f9;
}

.infinite-list-item {
  display: flex;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px solid #eaeaea;
}

.news-index {
  margin-right: 10px;
}

.news-title {
  font-weight: bold;
  color: #333;
  transition: color 0.3s;
}

.news-title:hover {
  color: #409eff;
}

.loading-item {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  color: #999;
}

.news-info {
  font-size: 12px;
  color: #888;
}
</style>

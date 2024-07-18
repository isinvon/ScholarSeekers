<!--评论列表组件-->
<template>
  <div class="comment-list">
    <comment-card v-for="comment in paginatedComments" :key="comment.id" :comment="comment"/>
  </div>
</template>

<script setup>
;
import CommentCard from '../CommentCard';
import {computed, ref} from "vue";

const props = defineProps({
  comments: {
    type: Object,
    required: true
  }
});
console.log("我是comment-list里的comments: ", props.comments)

const currentPage = ref(1);
const pageSize = ref(10);
const totalComments = computed(() => props.comments.length);

// 分页(分割)处理后的评论数据
const paginatedComments = computed(() => {
  // 将评论对象转换为数组
  const commentsArray = Object.values(props.comments);
  // 计算分页
  const start = (currentPage.value - 1) * pageSize.value; // 当前页的起始索引
  const end = start + pageSize.value; // 当前页的结束索引
  return commentsArray.slice(start, end); // 返回当前页的评论
});
</script>

<style scoped>
@import url('./index.less');

</style>

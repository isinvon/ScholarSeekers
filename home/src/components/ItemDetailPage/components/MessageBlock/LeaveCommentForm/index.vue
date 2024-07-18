<template>
  <el-form @submit.native.prevent="submit">
    <el-input
      type="textarea"
      v-model="content"
      placeholder="请输入您的评论..."
      :autosize="{ minRows: 4, maxRows: 8 }"
    ></el-input>
    <el-button type="primary" native-type="submit">提交评论</el-button>
  </el-form>
</template>

<script setup>
import './index.less'
import { ref } from 'vue';
import { useCommentStore } from '@/store/comment'; // 评论状态

const content = ref('');
const commentStore = useCommentStore();

const submit = () => {
  if (content.value.trim() !== '') {
    commentStore.addComment({ content: content.value });
    content.value = '';
  }
};
</script>

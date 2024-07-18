<!--留言块组件-->
<template>
  <div class="message-block">
    <!--评论列表-->
    <comment-list :comments="commentData"/>
    <!--提交评论表单-->
    <leave-comment-form @submit="addComment"/>
    <!--分页-->
    <pagination :total="totalComments" :page-size="pageSize" :current-page="currentPage"
                @page-change="handlePageChange"/>
  </div>
</template>

<script setup>
import {reactive, ref, computed} from "vue";
import CommentList from './CommentList';
import LeaveCommentForm from './LeaveCommentForm';
import Pagination from './Pagination';


const props = defineProps({
  // 评论接口
  commentData: {
    type: Object,
    required: true
  }
})

console.log("我是message-block里的commentData: ", props.commentData)

const comments = reactive([
  // 初始评论数据
]);

const currentPage = ref(1);
const pageSize = ref(10);
const totalComments = computed(() => comments.length);

const addComment = (newComment) => {
  comments.push(newComment);
};

const handlePageChange = (val) => {
  currentPage.value = val;
};
</script>

<style scoped>
@import url('./index.less');
.message-block {
  display: flex;
  flex-direction: column;
  align-items: center;
  /* 添加样式 */
}
</style>

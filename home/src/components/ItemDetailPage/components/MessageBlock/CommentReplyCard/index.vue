<!--回复卡片-->
<template>
  <div class="comment-replies">
    <comment-card-for-comment-reply-card  :reply="reply"/>
  </div>
</template>

<script setup>
import './index.less'
import CommentCardForCommentReplyCard from './CommentCardForCommentReplyCard';

defineOptions({
  name: 'CommentReplyCard',
})

const {reply} = defineProps({
  reply: Object,
});
console.log("我是comment-reply-card里的reply:", reply)
</script>

<style scoped>

</style>

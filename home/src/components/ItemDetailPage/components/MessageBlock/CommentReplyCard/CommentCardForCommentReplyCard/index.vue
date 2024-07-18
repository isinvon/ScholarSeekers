<!--供评论回复卡片使用的卡片组件-->
<template>
  <el-card class="comment-card" style="max-width: 480px">
    <!--卡片头部-->
    <template #header>
      <div class="comment-header">
        <el-avatar :size="32" :src="reply.author.avatar" shape="circle"></el-avatar>
        <div class="comment-author-info">
          <span class="author-name">{{ reply.author.name }}</span>
        </div>
        <!-- 用户头衔-->
        <div>
          <el-tag v-for="title in reply.author.titles" :key="title" type="light" size="small">{{ title }}</el-tag>
        </div>
        <!-- 操作按钮(点赞/踩)-->
        <div class="comment-actions">
          <!-- 点赞按钮-->
          <el-button type="text" size="small" @click="likeComment">
            <m-icon :icon="icon.heart" :size="iconSize"/>
            {{ reply.likes }}
          </el-button>
          <!-- 踩按钮-->
          <el-button type="text" size="small" @click="dislikeComment">
            <m-icon :icon="icon.brokenHeart" :size="iconSize"/>
            {{ reply.dislikes }}
          </el-button>
          <!-- 回复按钮-->
          <el-button type="text" size="small" @click="toggleReply">回复</el-button>
        </div>
      </div>
    </template>
    <!--卡片中部-->
    <template #default>
      <div class="comment-default">
        <!-- 评论内容(文字)-->
        <div class="comment-content">
          {{ reply.content }}
        </div>
        <!-- 评论图片-->
        <div class="comment-images">
          <el-image
              class="comment-images"
              v-for="image in reply.images"
              :key="image.url"
              :src="image.url"
              fit="cover"
              :preview-src-list="[image.url]"
          ></el-image>
        </div>
        <!-- 回复框-->
        <div class="comment-reply" v-if="showReply">
          <reply-form :comment="reply" @submit="submitReply"/>
        </div>
        <!-- 回复列表-->
        <!--<div class="comment-replies" v-if="reply.replies && reply.replies.length > 0">-->
        <!--  <comment-reply-card v-for="reply in reply.replies" :key="reply.id" :replies="reply"/>-->
        <!--</div>-->
      </div>
    </template>
  </el-card>
</template>

<script setup>

import ReplyForm from '../../ReplyForm';
import {ref} from 'vue';
// import CommentReplyCard from "../../CommentReplyCard";
// 图标枚举
import icon from "@/menuData/icon"
import MIcon from "@/components/MIcon.vue";

const props = defineProps({
  reply: Object,
  iconSize: {
    type: Number,
    default: 14
  }
});
console.log("我是comment-card-for-comment-card-reply里的reply:", props.reply)

// comment的数据结构示例:
// {
//   "id": "@id",
//   "author": {
//     "name": "@cname",
//     "avatar": "@image",
//     "titles": [
//       "@cname",
//       "@cname",
//       "@cname"
//     ]
//   },
//   "content": "@cparagraph",
//   "images": [
//     {
//       "url": "@image"
//     },
//     {
//       "url": "@image"
//     }
//   ],
//   "likes": "@integer(0, 100)",
//   "dislikes": "@integer(0, 10)",
//   "replies": [内容跟以上数据结构一样]

const showReply = ref(false);

const toggleReply = () => {
  showReply.value = !showReply.value;
};

const submitReply = (reply) => {
  // 假设有一个方法可以更新评论的回复数组
  // 这里应该调用 store 或者服务端的 API 来真正地添加回复
  console.log('Reply submitted:', reply);
};

const likeComment = () => {
  props.reply.likes++;
};

const dislikeComment = () => {
  props.reply.dislikes++;
};
</script>

<style lang="less" scoped>
@import url('./index.less');

</style>

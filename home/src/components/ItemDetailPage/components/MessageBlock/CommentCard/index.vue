<template>
  <el-card class="comment-card" style="max-width: 480px">
    <!--卡片头部-->
    <template #header>
      <div class="comment-header">
        <el-avatar :size="32" :src="comment.author.avatar" shape="circle"></el-avatar>
        <div class="comment-author-info">
          <span class="author-name">{{ comment.author.name }}</span>
        </div>
        <!-- 用户头衔-->
        <div>
          <el-tag v-for="title in comment.author.titles" :key="title" type="light" size="small">{{ title }}</el-tag>
        </div>
        <!-- 操作按钮(点赞/踩)-->
        <div class="comment-actions">
          <!-- 点赞按钮-->
          <el-button type="text" size="small" :icon="ThumbUpFilled" @click="likeComment">
            <m-icon :icon="icon.heart" :size="iconSize"/>
            {{ comment.dislikes }}
          </el-button>
          <!-- 踩按钮-->
          <el-button type="text" size="small" :icon="ThumbDownFilled" @click="dislikeComment">
            <m-icon :icon="icon.brokenHeart" :size="iconSize"/>
            {{ comment.dislikes }}
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
          {{ comment.content }}
        </div>
        <!-- 评论图片-->
        <div class="comment-images">
          <el-image
              class="comment-images"
              v-for="image in comment.images"
              :key="image.url"
              :src="image.url"
              fit="cover"
              :preview-src-list="[image.url]"
          ></el-image>
        </div>
        <!-- 回复框-->
        <div class="comment-reply" v-if="showReply">
          <reply-form :comment="comment" @submit="submitReply"/>
        </div>
        <!-- 回复列表-->
        <div class="comment-replies" v-if="comment.replies && comment.replies.length > 0">
          <comment-reply-card v-for="reply in comment.replies" :key="reply.id" :reply="reply"/>
        </div>
      </div>
    </template>
  </el-card>
</template>

<script setup>
import './index.less'
import ReplyForm from '../ReplyForm';
import {ref} from 'vue';
import CommentReplyCard from "../CommentReplyCard";
import icon from "@/menuData/icon";
import MIcon from "@/components/MIcon.vue";

const props = defineProps({
  comment: Object,
  iconSize: {
    type: Number,
    default: 14
  }
});

console.log("我是comment-cart里的comment:", props.comment)
console.log("我是comment-cart里的comment.replies:", props.comment.replies)

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
  props.comment.likes++;
};

const dislikeComment = () => {
  props.comment.dislikes++;
};
</script>

<style scoped>

</style>

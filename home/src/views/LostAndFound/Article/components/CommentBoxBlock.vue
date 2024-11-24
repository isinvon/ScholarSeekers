<!--评论框组件-->
<template>
  <div class="comment-box">
    <!--头部、中间、底部-->
    <div class="header">
      <!-- 文本输入框 -->
      <el-input
          type="textarea"
          v-model="comment"
          placeholder="写下你的评论..."
          :rows="3"
          class="comment-input"
      />
    </div>
    <div class="content">
    </div>
    <div class="footer">
      <!-- 表情按钮和表情选择器 -->
      <el-button @click="toggleEmojiPicker" class="emoji-button" type="text">
        <MIcon class="emoji-button-icon" :color="`#818283`" icon="ph:smiley-bold" :size="22"/>
        <transition name="fade">
          <EmojiPickComponent class="emoji-picker-container" v-if="showEmojiPicker" @emoji-selected="addEmoji"/>
        </transition>
      </el-button>
      <!--上传图片按钮-->
      <el-button class="upload-button" type="text">
        <el-upload
            ref="uploadRef"
            action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
            :auto-upload="false"
            :on-success="handleUploadSuccess"
            style="display: flex;align-items: center"
        >
          <MIcon class="upload-button-icon" :color="`#818283`" icon="ph:image-bold" :size="22"/>
        </el-upload>
      </el-button>

      <!--提交按钮推到最右边-->
      <div class="flex-line"/>
      <el-button class="submit-button" type="text" @click="submitComment">
        <!--提交-->
        <MIcon class="submit-button-icon" :color="`#818283`" icon="material-symbols:send-outline" :size="25"/>
      </el-button>
    </div>
  </div>
</template>

<script lang="js" setup>
import {ref} from 'vue';
import EmojiPickComponent from "../components/EmojiPickComponent.vue";
import MIcon from "@/components/MIcon.vue";

const comment = ref(''); // 评论内容
const showEmojiPicker = ref(false); // 是否显示表情选择器
const selectedImages = ref([]); // 已选图片列表

// 切换表情选择器显示状态
const toggleEmojiPicker = () => {
  showEmojiPicker.value = !showEmojiPicker.value;
};

// 添加表情到评论内容
const addEmoji = (emoji) => {
  comment.value += emoji;
  showEmojiPicker.value = false; // 选择后关闭表情选择器
};

// 图片上传成功处理
const handleUploadSuccess = (response, file) => {
  selectedImages.value.push(file);
};

// 提交评论
const submitComment = () => {
  // 提交逻辑，发送评论内容和图片
  console.log('评论内容:', comment.value);
  console.log('已上传图片:', selectedImages.value);
  // 清空输入框和图片列表
  comment.value = '';
  selectedImages.value = [];
};
</script>

<style lang="less" scoped>
.comment-box {
  gap: 10px;
}
.comment-input {
  width: 100%;
}

.footer {
  display: flex;
  justify-content: flex-start; // 左对齐
  align-items: center;
  padding-top: 5px;
}

// 淡入淡出过渡效果
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}

// 淡入淡出过渡效果初始状态
.fade-enter-from, .fade-leave-to {
  opacity: 0;
}

.emoji-picker-container {
  position: absolute;
  bottom: 50%;
  z-index: 2;
}

.submit-button {
  // 圆角按钮
  border-radius: 100px;
  padding: 0 10px;
  font-weight: lighter;
}

// 占位元素
.flex-line {
  flex: 1;
}
</style>

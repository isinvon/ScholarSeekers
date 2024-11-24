<template>
  <div class="emoji-picker">
    <div class="emoji-list">
      <span
          v-for="emoji in emojis"
          :key="emoji"
          class="emoji-item"
          @click="selectEmoji(emoji)">
        {{ emoji }}
      </span>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue';

// 定义表情列表
const emojis = ref(['👍', '🎉', '❤️', '😄', '😕', '👀', '🚀', '👎', '🤮','💔','😆','🤡','😨','😭']);

// 表情选择处理
const selectEmoji = (emoji) => {
  // console.log(`Selected emoji: ${emoji}`);
  // 在这里可以执行进一步的逻辑，如发送到服务器或更新状态
};
</script>

<style lang="less" scoped>
.emoji-picker {
  display: flex;
  flex-wrap: wrap;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 6px;
  background-color: #fff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.emoji-list {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.emoji-item {
  font-size: 20px; // 设置表情大小
  cursor: pointer; // 设置鼠标样式为手
  transition: transform 0.2s ease; // 添加鼠标悬浮在表情上的过渡效果
}

.emoji-item:hover {
  transform: scale(1.2); // 鼠标悬浮时表情放大大小
}
</style>

<template>
  <div class="issue-timeline">
    <!--时间线-->
    <el-timeline>
      <!--显示时间戳于卡片下方-->
      <!--<el-timeline-item-->
      <!--    v-for="(issue, index) in message"-->
      <!--    :key="index"-->
      <!--    :timestamp="issue.date"-->
      <!--    class="timeline-item"-->
      <!--&gt;-->
      <!--不显示时间戳于卡片下方-->
      <el-timeline-item
          v-for="(issue, index) in message"
          :key="index"
          class="timeline-item"
      >
        <!--卡片（头像和内容）-->
        <el-row class="issue-item">
          <el-col :span="24">
            <!-- 将时间线卡片放入聊天气泡中 -->
            <div class="issue-cart">
              <!--用户信息棒栏-->
              <div class="header">
                <!--头像-->
                <el-avatar
                    class="avatar"
                    :src="issue.avatar"
                    size="small"
                />
                <span class="nickname">{{ issue.user }}</span>
                <!--分割线-->
                <el-divider direction="vertical"/>
                <!--修改时间-->
                <span class="comment">{{ issue.comment }}</span>
                <!--分割线-->
                <el-divider direction="vertical"/>
                <!--时间-->
                <span class="date">{{ issue.date }}</span>
              </div>
            </div>
            <p class="detail">{{ issue.detail }}</p>
            <!--卡片底部-->
            <div class="item-footer">
              <!--表情按钮-->
              <el-button type="text" size="small" @click="toggleEmojiPicker">
                <MIcon class="select-emoji-button-icon" :color="`#818283`" icon="ph:smiley-bold" :size="22"/>
              </el-button>
              <!--表情框-->
              <transition name="scale-transition">
                <EmojiSimple v-if="isClickAddEmojiButton" @emoji-selected="addEmoji" class="emoji-picker-container"/>
              </transition>
              <!-- 已选择表情显示 -->
              <div v-if="Object.keys(issue.emojis).length > 0" class="selected-emoji">
                <span v-for="(count, emoji) in issue.emojis" :key="emoji" class="emoji-display">
                  {{ emoji }}{{ count }}
                </span>
              </div>
              <div style="display: flex;flex: 1"/>
              <!--评论按钮-->
              <el-button type="text" size="small" @click="">
                <MIcon class="comment-button-icon" :color="`#818283`" icon="mdi:comment-outline"
                       :size="22"/>
              </el-button>
            </div>
          </el-col>
        </el-row>
      </el-timeline-item>
    </el-timeline>
    <!-- 评论框 -->
    <CommentBoxBlock/>
  </div>
</template>

<script setup>
import {onBeforeUnmount, onMounted, ref} from 'vue';
import CommentBoxBlock from "../components/CommentBoxBlock.vue";
import MIcon from "@/components/MIcon.vue";
import EmojiSimple from "../components/EmojiSimple.vue";

// 是否点击了添加表情的状态
const isClickAddEmojiButton = ref(false);

// 外部点击关闭逻辑 - 点击其他区域关闭表情选择器
const handleClickOutside = (event) => {
  const emojiPicker = document.querySelector('.emoji-picker-container');
  if (emojiPicker && !emojiPicker.contains(event.target)) {
    isClickAddEmojiButton.value = false;
  }
};

// 组件挂载时添加事件监听器
onMounted(() => {
  document.addEventListener('click', handleClickOutside);
});

// 组件销毁前移除事件监听器
onBeforeUnmount(() => {
  document.removeEventListener('click', handleClickOutside);
});

// 表情选择的处理逻辑
const addEmoji = (emoji, index) => {
  const selectedEmoji = message.value[index].emojis.find(e => e.emoji === emoji);
  console.log(emoji, index)
  if (selectedEmoji) {
    selectedEmoji.count++;
  } else {
    message.value[index].emojis.push({emoji, count: 1});
  }
};

// 切换表情选择器的显示状态
const toggleEmojiPicker = (event) => {
  isClickAddEmojiButton.value = !isClickAddEmojiButton.value;
  event.stopPropagation();  // 阻止事件冒泡，避免触发 handleClickOutside
};

const message = ref([
  {
    user: 'yangzongzhuan',
    avatar: 'https://avatars.githubusercontent.com/u/5264189?v=4',
    comment: 'commented on Oct 18, 2019',
    date: 'Oct 18, 2019',
    detail: 'Owner\n请在issues里提问，方便统一处理。',
    emojis: {
      '👍': 1,
      '❤️': 111,
      '😄': 1,
      '🎉': 2,
      '🚀': 1,
    }
  },
  {
    user: 'fan-px',
    avatar: 'https://avatars.githubusercontent.com/u/13228255?v=4',
    comment: 'commented on Mar 11, 2020',
    date: 'Mar 11, 2020',
    detail: '若依的notice公告和通知在哪里通知的',
    emojis: {
      '👍': 12229,
      '❤️': 1312311,
      '😄': 1,
      '🎉': 2,
      '🚀': 1666,
    }
  },
  // 继续添加其他issue
  {
    user: 'fan-px',
    avatar: 'https://avatars.githubusercontent.com/u/13228255?v=4',
    comment: 'commented on Mar 11, 2020',
    date: 'Mar 11, 2020',
    detail: '若依的notice公告和通知在哪里通知的',
    emojis: {
      '👍': 12229,
      '❤️': 1312311,
      '😄': 1,
      '🎉': 2,
    },
  },
  {
    user: 'fan-px',
    avatar: 'https://avatars.githubusercontent.com/u/13228255?v=4',
    comment: 'commented on Mar 11, 2020',
    date: 'Mar 11, 2020',
    detail: '若依的notice公告和通知在哪里通知的',
    emojis: {
      '👍': 12229,
      '❤️': 1312311,
      '😄': 1,
      '🎉': 2,
    }
  },
  {
    user: 'fan-px',
    avatar: 'https://avatars.githubusercontent.com/u/13228255?v=4',
    comment: 'commented on Mar 11, 2020',
    date: 'Mar 11, 2020',
    detail: '若依的notice公告和通知在哪里通知的',
    emojis: {
      '👍': 12229,
      '❤️': 1312311,
      '😄': 1,
      '🎉': 2,
    }
  },
  {
    user: 'fan-px',
    avatar: 'https://avatars.githubusercontent.com/u/13228255?v=4',
    comment: 'commented on Mar 11, 2020',
    date: 'Mar 11, 2020',
    detail: '若依的notice公告和通知在哪里通知的',
    emojis: {
      '👍': 12229,
      '❤️': 1312311,
      '😄': 1,
      '🎉': 2,
    }
  },
  {
    user: 'fan-px',
    avatar: 'https://avatars.githubusercontent.com/u/13228255?v=4',
    comment: 'commented on Mar 11, 2020',
    date: 'Mar 11, 2020',
    detail: '若依的notice公告和通知在哪里通知的',
    emojis: {
      '👍': 12229,
      '❤️': 1312311,
      '😄': 1,
      '🎉': 2,
    }
  }
]);
</script>

<style lang="less" scoped>
@import url('./index.less');
/* 调整卡片的样式 */
.issue-item {
  margin-left: -70px; /* 使卡片覆盖时间线 */
  //padding-left: 40px;
  z-index: 2; /* 确保卡片在时间线之上 */
  border: 1px solid #dee2e6;
  //padding: 10px;
  border-radius: 10px;
  background-color: white;
  position: relative;
}

.issue-timeline {
  padding: 20px;
}

.issue-cart {
  display: flex;
  align-items: flex-start;
  max-width: 100%;
  padding: 10px;
  border-radius: 10px;
  //box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); // 阴影效果
  //background-color: #e5e5e5;
  background-color: #ebedf0;
}

.avatar {
  margin-right: 10px;
  cursor: pointer; // 设置鼠标样式为手
}

.header {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
  color: #555;
  // 垂直方向居中
  align-items: center;
}

// 文章/评论内容
.detail {
  margin: 10px;
  font-size: 14px;
  color: #333;
  // 左对齐
  text-align: left;
}

.item-footer {
  margin: 20px 10px 10px 10px; // 上外边距为 20 像素，右外边距为 10 像素，下外边距为 10 像素，左外边距为 10 像素，
  display: flex;
  justify-content: flex-start;
}

.select-emoji-button-icon {
  // 边框
  border: 1px solid #ccc;
  // 圆角
  border-radius: 1000px;
}

/* 动画过渡 */
// 添加过渡效果
.scale-transition-enter-active,
.scale-transition-leave-active {
  transition: transform 0.1s ease; // 0.1秒的过渡效果
}

// 初始状态，表情框缩小
.scale-transition-enter-from,
.scale-transition-leave-to {
  transform: scale(0);
}

// 进入时，放大表情框
.scale-transition-enter-to {
  transform: scale(1.1);
}

// 退出时，缩小表情框
.scale-transition-leave-from {
  transform: scale(1.1);
}

.emoji-picker-container {
  background-color: white; // 表情框背景颜色
  border: 1px solid #ccc; // 表情框边框，solid：表示边框的样式为实线。
  border-radius: 10px; // 表情框圆角
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15); // 表情框阴影效果
  position: absolute;
  left: 50px;
  z-index: 999;
}

// 已选择表情显示
.selected-emojis {
  display: flex;
  gap: 10px;
  margin-right: 10px;
  // 边框
}

// 表情显示样式
.emoji-display {
  font-size: 12px;
  font-weight: 500;
  color: #333;
  margin-right: 10px;
  padding: 5px;
  // 边框
  border: 1px solid #c7c7c7;
  // 圆角
  border-radius: 100px;
  cursor: pointer; // 设置鼠标样式为手
}

</style>

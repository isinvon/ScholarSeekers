<script setup>
import {onMounted, ref} from "vue";

const fullMessage = "欢迎来到 Sinvon!";
const visibleChars = ref(""); // 用于存储当前可见的字符
const visible = ref(true); // 控制欢迎信息的可见性

onMounted(() => {
  let index = 0; // 当前字符索引
  const typingSpeed = 50; // 打字速度（越大越慢）
  const eraseSpeed = 80; // 消失速度

  const typeCharacters = () => {
    if (index < fullMessage.length) {
      visibleChars.value += fullMessage[index++];
    } else {
      clearInterval(typingInterval); // 停止打字定时器
      setTimeout(() => {
        eraseCharacters(); // 打字完成后开始消失效果
      }, 1000); // 等待1秒后开始消失
    }
  };

  const eraseCharacters = () => {
    const eraseInterval = setInterval(() => {
      visibleChars.value = visibleChars.value.slice(0, -1);
      if (visibleChars.value.length === 0) {
        clearInterval(eraseInterval); // 停止消失定时器
        visible.value = false; // 信息不可见
        // 在这里不再重置 index，所以不会重新出现
      }
    }, eraseSpeed);
  };

  // 启动打字效果
  const typingInterval = setInterval(typeCharacters, typingSpeed);
});
</script>

<template>
  <h1 class="welcome-message" :style="{ opacity: visible ? 1 : 0 }">
    <span v-for="(char, index) in visibleChars" :key="index" class="char">{{ char }}</span>
  </h1> <!-- 欢迎信息 -->
</template>

<style scoped lang="less">
.welcome-message {
  font-size: 24px;
  color: white;
  text-align: center;
  margin-top: 60px;
  transition: opacity 0.5s; /* 添加过渡效果 */
  height: 36px; /* 设置固定高度，防止挤占布局 */
  overflow: hidden; /* 隐藏溢出内容 */
}

//打字机
.char {
  display: inline-block;
  font-size: 20px;
  //  下划线
  text-decoration: underline;
  //  渐变色
  //background: linear-gradient(to right, #272734, #6779f5);
  background: #4c5ab9;
  -webkit-background-clip: text;
  color: transparent;
}
</style>
<!--通知提示框-->
<template>
  <el-button plain type="text" @click="showNextNotification">🎁</el-button>
</template>

<script lang="js" setup>
import {h, ref, onMounted} from 'vue';
import {ElNotification} from 'element-plus';

// 定义接收 JSON 数组的 prop
const props = defineProps({
  messages: {
    type: Array,
    required: true,
  },
});

// 使用 ref 来存储当前显示消息的索引
const currentIndex = ref(0);

// 显示下一个通知的方法
const showNextNotification = () => {
  // 获取当前消息
  const currentMessage = props.messages[currentIndex.value];

  // 显示通知
  ElNotification({
    // title: '通知',
    message: h('i', {style: 'color: teal'}, currentMessage.text),
  });

  // 更新索引，循环遍历数组
  currentIndex.value = (currentIndex.value + 1) % props.messages.length;
};

</script>

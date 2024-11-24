<template>
  <div class="chat-container">
    <!-- 聊天气泡 -->
    <div
        v-for="(message, index) in messages"
        :key="index"
        :class="['issue-cart', message.isSent ? 'sent' : 'received']"
    >
      <!-- 接收消息的 -->
      <el-avatar
          v-if="!message.isSent"
          class="avatar"
          size="small"
          src="https://via.placeholder.com/40"
      />

      <!-- 发送消息的 -->
      <el-avatar
          v-if="message.isSent"
          class="avatar"
          size="small"
          src="https://via.placeholder.com/40"
      />

      <div>
        <!--昵称-->
        <span :class="['nickname', message.isSent ? 'sent' : 'received']">
          {{ message.nickname }}
        </span>

        <div class="bubble-content">
          <p style="text-align: start">{{ message.text }}</p>
        </div>

        <div v-if="timestampIsShow" class="timestamp-container">
          <span :class="['timestamp', message.isSent ? 'sent' : 'received']">{{ message.timestamp }}</span>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup>

const {props} = defineProps({
  // 消息列表
  messages: {
    type: Object,
    default: () => [
      {text: 'Hello!', nickname: '我', timestamp: '10:00 AM', isSent: false},
      {text: 'Hello World!', nickname: '我', timestamp: '10:00 AM', isSent: false},
      {
        text: 'Hi there!到婚纱给大家哈工大回家啊说的话尬死大发光火',
        nickname: '小明',
        timestamp: '10:01 AM',
        isSent: false
      },
      {
        text: 'How are you?wosdhadadhkjadgadhkjadgadhkjadgadhkjadgadhkjadgadhkjadgadhkjadgadhkjadgadhkjadgajhdgashjfgsahj',
        nickname: '我',
        timestamp: '10:02 AM',
        isSent: false
      },
      {
        text: 'I am good, thanks!sssssss多久啊还是规范化时光飞逝啊好几个大化时光飞逝啊好几个大化时光飞逝啊好几个大化时光飞逝啊好几个大化时光飞逝啊好几个大街上的感觉黄沙古渡就好',
        nickname: '小明',
        timestamp: '10:03 AM',
        isSent: false
      },
    ]
  },
  // 时间戳显示状态
  timestampIsShow: {
    type: Boolean,
    default: true
  }
});


import {ref} from 'vue';
//
// const messages = ref([
//   {text: 'Hello!', nickname: '我', timestamp: '10:00 AM', isSent: true},
//   {text: 'Hello World!', nickname: '我', timestamp: '10:00 AM', isSent: true},
//   {text: 'Hi there!到婚纱给大家哈工大回家啊说的话尬死大发光火', nickname: '小明', timestamp: '10:01 AM', isSent: false},
//   {
//     text: 'How are you?wosdhadadhkjadgadhkjadgadhkjadgadhkjadgadhkjadgadhkjadgadhkjadgadhkjadgadhkjadgajhdgashjfgsahj',
//     nickname: '我',
//     timestamp: '10:02 AM',
//     isSent: true
//   },
//   {
//     text: 'I am good, thanks!sssssss多久啊还是规范化时光飞逝啊好几个大化时光飞逝啊好几个大化时光飞逝啊好几个大化时光飞逝啊好几个大化时光飞逝啊好几个大街上的感觉黄沙古渡就好',
//     nickname: '小明',
//     timestamp: '10:03 AM',
//     isSent: false
//   },
// ]);
</script>

<style lang="less" scoped>

@SenderColor: #95ec69; // 微信绿
@ReceiverColor: #e5e5e5; // 微信白
@Shadow: rgba(0, 0, 0, 0.1); // 聊天气泡阴影

.chat-container {
  display: flex;
  flex-direction: column;
  gap: 30px;
  padding: 10px;
  max-width: 400px;
  margin: 0 auto;
}

// 聊天气泡样式
.issue-cart {
  display: flex;
  align-items: flex-start;
  max-width: 70%;

  .avatar {
    flex-shrink: 0;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-left: 12px;
    margin-right: 12px;
  }
}

// 发送消息的整个容器的样式
.sent {
  align-self: flex-end;
  flex-direction: row-reverse;
}

// 接收消息的整个容器的样式
.received {
  align-self: flex-start;
}

// 全体聊天气泡内容样式
.bubble-content {
  padding-left: 10px;
  padding-right: 10px;
  border-radius: 15px;
  position: relative;
  max-width: 100%;
  box-shadow: 0 2px 4px @Shadow;
  word-break: break-word;
  display: flex;
  flex-direction: column;
}

.nickname {
  font-size: 12px;
  color: #999;

}

// 发送消息的昵称靠右
.sent .nickname {
  text-align: right;
  display: block;
  margin-right: 10px;
  margin-bottom: 5px;
}

// 接收消息的昵称靠左
.received .nickname {
  text-align: left;
  display: block;
  margin-left: 10px;
  margin-bottom: 5px;
}

// 发送消息的样式
.sent .bubble-content {
  background-color: @SenderColor; // 使用微信绿
}

// 接收消息的样式
.received .bubble-content {
  background-color: @ReceiverColor; // 使用微信白
}

// 时间戳容器样式
.timestamp-container {
  display: flex;
  justify-content: flex-end; // 默认右对齐
  margin-top: 5px;
}


// 发送消息的时间戳对齐方式
.sent .timestamp-container {
  justify-content: flex-end; // 右对齐
}

// 接收消息的时间戳对齐方式
.received .timestamp-container {
  justify-content: flex-start; // 左对齐
}

// 时间戳样式
.timestamp {
  font-size: 12px;
  color: #999;
}

// 箭头样式
.bubble-content::before {
  content: '';
  position: absolute;
  width: 0;
  height: 0;
  border-style: solid;
}

// 发送消息的箭头样式
.sent .bubble-content::before {
  border-width: 10px 0 10px 10px; // 箭头宽度为10px
  border-color: transparent transparent transparent @SenderColor; // 使用微信绿
  top: 5px; // 向上偏移5px
  right: -6px; // 向右偏移6px
}

// 接收消息的箭头样式
.received .bubble-content::before {
  border-width: 10px 10px 10px 0; // 箭头宽度为10px
  border-color: transparent @ReceiverColor transparent transparent; // 使用微信白
  top: 5px; // 向上偏移5px
  left: -6px; // 向左偏移6px
}

</style>

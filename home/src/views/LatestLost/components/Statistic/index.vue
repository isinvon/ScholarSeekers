<template>
  <el-row>
    <el-col :span="6">
      <el-statistic title="今日新增" :value="todayLostValueTransition" />
    </el-col>
    <el-col :span="6">
      <el-statistic title="待认领数" :value="unclaimValueTransition" />
    </el-col>
    <el-col :span="6">
      <el-statistic title="认领成功数" :value="claimSuccessValueTransition" />
    </el-col>
    <!--<el-col :span="6">-->
    <!--  <el-statistic title="认领成功数" :value="outputValue" />-->
    <!--</el-col>-->
    <el-col :span="6">
      <el-statistic title="留言数量" :value="messageValueTransition">
        <template #suffix>
          <el-icon style="vertical-align: -0.125em">
            <ChatLineRound />
          </el-icon>
        </template>
      </el-statistic>
    </el-col>
  </el-row>
</template>

<script lang="js" setup>
import { ref } from 'vue'
import { useTransition } from '@vueuse/core'
import { ChatLineRound, Male } from '@element-plus/icons-vue'

const props = defineProps({
  // 今日新增
  todayLostValue: {
    type: Number,
    default: 12,
  },
  // 待认领
  unclaimValue: {
    type: Number,
    default: 53,
  },
  // 认领成功
  claimSuccessValue: {
    type: Number,
    default: 243,
  },
  // 留言数量
  messageValue: {
    type: Number,
    default: 3123,
  },
})

const todayLostValue = ref(0)
const unclaimValue = ref(0)
const claimSuccessValue = ref(0)
const messageValue = ref(0)

// 返回动画特效后的数字
// 今日新增(动画后数字)
const todayLostValueTransition = useTransition(todayLostValue, {
  // 数字的动画持续时间
  duration: 1000,
})
// 未认领(动画后数字)
const unclaimValueTransition = useTransition(unclaimValue, {
  // 数字的动画持续时间
  duration: 1000,
})
// 已认领(动画后数字)
const claimSuccessValueTransition = useTransition(claimSuccessValue, {
  // 数字的动画持续时间
  duration: 1000,
})
// 留言(动画后数字)
const messageValueTransition = useTransition(messageValue, {
  // 数字的动画持续时间
  duration: 1000,
})

todayLostValue.value = props.todayLostValue
unclaimValue.value = props.unclaimValue
claimSuccessValue.value = props.claimSuccessValue
messageValue.value = props.messageValue
</script>

<style scoped>
.el-col {
  text-align: center;
}
</style>

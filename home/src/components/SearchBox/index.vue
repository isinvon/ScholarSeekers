<!--漂亮的搜索框组件-->
<template>
  <div class="input-container">
    <input
        type="text"
        id="i-name"
        name="i-name"
        :value="modelValue"
        aria-labelledby="label-name"
        @input="updateValue"
        :style="{ borderRadius: computedBorderRadius }"
    />
    <label class="label" for="i-name" id="label-name">
      <div class="text">{{ placeholder }}</div>
    </label>
  </div>
</template>

<script lang="js" setup>
// 我们可以通过如下代码 监听 input 的输入行为，把输入内容 同步到 input value 属性即可
import {ref, onMounted, computed} from 'vue';

// 使用 ref 来声明响应式数据
const value = ref('');

// 使用 defineProps 和 defineEmits 处理 v-model
const props = defineProps({
  // 父组件传递过来的 v-model 绑定的值
  modelValue: {
    type: String,
    required: true
  },
  // 提示文字
  placeholder: {
    type: String,
    default: '搜索'
  },
  // 圆角
  borderRadius: {
    type: [Number, String],
    default: '4px' // 默认圆角大小
  }
});

// 暴露 updateValue 方法给父组件使用
const emit = defineEmits(['update:modelValue']);

// 更新父组件的 v-model
const updateValue = (event) => {
  emit('update:modelValue', event.target.value);
};

// 动态计算 borderRadius，可以接受数字或字符串
const computedBorderRadius = computed(() => {
  return typeof props.borderRadius === 'number' ? `${props.borderRadius}px` : props.borderRadius;
});

// 使用 onMounted 确保 DOM 元素完全渲染后再操作
onMounted(() => {
  const inputEle = document.getElementById('i-name');
  if (inputEle) {
    inputEle.addEventListener('input', () => {
      inputEle.setAttribute('value', inputEle.value);
    });
  } else {
    console.error('Input element not found.');
  }
});
</script>

<style lang="less" scoped>
/*1. 把 label 放到 input 里面*/
.input-container {
  position: relative;
}

input {
  height: 48px;
  width: 280px;
  border: 1px solid #c0c0c0;
  border-radius: 4px;
  box-sizing: border-box;
  padding: 16px;
}

.label {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 16px;
  display: flex;
  align-items: center;
  /* 取消 label 的鼠标行为 */
  pointer-events: none;
}
/*2. 改变 label 颜色*/
input:focus {
  outline: none;
  border: 2px solid blue;
}
input:focus + .label .text {
  color: blue;
}

/*3. 增加动画*/
.label .text {
  transition: all 0.15s ease-out;
  color: grey;
}

input:focus + .label .text,
:not(input[value='']) + .label .text {
  font-size: 12px;
  transform: translate(0, -150%);
  background-color: white;
  padding-left: 4px;
  padding-right: 4px;
}
</style>

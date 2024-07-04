<template>
  <!--<div class="flex gap-4 mb-4 items-center">-->
  <el-input
      v-model="input"
      style="width: 240px"
      :size="props.size"
      placeholder="输入关键词搜索"
      :suffix-icon="Search"
  />
  <!--</div>-->
</template>

<script lang="js" setup>
import {ref} from 'vue'
import {Search} from '@element-plus/icons-vue'

// 定义Props类型
const props = defineProps({
  /**
   * @type {'small'|'default'|'large'} size的枚举值
   * @default 'default'
   */
  size: {
    type: String,
    default: 'default',
    // 注意：在`<script setup>`中，直接使用函数进行验证，JSDoc辅助IDE提示
    validator: (value) => ['large', 'default', 'small'].includes(value),
  },
});

const input = ref('')
</script>

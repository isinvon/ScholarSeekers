<!--圆角边框组件-->
<template>
  <div
      class="radius"
      :style="{
          borderRadius: radiusGroup.type
            ? `var(--el-border-radius-${radiusGroup.type})`
            : '',
        }"
  />
</template>

<script lang="js" setup>
import {ref} from 'vue'

defineOptions({
  name: 'BorderRadiusComponent',
})
const radiusGroup = ref(
    {
      name: 'Round Radius',
      type: 'round',
    },
)
// const radiusGroup = ref([
//   // {
//   //   name: 'No Radius',
//   //   type: '',
//   // },
//   // {
//   //   name: 'Small Radius',
//   //   type: 'small',
//   // },
//   // {
//   //   name: 'Large Radius',
//   //   type: 'base',
//   // },
//   {
//     name: 'Round Radius',
//     type: 'round',
//   },
// ])

const getValue = (type) => {
  const getCssVarValue = (prefix, type) =>
      getComputedStyle(document.documentElement).getPropertyValue(
          `--el-${prefix}-${type}`
      )
  return getCssVarValue('border-radius', type)
}
</script>
<style scoped>
@import url('./index.less');
.demo-radius .title {
  color: var(--el-text-color-regular);
  font-size: 18px;
  margin: 10px 0;
}

.demo-radius .value {
  color: var(--el-text-color-primary);
  font-size: 16px;
  margin: 10px 0;
}

.demo-radius .radius {
  height: 40px;
  width: 70%;
  border: 1px solid var(--el-border-color);
  border-radius: 0;
  margin-top: 20px;
}
</style>

<script setup lang="js">
import {Icon} from "@iconify/vue";

defineOptions({
  name: "MIcon",
});
const props = defineProps({
  icon: {
    type: [String],
    default: "",
  },
  size: {
    typer: [String, Number],
    default: 32,
  },
  color: {
    type: String,
    default: "",
  },
});
</script>

<template>
  <div class="rounded-full flex items-center">
    <icon :icon="icon" class="icon-size" :width="size" :height="size"/>
  </div>
</template>

<style lang="less" scoped>
.icon-size {
@apply shadow-sm rounded-full;
  color: v-bind(color);
  object-fit: fill;
}
</style>

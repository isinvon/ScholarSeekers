<!--翻转卡片 - 原版-->
<script setup>
defineOptions({ name: 'FlipCard' })
const props = defineProps({
  // 正面文本
  frontText: {
    type: String,
    default: 'Front'
  },
  frontDetail: {
    type: String,
    default: 'F-detail'
  },
  // 反面文本
  backText: {
    type: String,
    default: 'Back'
  },
  // 反面详情
  backDetail: {
    type: String,
    default: 'B-detail'
  }
})
</script>

<template>
  <div class="flip-card">
    <div class="flip-card-inner">
      <!--正面-->
      <div class="flip-card-front">
        <h2>{{ frontText }}</h2>
        <p>{{frontDetail}}</p>
      </div>
      <!--反面-->
      <div class="flip-card-back">
        <h2>{{ backText }}</h2>
        <p>{{ backDetail }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">
.flip-card {
  background-color: transparent;
  perspective: 1000px;
  margin: 10px;
}

.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.6s;
  transform-style: preserve-3d;
}

.flip-card:hover .flip-card-inner {
  transform: rotateY(180deg);
}

.flip-card-front,
.flip-card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.flip-card-front {
  //background-color: #bbb;
  //color: black; // 字体颜色
  background: linear-gradient(to right, #36be81, #65ad59, #a1865a, #e586ca, burlywood);
  color: white;

  //color: transparent;
  //background: linear-gradient(to right, #a135a1, #ff5e2b, #ffa500, #ffd700, burlywood);
  //-webkit-background-clip: text; // 作用是将背景裁剪为文本的形状
}

.flip-card-back {
  background-color: #2980b9;
  color: white;
  transform: rotateY(180deg);
}
</style>

<!--翻转卡片 - 📦高复用性封装版本 ( 未完善，有BUG，请修复好后再使用) -->
<!--<script lang="js" setup>-->
<!--defineOptions({ name: 'FlipCard' })-->
<!--const props = defineProps({-->
<!--  // 前面和背面卡片的背景颜色-->
<!--  frontBackgroundColor: {-->
<!--    type: String,-->
<!--    default: '#bbb'-->
<!--  },-->
<!--  backBackgroundColor: {-->
<!--    type: String,-->
<!--    default: '#2980b9'-->
<!--  },-->
<!--  // 前后卡片的文字颜色-->
<!--  frontTextColor: {-->
<!--    type: String,-->
<!--    default: '#000'-->
<!--  },-->
<!--  backTextColor: {-->
<!--    type: String,-->
<!--    default: '#fff'-->
<!--  },-->
<!--  // 翻转卡片的宽度和高度-->
<!--  width: {-->
<!--    type: [Number, String],-->
<!--    default: 300-->
<!--  },-->
<!--  height: {-->
<!--    type: [Number, String],-->
<!--    default: 200-->
<!--  },-->
<!--  // 卡片的圆角和阴影-->
<!--  borderRadius: {-->
<!--    type: String,-->
<!--    default: '10px'-->
<!--  },-->
<!--  boxShadow: {-->
<!--    type: String,-->
<!--    default: '0 4px 8px rgba(0, 0, 0, 0.2)'-->
<!--  },-->
<!--  // 是否启用翻转效果-->
<!--  enableFlip: {-->
<!--    type: Boolean,-->
<!--    default: true-->
<!--  },-->
<!--  // 默认的前面和背面文本-->
<!--  frontText: {-->
<!--    type: String,-->
<!--    default: 'Front Side'-->
<!--  },-->
<!--  backText: {-->
<!--    type: String,-->
<!--    default: 'Back Side'-->
<!--  },-->
<!--  backDetail: {-->
<!--    type: String,-->
<!--    default: 'Some details here'-->
<!--  }-->
<!--})-->
<!--</script>-->

<!--<template>-->
<!--  <div class="flip-card" :style="{ width: width + 'px', height: height + 'px' }">-->
<!--    <div class="flip-card-inner" :class="{ 'flip-enabled': enableFlip }">-->
<!--      <div-->
<!--          class="flip-card-front"-->
<!--          :style="{-->
<!--          backgroundColor: frontBackgroundColor,-->
<!--          color: frontTextColor,-->
<!--          borderRadius: borderRadius,-->
<!--          boxShadow: boxShadow-->
<!--        }"-->
<!--      >-->
<!--        <slot name="front">-->
<!--          <h2>{{ frontText }}</h2>-->
<!--        </slot>-->
<!--      </div>-->
<!--      <div-->
<!--          class="flip-card-back"-->
<!--          :style="{-->
<!--          backgroundColor: backBackgroundColor,-->
<!--          color: backTextColor,-->
<!--          borderRadius: borderRadius,-->
<!--          boxShadow: boxShadow-->
<!--        }"-->
<!--      >-->
<!--        <slot name="back">-->
<!--          <h2>{{ backText }}</h2>-->
<!--          <p>{{ backDetail }}</p>-->
<!--        </slot>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<style scoped lang="less">-->
<!--.flip-card {-->
<!--  background-color: transparent;-->
<!--  perspective: 1000px;-->
<!--  margin: 40px;-->
<!--}-->

<!--.flip-card-inner {-->
<!--  position: relative;-->
<!--  width: 100%;-->
<!--  height: 100%;-->
<!--  text-align: center;-->
<!--  transition: transform 0.6s;-->
<!--  transform-style: preserve-3d;-->
<!--}-->

<!--.flip-enabled:hover .flip-card-inner {-->
<!--  transform: rotateY(180deg);-->
<!--}-->

<!--.flip-card-front,-->
<!--.flip-card-back {-->
<!--  position: absolute;-->
<!--  width: 100%;-->
<!--  height: 100%;-->
<!--  backface-visibility: hidden;-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  justify-content: center;-->
<!--}-->

<!--.flip-card-back {-->
<!--  transform: rotateY(180deg);-->
<!--}-->
<!--</style>-->

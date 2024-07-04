<!-- 卡片 -->
<template>
  <div class="item-card">
    <!-- 1. 丢失物品图片 -->
    <div class="image-container">
      <img :src="itemImageUrl" style="border-radius: 10px;" alt="Lost Item" v-if="itemImageUrl" />
      <span v-else>No Image</span>
    </div>

    <!-- 2. 丢失物品描述 -->
    <div class="description">
      <p>{{ itemDescription }}</p>
    </div>

    <!-- 3. 物品丢失的地址 -->
    <div class="location">
      <el-tag type="info">Lost at:</el-tag>
      <span>{{ lostLocation }}</span>
    </div>

    <!-- 4. 捡到者的联系方式 -->
    <div class="contact">
      <el-tag type="success">Contact:</el-tag>
      <span>{{ finderContact }}</span>
    </div>
  </div>
</template>

<script setup>
import { ElTag } from "element-plus";
import { defineProps } from "vue";

defineProps({
  itemImageUrl: {
    type: String,
    default: "https://picsum.photos/200/200",
  },
  itemDescription: {
    type: String,
    required: true,
    default: "No Description",
  },
  lostLocation: {
    type: String,
    required: true,
    default: "No Location",
  },
  finderContact: {
    type: String,
    required: true,
    default: "No Contact",
  },
});
</script>

<style scoped>
.item-card {
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 16px;
  margin-bottom: 16px;
}

.image-container {
  text-align: center;
  margin-bottom: 10px;
}

.image-container img {
  max-width: 100%;
  height: auto;
}

.description p {
  margin: 0;
}

.location,
.contact {
  margin-top: 8px;
}
</style>

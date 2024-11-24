<!-- 卡片 -->
<!-- ItemCard.vue -->
<template>
  <div class="item-cards">
    <div class="item-card" v-for="(item, index) in items" :key="index" @click="handleClick(item)">
      <!-- 1. 丢失物品图片，保持自适应宽高 -->
      <div class="image-container" v-if="item.itemImageUrl">
        <el-image
            :src="item.itemImageUrl"
            fit="cover"
            style="border-radius: 10px; width: 100%; height: 200px;"
            preview-src-list="[item.itemImageUrl]"
            :loading="imageLoading"
            @load="imageLoading = false"
            alt="Lost Item"
        >
          <template #placeholder>
            <el-skeleton :rows="1" style="width: 100%; height: 200px;"/>
          </template>
        </el-image>
      </div>
      <div v-else class="no-image">No Image</div>

      <!-- 2. 丢失物品描述 -->
      <div class="description">
        <p>{{ item.itemDescription }}</p>
      </div>

      <!-- 3. 物品丢失的地址 -->
      <div class="location">
        <el-tag type="info">丢失在:</el-tag>
        <span>{{ item.lostLocation }}</span>
      </div>

      <!-- 4. 捡到者的联系方式 -->
      <div class="contact">
        <el-tag type="info">联系方式:</el-tag>
        <span>{{ item.finderContact }}</span>
      </div>

      <!-- 5. 添加时间戳 -->
      <div class="timestamp">
        <el-tag type="info">丢失于:</el-tag>
        <span>{{ item.lostDate }}</span>
      </div>

      <!-- 6. 状态标签 -->
      <div class="status">
        <el-tag :type="item.isClaimed ? 'success' : 'danger'">{{ item.isClaimed ? '已认领' : '未认领' }}</el-tag>
        <!-- 新增物品类型 -->
        <el-tag class="item-type" type="primary">{{ item.itemType }}</el-tag>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import {ElTag, ElImage, ElSkeleton} from "element-plus";


const props = defineProps({
  /**
   * 丢失物品列表
   * @type {Array}
   * @default []
   * @example
   * [
   *   {
   *     itemImageUrl: "https://picsum.photos/200/200",
   *     itemDescription: "一个丢失的垃圾袋",
   *     lostLocation: "二号食堂水果店",
   *     finderContact: "138888888888",
   *     lostDate: "2024年9月2号",
   *     itemType: "其他物品",
   *     isClaimed: true,
   *   }
   * ]
   */
  items: {
    type: Array,
    required: true,
    default: () => ([
      {
        itemImageUrl: "https://picsum.photos/200/200",
        itemDescription: "一个丢失的垃圾袋",
        lostLocation: "二号食堂水果店",
        finderContact: "138888888888",
        lostDate: "2024年9月2号",
        itemType: "其他物品",
        isClaimed: true,
      }
    ]),
  }
});

const imageLoading = ref(true);

// 点击卡片跳转详情页
const handleClick = (item) => {
  alert('跳转到物品详情页: ' + item.itemDescription);
};
</script>

<style lang="less" scoped>
.item-card {
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 20px;
  margin-bottom: 16px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  cursor: pointer;
  background-color: #fff;
  text-align: left; /* 默认内容左对齐 */
}

.item-card:hover {
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
  transform: translateY(-5px);
}


/* 图片容器保持自适应宽高 */
.image-container {
  display: flex;
  justify-content: center;
  margin-bottom: 12px;
}

.no-image {
  display: flex;
  justify-content: center; /* 图片占位符居中 */
  align-items: center;
  height: 200px;
  background-color: #f0f0f0;
  color: #aaa;
  border-radius: 10px;
}

.description p {
  margin: 0 0 8px;
  font-weight: bold;
}

.location,
.contact,
.timestamp,
.status {
  margin-top: 8px;
}

.el-tag {
  margin-right: 8px;
}

.item-card img {
  max-width: 100%;
  height: auto;
  border-radius: 10px;
}

@media (max-width: 768px) {
  .item-card {
    padding: 16px;
  }
}
</style>

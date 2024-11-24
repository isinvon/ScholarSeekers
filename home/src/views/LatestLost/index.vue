<!-- 最新丢失 -->
<script setup>
import ItemCart from "@/components/ItemCard";
import MyPagination from "@/components/MyPagination.vue";
// 数据统计
import Statistic from './components/Statistic'
import RandomUtils from "@/utils/RandomUtils";
import MockRandomUtils from "../../../mock/utils/MockRandomUtils";
import {onMounted, ref} from "vue";

// 模拟请求数据
const items = ref([]); // 使用 ref 创建响应式数据
const itemsLoaded = ref(false); // 加载状态

async function generateItems() {
  for (let i = 0; i < 20; i++) {
    items.value.push({
      itemImageUrl: RandomUtils.getRandomGrayBlurImageBySizeAndBlur(200, 200, 8),
      itemDescription: MockRandomUtils.getRandomChineseParagraph().slice(0, 10),
      lostLocation: MockRandomUtils.getRandomAddress(),
      finderContact: MockRandomUtils.getRandomMobile(),
      lostDate: MockRandomUtils.getRandomDateTime(),
      itemType: MockRandomUtils.getRandomChineseWordByLen(2),
      isClaimed: RandomUtils.getRandomBoolean()
    });

    // 每次生成一个 item 后等待 1 秒
    await new Promise(resolve => setTimeout(resolve, 50));
  }

  itemsLoaded.value = true; // 数据加载完成
}

// 在组件挂载后调用生成数据函数
onMounted(() => {
  generateItems();
});
</script>

<template>
  <div>
    <Statistic/>
    <!--分割线-->
    <el-divider>New</el-divider>
    <!--多栏布局-->
    <el-row :gutter="20">
      <!-- 遍历 items 数组，每个卡片占用6个span，24 / 6 = 每行4个卡片 -->
      <el-col v-for="(item, index) in items" :key="index" :span="6">
        <!-- 物品卡片，传递单个 item 数据 -->
        <ItemCart :item="item" class="item-cart"/>
      </el-col>
    </el-row>

    <!--分页-->
    <MyPagination/>
  </div>
</template>

<style scoped lang="less">
.item-cart:hover {
  //时间过渡
  transition: all 0.4s ease;
}

// 卡片所在"分栏"布局
.el-row {
}

.el-row:last-child {
}

.el-col {
}

.grid-content {
}

// 侧边栏布局
.side-menu {
  //display: flex;
  height: 100%;
}
</style>

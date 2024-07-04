<!-- 最新丢失 -->
<script setup>
import ItemCart from "@/components/ItemCard";
import MyPagination from "@/components/MyPagination.vue";
</script>

<template>
  <div>
    <!--多栏布局-->
    <el-row :gutter="20">
      <el-col :span="6" v-for="item in 20" :key="item">
        <div class="grid-content ep-bg-purple" />
        <!-- 物品卡片 -->
        <ItemCart class="item-cart" />
      </el-col>
    </el-row>
    <!--分页-->
    <MyPagination />
  </div>
</template>

<style scoped lang="scss">


.item-cart:hover {
  //边缘阴影
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.2);
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

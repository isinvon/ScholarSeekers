<script setup>
import HeaderMenu from "@/layout/HeaderMenu/index.vue";
import SideMenu from "@/layout/SideMenu/index.vue";
import BackTopButton from "@/components/BackTopButton.vue";
</script>

<template>
  <div class="common-layout">
    <el-container class="common-container">
      <!-- 左边 -->
      <el-aside width="200px" class="common-aside">
        <SideMenu class="side-menu" />
      </el-aside>
      <!-- 右边 -->
      <el-container class="common-container-right">
        <!-- 右-头部 -->
        <el-header class="common-header">
          <!--TODO 显示丢失物品的分类, 地址就用文字搜索吧, 物品类型用下拉框或者联级选择器-->
          <!--顶部菜单栏-->
          <HeaderMenu />
        </el-header>
        <!-- 右-主体 -->
        <el-main class="common-main">
          <!--动态视图-->
          <router-view></router-view>
          <!--返回顶部-->
          <BackTopButton style="right: 10px" />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped lang="scss">

</style>
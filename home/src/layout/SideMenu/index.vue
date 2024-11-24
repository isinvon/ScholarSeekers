<template>
  <el-row class="tac">
    <!--调整占据的空间为24/24-->
    <el-col :span="24">
      <!--项目标题 - logo -->
      <!--<h3 class="mb-2" style="color: #207585">Glut失物招领</h3>-->
      <!--反转卡片 - Logo -->
      <FlipCard class="flip-card" :front-text="'寻物系统'" :front-detail="''" :back-text="'By Sinvon'" :back-detail="''"/>
      <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
      >

        <el-menu-item class="list-item" v-for="item in sideMenu" :key="item.title" :index="item.id">
          <el-icon>
            <component :is="item.icon"/>
          </el-icon>
          <!--<span>{{ item.title}}</span>-->
          <router-link :to="`${item.path}`">{{ item.title }}</router-link>
        </el-menu-item>
      </el-menu>

    </el-col>
  </el-row>
</template>

<script lang="js" setup>

import {sideMenu} from "@/menuData/sideMenu";
import FlipCard from "@/components/FlipCard/index.vue";

const handleOpen = (key, keyPath) => {
  // console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
  // console.log(key, keyPath)
}
</script>

<style lang="less" scoped>
@import url('./index.less');
</style>

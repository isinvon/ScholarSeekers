<template>
  <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      :ellipsis="false"
      @select="handleSelect"
  >
    <el-menu-item index="0">
      <img
          style="width: auto; height: 30px"
          :src="LogoUrl"
          alt="Element logo"
      />
    </el-menu-item>

    <div class="flex-grow"/>
    <!--校园新闻热榜-->
    <div style="margin-right: 10px;margin-top: 12px">
      <CampusNewsRightDrawer/>
    </div>
    
    <el-menu-item v-for="item in headerMenu" :key="item.title" :index="item.id">
      <router-link :to="`${item.path}`">
        {{ item.title }}
      </router-link>
    </el-menu-item>

    <el-menu-item index="99">
      <router-link to="/about">
        <span>关于我们</span>
      </router-link>
    </el-menu-item>

    <el-menu-item index="100">
      <router-link to="/login">
        <span>登录</span>
      </router-link>
    </el-menu-item>
    <!--<el-sub-menu index="2">-->
    <!--  <template #title>Workspace</template>-->
    <!--  <el-menu-item index="2-1">item one</el-menu-item>-->
    <!--  <el-menu-item index="2-2">item two</el-menu-item>-->
    <!--  <el-menu-item index="2-3">item three</el-menu-item>-->
    <!--  <el-sub-menu index="2-4">-->
    <!--    <template #title>item four</template>-->
    <!--    <el-menu-item index="2-4-1">item one</el-menu-item>-->
    <!--    <el-menu-item index="2-4-2">item two</el-menu-item>-->
    <!--    <el-menu-item index="2-4-3">item three</el-menu-item>-->
    <!--  </el-sub-menu>-->
    <!--</el-sub-menu>-->
  </el-menu>
</template>

<script lang="js" setup>
import './index.less'
import {ref, defineOptions} from 'vue'
// Logo
import LogoUrl from '@/assets/logo.png'
import {headerMenu} from "@/menuData/headerMenu";
import CampusNewsRightDrawer from '@/components/Drawer/CampusNewsRightDrawer'

defineOptions({
  name: 'HeaderMenu'
})

const activeIndex = ref('1')
const handleSelect = (key, keyPath) => {
  console.log(key, keyPath)
}
</script>

<style lang="less" scoped>

</style>

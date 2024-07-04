<template>
  <el-row class="tac">
    <el-col :span="12">
      <h4 class="mb-2" style="color: #207585">Glut失物招领</h4>
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

const handleOpen = (key, keyPath) => {
  console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
  console.log(key, keyPath)
}
</script>

<style lang="scss" scoped>
.el-row {
  // width: 200%;
}

.el-col {
  display: inline-table;
}
</style>

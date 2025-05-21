<template>
  <div class="personal-center-container">
    <!-- 添加返回按钮 -->
    <el-button
      v-if="$route.name !== 'Profile'"
      type="text"
      class="back-btn"
      @click="router.go(-1)"
      v-show="$route.name !== 'Profile'"
    >
      <el-icon><ArrowLeft /></el-icon> 返回
    </el-button>

    <el-button
      type="primary"
      @click="router.push({ name: 'EditProfile' })"
      v-show="$route.name !== 'EditProfile'"
      icon="Edit"
    >
      编辑资料
    </el-button>

    <router-view @refresh="handleRefresh" />
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { ArrowLeft } from "@element-plus/icons-vue"; // 添加图标

const router = useRouter();
const refreshKey = ref(0);

const handleRefresh = () => {
  refreshKey.value++;
  router.push({ name: "Profile" });
};
</script>

<style lang="less" scoped>
.personal-center-container {
  position: relative; /* 为绝对定位按钮准备 */
  max-width: 1200px;
  margin: 20px auto;
  padding: 40px; /* 增加内边距 */
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.06); /* 更柔和的阴影 */

  .back-btn {
    position: absolute;
    left: 20px;
    top: 20px;
    font-size: 14px;
    color: #666;

    &:hover {
      color: #409eff;
    }
  }
}
</style>

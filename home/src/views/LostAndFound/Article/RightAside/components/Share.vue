<!--社交平台分享-->
<template>
  <div class="social-icons">
    <el-tooltip content="QQ" placement="top">
      <el-button type="text">
        <MIcon icon="ant-design:qq-circle-filled" size="20" />
      </el-button>
    </el-tooltip>
    <el-tooltip content="WeChat" placement="top">
      <el-button type="text">
        <MIcon icon="ant-design:wechat-filled" size="20" />
      </el-button>
    </el-tooltip>
    <el-tooltip content="Weibo" placement="top">
      <el-button type="text">
        <MIcon icon="ant-design:weibo-circle-filled" size="20" />
      </el-button>
    </el-tooltip>
    <el-tooltip content="Twitter" placement="top">
      <el-button type="text">
        <MIcon icon="ant-design:twitter-circle-filled" size="20" />
      </el-button>
    </el-tooltip>
    <el-tooltip content="Reddit" placement="top">
      <el-button type="text">
        <MIcon icon="ant-design:reddit-circle-filled" size="20" />
      </el-button>
    </el-tooltip>
    <el-tooltip content="LinkedIn" placement="top">
      <el-button type="text">
        <MIcon icon="ant-design:linkedin-filled" size="20" />
      </el-button>
    </el-tooltip>
    <el-tooltip content="GitHub" placement="top">
      <el-button type="text">
        <MIcon icon="ant-design:github-filled" size="20" />
      </el-button>
    </el-tooltip>
    <el-tooltip content="Facebook" placement="top">
      <el-button type="text">
        <MIcon icon="ant-design:facebook-filled" size="20" />
      </el-button>
    </el-tooltip>
  </div>
</template>

<script setup>
import MIcon from "@/components/MIcon.vue";
</script>

<style scoped lang="less">

</style>

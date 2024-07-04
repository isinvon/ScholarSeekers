<template>
  <div class="about-page">
    <!--返回首页-->
    <el-row justify="center" class="header">
      <el-col :span="20">
        <el-button type="info" icon="el-icon-back" @click="goBack">返回首页</el-button>
      </el-col>
    </el-row>


    <!-- 页眉 -->
    <el-row justify="center" class="header">
      <el-col :span="20">
        <el-divider>关于我们</el-divider>
        <!--<h1>关于我们</h1>-->
      </el-col>
    </el-row>

    <!-- 公司简介 -->
    <el-row justify="center" class="section">
      <el-col :span="18">
        <el-card shadow="always">
          <h2>公司简介</h2>
          <p>
            我们的公司成立于2000年，是一家专注于技术创新与服务的领先企业。我们致力于为全球用户提供高效、可靠的解决方案，涵盖软件开发、云计算服务、人工智能等多个领域。通过不断探索与实践，我们已帮助成千上万的企业实现了数字化转型。
          </p>
        </el-card>
      </el-col>
    </el-row>

    <!-- 团队介绍 -->
    <el-row justify="center" class="section">
      <el-col :span="20">
        <el-card shadow="always">
          <h2>团队介绍</h2>
          <el-row gutter="20">
            <el-col :span="8" v-for="(member, index) in teamMembers" :key="index">
              <el-card shadow="hover">
                <img :src="member.avatar" alt="团队成员" class="team-avatar"/>
                <p class="team-name">{{ member.name }}</p>
                <p class="team-role">{{ member.role }}</p>
              </el-card>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 使命愿景 -->
    <el-row justify="center" class="section">
      <el-col :span="18">
        <el-card shadow="always">
          <h2>使命与愿景</h2>
          <p>
            我们的使命是通过技术的力量，推动社会进步，让生活更便捷、更美好。我们展望未来，希望成为全球最受信赖的技术创新伙伴，持续引领行业变革，共创数字时代的新篇章。
          </p>
        </el-card>
      </el-col>
    </el-row>

    <!-- 联系方式 -->
    <el-row justify="center" class="section contact-section">
      <el-col :span="16">
        <el-card shadow="always">
          <h2>联系我们</h2>
          <el-descriptions title="" :column="2" border>
            <el-descriptions-item label="地址">
              城市名, 街道名, 门牌号
            </el-descriptions-item>
            <el-descriptions-item label="电话">
              +86-123-4567-890
            </el-descriptions-item>
            <el-descriptions-item label="邮箱">
              info@example.com
            </el-descriptions-item>
            <el-descriptions-item label="网站">
              <a href="http://www.example.com" target="_blank">www.example.com</a>
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import {ref} from 'vue';

// 示例团队成员数据
const teamMembers = ref([
  {
    name: '张三',
    role: '首席执行官',
    avatar: 'https://picsum.photos/200/200', // 替换为真实路径
  },
  {
    name: '李四',
    role: '技术总监',
    avatar: 'https://picsum.photos/200/200', // 替换为真实路径
  },
  {
    name: '王五',
    role: '牛马',
    avatar: 'https://picsum.photos/200/200', // 替换为真实路径
  },
  // 更多成员...
]);

// 返回首页
const goBack = () => {
  window.history.back();
};
</script>

<style scoped>
.about-page {
  padding: 50px;
}

.header h1 {
  text-align: center;
  margin-bottom: 50px;
}

.section {
  margin-top: 30px;
}

.team-avatar {
  width: 100%;
  height: auto;
  border-radius: 50%;
  object-fit: cover;
}

.team-name {
  text-align: center;
  margin-top: 10px;
}

.team-role {
  text-align: center;
  color: #606266;
  font-size: 14px;
}

.contact-section {
  margin-top: 50px;
}
</style>

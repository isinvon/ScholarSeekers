<!--关于我们-->
<template>
  <div class="about-page">
    <!--<el-button type="success" size="small" icon="el-icon-back" round @click="RouteUtils.goBackToPreviousPage" class="back-button">返回首页</el-button>-->
    <el-button
        type="success"
        size="large"
        icon="el-icon-back"
        @click="RouteUtils.goToHomePage"
        class="back-button"
        @mouseover="showButton"
        @mouseleave="hideButton"
        :class="{ 'button-expanded': isButtonExpanded }"
    >
      返回首页
    </el-button>

    <!-- 页头区域 -->
    <section class="hero-section">
      <el-row justify="center">
        <el-col :span="20">
          <h1>关于我们</h1>
          <p class="hero-text">技术创新与服务的领先企业，致力于推动社会进步</p>
        </el-col>
      </el-row>
    </section>

    <!-- 公司简介 -->
    <section class="company-section section">
      <el-row justify="center">
        <el-col :span="20">
          <el-card shadow="hover" class="card">
            <h2>公司简介</h2>
            <p class="company-description">
              我们的公司成立于2000年，是一家专注于技术创新与服务的领先企业。我们致力于为全球用户提供高效、可靠的解决方案，涵盖软件开发、云计算服务、人工智能等多个领域。
            </p>
            <!--<el-button round type="primary" @click="learnMore">了解更多</el-button>-->
          </el-card>
        </el-col>
      </el-row>
    </section>


    <!-- 团队介绍 -->
    <section class="team-section section">
      <el-row justify="center">
        <el-col :span="20">
          <!--<h2>团队介绍</h2>-->
          <el-row gutter="24">
            <el-col :span="6" v-for="(member, index) in teamMembers" :key="index">
              <!--人物卡片-->
              <PeopleCard class="team-card" :img="member.avatar" :img-alt='团队成员' :title="member.name"
                          :subtitle="member.role" :content="member.description"/>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </section>

    <!-- 使命与愿景 -->
    <section class="mission-section section">
      <el-row justify="center">
        <el-col :span="20">
          <el-card shadow="always" class="card">
            <h2>使命与愿景</h2>
            <p>
              我们的使命是通过技术的力量，推动社会进步，让生活更便捷、更美好。展望未来，我们希望成为全球最受信赖的技术创新伙伴，持续引领行业变革。
            </p>
          </el-card>
        </el-col>
      </el-row>
    </section>

    <!-- 联系方式 -->
    <section class="contact-section section">
      <el-row justify="center">
        <el-col :span="20">
          <el-card shadow="always" class="card">
            <h2>联系我们</h2>
            <el-descriptions :column="2" border>
              <el-descriptions-item label="地址">
                北京市，科技大道88号
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
    </section>


    <!--里程碑展示-->
    <section class="milestone-section section">
      <el-row :gutter="24" justify="center">
        <el-col :span="22">
          <!-- 时间轴 -->
          <el-timeline>
            <el-timeline-item
                v-for="(milestone, index) in milestones"
                :key="index"
                :timestamp="milestone.date"
                class="timeline-item"
            >
              {{ milestone.event }}
            </el-timeline-item>
          </el-timeline>
        </el-col>
      </el-row>
    </section>
  </div>
</template>

<script lang="js" setup>
import RouteUtils from "@/utils/RouteUtils";
import PeopleCard from "@/components/PeopleCard/index.vue";
import {ref} from "vue";
import RandomUtils from "@/utils/RandomUtils";

// 示例团队成员数据
const teamMembers = ref([
  {
    name: '张三',
    role: '首席执行官',
    avatar: RandomUtils.getRandomAvatar(),
    description: '我是一个充满激情的CTO，我擅长使用 cutting-edge 技术解决complex problems。',
  },
  {
    name: '李四',
    role: '技术总监',
    avatar: RandomUtils.getRandomAvatar(),
    description: '我是一个有远见的技术经理，我擅长指导团队，并帮助团队实现技术目标。',
  },
  {
    name: '王五',
    role: 'CTO',
    avatar: RandomUtils.getRandomAvatar(),
    description: '我是一个有远见的技术经理，我擅长指导团队，并帮助团队实现技术目标。',
  },
  {
    name: '赵六',
    role: '前端工程师',
    avatar: RandomUtils.getRandomAvatar(),
    description: '我是一个有远见的技术经理，我擅长指导团队，并帮助团队实现技术目标。',
  },

]);

// 公司历程
const milestones = ref([
  {date: '2000-01', event: '公司成立'},
  {date: '2005-05', event: '获得行业内重要奖项'},
  {date: '2010-09', event: '技术创新突破'},
  {date: '2020-01', event: '全球业务扩展'},
]);

// 了解更多按钮点击事件
const learnMore = () => {
  alert('更多公司信息');
};

// 控制按钮是否展开
const isButtonExpanded = ref(false);
// 显示按钮
const showButton = () => {
  isButtonExpanded.value = true;
};
// 隐藏按钮
const hideButton = () => {
  isButtonExpanded.value = false;
};

</script>

<style lang="less" scoped>
// 返回按钮
.back-button {
  font-weight: bold;
  font-size: 18px;
  text-align: center;
  background-color: #a74ee3;
  color: white;
  padding: 10px 20px;
  position: fixed;
  top: 400px;
  left: 0;
  width: 100px;
  transform: translateX(-53%); // 移动到屏幕左侧（负数就是向右边弹开）
  transition: transform 0.3s ease;
  z-index: 1;
}

// 返回按钮 - 展开时样式
.button-expanded {
  transform: translateX(-10px); // 向左移动10px
}


.about-page {
  padding: 15px;
  color: #333;
  background: linear-gradient(135deg, #b793bb, #b2d0e3, #a0c9bb);
}

.hero-section {
  background: linear-gradient(135deg, #42b983, #2189f5, #6bdeb6);
  box-shadow: 2px 4px 10px rgba(16, 11, 11, 0.2);
  padding: 40px 0;
  color: white;
  position: relative;
  border-radius: 12px;
}

.hero-section h1 {
  font-size: 36px;
  margin-bottom: 10px;
}

.hero-text {
  font-size: 16px;
  margin: 0;
}

.section {
  margin: 40px 0px;
}

.card {
  border-radius: 16px;
}

.team-card {
  text-align: center;
  border-radius: 16px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  border: 4px solid #abcdf1;
}

.team-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.el-link {
  font-size: 20px;
  color: #409eff;
  margin-right: 10px;
}

.mission-section {
}


.milestone-section .el-timeline-item__timestamp {
  color: #409eff;
}

.contact-section {

}

.contact-section .el-descriptions__item {
  border-radius: 16px;
  padding: 20px;
  background-color: #fff;
}


// 时间轴 ---------- 开始 ---------

.milestone-section {
  padding: 20px;
}

.timeline-item {
  text-align: left; /* 使内容靠左对齐 */
}

.el-timeline-item__timestamp {
  text-align: left; /* 使时间戳靠左对齐（如果需要） */
}

.el-timeline-item__content {
  text-align: left; /* 使内容区域靠左对齐 */
}

.el-timeline-item__tail {
  border-color: #dcdfe6; /* 可以根据需要调整时间轴的颜色 */
}

// 时间轴 ---------- 结束 ---------
</style>


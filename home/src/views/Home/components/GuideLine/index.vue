<!--失物招领问题指南-->
<template>
  <el-container class="guideline-container">
    <!--<el-header>-->
    <!--</el-header>-->
    <el-main>
      <!--此处gutter就是两个元素的间隔-->
      <el-row :gutter="15">
        <el-col :span="12">
          <el-card class="faq">
            <h3 class="faq-title">常见问题解答</h3>
            <el-input v-model="searchQuery" placeholder="搜索问题..." class="search-input"/>
            <el-collapse>
              <el-collapse-item v-for="(item, index) in filteredFaqs" :key="index" :name="index.toString()">
                <template #title>
                  <el-tag type="info">Q</el-tag>
                  <!--间隔-->
                  <span style="margin: 0 5px;"></span>
                  <span class="faq-question">{{ item.question }}</span>
                </template>
                <p class="faq-answer">{{ item.answer }}</p>
              </el-collapse-item>
            </el-collapse>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card class="operation-guide">
            <h3 class="operation-list-title">操作说明</h3>
            <ul class="operation-list">
              <li v-for="(step, index) in operationSteps" :key="index">
                <el-tag type="info">{{ index + 1 }}</el-tag>
                <!--间隔-->
                <span style="margin: 0 5px;"></span>
                {{ step }}
              </li>
            </ul>
          </el-card>
        </el-col>
      </el-row>


      <el-card class="success-stories">
        <h3>找回成功案例</h3>
        <div class="scrolling-panel" ref="scrollPanel">
          <el-timeline>
            <el-timeline-item
                v-for="(story, index) in successStories"
                :key="index"
                :timestamp="new Date().toLocaleString()"
            >
              {{ story }}
            </el-timeline-item>
          </el-timeline>
        </div>
      </el-card>

      <el-card class="statistics">
        <h3>失物招领统计</h3>
        <p>近期失物数量：{{ recentLostCount }}</p>
        <p>认领成功率：{{ claimSuccessRate }}%</p>
      </el-card>

      <el-card class="contact-info">
        <h3>联系方式</h3>
        <p>电话：123-456-7890</p>
        <p>邮箱：support@campuslostandfound.com</p>
      </el-card>
    </el-main>
  </el-container>
</template>

<script lang="js" setup>
import {ref, computed, onMounted} from 'vue';

const operationSteps = [
  "点击“发布失物”按钮，填写物品信息。",
  "上传清晰的物品照片，确保描述准确。",
  "提交后，您可以在主页查看您的失物信息。",
  "如有人找到您的物品，您将收到通知。",
  "认领后，您可以在“已认领”页面查看认领信息。",
  "如有任何疑问，请随时联系我们。",
];

const faqs = ref([
  {question: '我该如何发布失物信息？', answer: '请点击“发布失物”按钮，填写相关信息并提交。', isOpen: false},
  {
    question: '如何修改或删除我的失物信息？',
    answer: '请登录您的账号，进入“我的发布”页面进行修改或删除操作。',
    isOpen: false
  },
  {question: '如何确认物品是否已被认领？', answer: '当您的物品被认领后，您将收到通知。', isOpen: false},
  {question: '失物招领平台支持哪些物品？', answer: '平台支持各种失物，包括书籍、电子设备、个人物品等。', isOpen: false},
  {question: '我可以举报虚假信息吗？', answer: '是的，您可以通过“举报”功能举报虚假信息。', isOpen: false},
  {question: '失物招领需要注册账户吗？', answer: '是的，您需要注册一个账户才能发布或认领失物。', isOpen: false},
  {question: '如何查看我的失物信息？', answer: '请登录您的账户，进入“我的发布”页面查看。', isOpen: false},
]);

const lostItems = ref([
  {name: '黑色背包', description: '含有书籍和个人物品'},
  {name: '蓝牙耳机', description: '品牌：某某，型号：XXX'},
]);

const successStories = ref([
  '用户小张成功找回了丢失的手机。',
  '用户小李通过平台找到了遗失的钥匙。',
]);

const recentLostCount = 20;
const claimSuccessRate = 75;

const searchQuery = ref('');
const filteredFaqs = computed(() => {
  return faqs.value.filter(item => item.question.includes(searchQuery.value));
});

const toggleFaq = (index) => {
  faqs.value[index].isOpen = !faqs.value[index].isOpen;
};

const viewMoreLostItems = () => {
  alert("更多失物信息请访问我们的专门页面。");
};

const scrollPanel = ref(null);

const autoScroll = () => {
  if (scrollPanel.value) {
    const panel = scrollPanel.value;
    panel.scrollTop += 20; // 每次向下滚动20px
    if (panel.scrollTop >= panel.scrollHeight - panel.clientHeight) {
      panel.scrollTop = 0; // 如果滚动到达底部，则回到顶部
    }
  }
};

onMounted(() => {
  setInterval(autoScroll, 3000); // 每3秒滚动一次
});
</script>

<style lang="less" scoped>
.guideline-container {
  margin: -20px;
}

.operation-guide, .faq, .contact-info, .lost-items, .success-stories, .statistics {
  margin-bottom: 15px;
}

.faq ,.operation-guide {
  min-height: 500px;
  max-height: 500px;
}

.faq-title {
  display: flex;
  align-items: flex-start;
}

.operation-guide {
  display: flex;
  //  左对齐
  .operation-list-title {
    display: flex;
    align-items: flex-start;
  }

  .operation-list {
    gap: 10px;
    display: flex;
    // 垂直对齐
    flex-direction: column;
    // 水平对齐
    align-items: flex-start;
  }
}

.step-number {
  color: #007bff;
}

.search-input {
  margin-bottom: 15px;
}

.scrolling-panel {
  max-height: 100px; /* 根据需求调整高度 */
  overflow: hidden;
}


/* 搜索输入框样式 */
.search-input {
  width: 100%;
  margin-bottom: 10px;
}


/* 常见问题标题样式 */
.faq-question {
  font-size: 16px;
  //font-weight: bold;
}


/* 常见问题答案样式 */
.faq-answer {
  font-size: 16px;
  line-height: 1.5;
}


/* 操作步骤编号样式 */
.step-number {
  margin-right: 5px;
}


/* 操作说明列表样式 */
ul {
  list-style: none;
  padding: 0;
}


/* 操作说明列表项样式 */
li {
  margin-bottom: 5px;
}
</style>

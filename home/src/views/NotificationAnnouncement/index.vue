<!--通知公告-->

<!--第一版 - 原始风格-->
<!--<template>-->
<!--  <el-card class="announcement-board" shadow="hover">-->
<!--    <template #header>-->
<!--      <div class="announcement-header">-->
<!--        <i class="el-icon-bell"></i>-->
<!--        <span>公告栏</span>-->
<!--      </div>-->
<!--    </template>-->
<!--    <el-timeline>-->
<!--      <el-timeline-item-->
<!--          v-for="item in items"-->
<!--          :key="item.id"-->
<!--          :timestamp="item.date"-->
<!--          placement="top"-->
<!--          hide-timestamp-->
<!--      >-->
<!--        <el-card class="announcement-card" shadow="always">-->
<!--          <div class="announcement-content">-->
<!--            <h4>{{ item.title }}</h4>-->
<!--            <p>{{ item.content }}</p>-->
<!--            <small>{{ item.date }}</small>-->
<!--          </div>-->
<!--        </el-card>-->
<!--      </el-timeline-item>-->
<!--    </el-timeline>-->
<!--  </el-card>-->
<!--</template>-->

<!--<script setup>-->
<!--import { ref } from 'vue'-->

<!--const items = ref([-->
<!--  { id: 1, date: '2024-05-15', title: '公告标题 1', content: '这里是公告内容 1...' },-->
<!--  { id: 2, date: '2024-05-14', title: '公告标题 2', content: '这里是公告内容 2...' },-->
<!--  // 更多公告...-->
<!--])-->
<!--</script>-->

<!--<style scoped>-->
<!--.announcement-board {-->
<!--  margin: 30px;-->
<!--  padding: 20px;-->
<!--  border-radius: 10px;-->
<!--  background-color: #f9f9f9;-->
<!--  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);-->
<!--}-->

<!--.announcement-header {-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  font-size: 18px;-->
<!--  font-weight: bold;-->
<!--}-->

<!--.announcement-header .el-icon-bell {-->
<!--  margin-right: 10px;-->
<!--  color: #ff5e2b;-->
<!--}-->

<!--.el-timeline-item {-->
<!--  margin-bottom: 20px;-->
<!--}-->


<!--.el-timeline-item:before {-->
<!--  background-color: #ff5e2b; /* 时间线的颜色 */-->
<!--}-->

<!--.el-timeline-item__node {-->
<!--  background-color: #ff5e2b;-->
<!--}-->


<!--.announcement-card {-->
<!--  border-radius: 8px;-->
<!--  padding: 15px;-->
<!--  background-color: #fff;-->
<!--}-->

<!--.announcement-content h4 {-->
<!--  margin: 0;-->
<!--  font-size: 18px;-->
<!--  color: #333;-->
<!--}-->

<!--.announcement-content p {-->
<!--  margin: 10px 0;-->
<!--  font-size: 16px;-->
<!--  color: #666;-->
<!--}-->

<!--.announcement-content small {-->
<!--  font-size: 12px;-->
<!--  color: #999;-->
<!--  display: block;-->
<!--  margin-top: 10px;-->
<!--}-->

<!--</style>-->


<!--第二版 -  简洁风格-->


<!--<template>-->
<!--  <el-card class="announcement-board" shadow="hover">-->
<!--    <template #header>-->
<!--      <div class="announcement-header">-->
<!--        <i class="el-icon-bell"></i>-->
<!--        <span>公告栏</span>-->
<!--        &lt;!&ndash; 搜索框 &ndash;&gt;-->
<!--        <el-input v-model="searchTerm" placeholder="搜索公告..." class="announcement-search" clearable />-->
<!--      </div>-->
<!--    </template>-->
<!--    <el-timeline>-->
<!--      <el-timeline-item-->
<!--          v-for="item in filteredItems"-->
<!--          :key="item.id"-->
<!--          :timestamp="item.date"-->
<!--          placement="top"-->
<!--          hide-timestamp-->
<!--      >-->
<!--        <el-card class="announcement-card" shadow="always">-->
<!--          <div class="announcement-content">-->
<!--            <div class="announcement-header">-->
<!--              <el-avatar src="https://www.example.com/avatar.png" />-->
<!--              <el-tag :type="item.tagType" class="announcement-tag">{{ item.tag }}</el-tag>-->
<!--            </div>-->
<!--            <h4>{{ item.title }}</h4>-->
<!--            <p v-if="expandedId === item.id">{{ item.content }}</p>-->
<!--            <p v-else>{{ item.content.substring(0, 50) }}...</p>-->
<!--            <small>{{ item.date }}</small>-->
<!--            <el-button type="text" @click="toggleExpand(item.id)">-->
<!--              {{ expandedId === item.id ? '收起' : '展开' }}-->
<!--            </el-button>-->
<!--          </div>-->
<!--        </el-card>-->
<!--      </el-timeline-item>-->
<!--    </el-timeline>-->
<!--  </el-card>-->
<!--</template>-->

<!--<script setup>-->
<!--import { ref, computed } from 'vue'-->

<!--const items = ref([-->
<!--  { id: 1, date: '2024-05-15', title: '公告标题 1', content: '这里是公告内容 1...', tag: '紧急', tagType: 'danger' },-->
<!--  { id: 2, date: '2024-05-14', title: '公告标题 2', content: '这里是公告内容 2...', tag: '通知', tagType: 'success' },-->
<!--  // 更多公告...-->
<!--])-->

<!--// 搜索功能-->
<!--const searchTerm = ref('')-->
<!--const filteredItems = computed(() =>-->
<!--    items.value.filter(item => item.title.includes(searchTerm.value) || item.content.includes(searchTerm.value))-->
<!--)-->

<!--// 展开/折叠功能-->
<!--const expandedId = ref(null)-->
<!--const toggleExpand = (id) => {-->
<!--  expandedId.value = expandedId.value === id ? null : id-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--.announcement-board {-->
<!--  margin: 30px;-->
<!--  padding: 20px;-->
<!--  border-radius: 10px;-->
<!--  background-color: #f9f9f9;-->
<!--  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);-->
<!--}-->

<!--.announcement-header {-->
<!--  display: flex;-->
<!--  justify-content: space-between;-->
<!--  align-items: center;-->
<!--  font-size: 18px;-->
<!--  font-weight: bold;-->
<!--}-->

<!--.announcement-header .el-icon-bell {-->
<!--  margin-right: 10px;-->
<!--  color: #ff5e2b;-->
<!--}-->

<!--.announcement-search {-->
<!--  max-width: 300px;-->
<!--}-->

<!--.el-timeline-item {-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.announcement-card {-->
<!--  border-radius: 8px;-->
<!--  padding: 15px;-->
<!--  background-color: #fff;-->
<!--  transition: all 0.3s ease-in-out;-->
<!--}-->

<!--.announcement-card:hover {-->
<!--  transform: translateY(-5px);-->
<!--}-->

<!--.announcement-content {-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--}-->

<!--.announcement-header {-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  margin-bottom: 10px;-->
<!--}-->

<!--.announcement-header .el-avatar {-->
<!--  margin-right: 10px;-->
<!--}-->

<!--.announcement-tag {-->
<!--  margin-left: auto;-->
<!--}-->

<!--.announcement-content h4 {-->
<!--  margin: 0;-->
<!--  font-size: 18px;-->
<!--  color: #333;-->
<!--}-->

<!--.announcement-content p {-->
<!--  margin: 10px 0;-->
<!--  font-size: 16px;-->
<!--  color: #666;-->
<!--}-->

<!--.announcement-content small {-->
<!--  font-size: 12px;-->
<!--  color: #999;-->
<!--  display: block;-->
<!--  margin-top: 10px;-->
<!--}-->

<!--.el-timeline-item:before {-->
<!--  background-color: #ff5e2b; /* 时间线的颜色 */-->
<!--}-->

<!--.el-timeline-item__node {-->
<!--  background-color: #ff5e2b;-->
<!--}-->
<!--</style>-->


<!--第三版-->

<template>
  <el-card class="announcement-board" shadow="hover">
    <template #header>
      <div class="announcement-header">
        <!--返回上一页按钮-->
        <el-button class="back-button" type="info" circle="circle" @click="RouteUtils.goBackToPreviousPage">
          <el-icon>
            <Back/>
          </el-icon>
        </el-button>
        <span class="announcement-title"> 📢 公告栏</span>
        <!--隔离带-->
        <div style="flex-grow: 0.4;"/>
        <!-- 搜索框 - 版本一 -->
        <!--<el-input v-model="searchTerm" placeholder="搜索公告..." class="announcement-search" clearable/>-->
        <!--搜索框 - 版本二-->
        <SearchBox class="announcement-search"  v-model="searchTerm" placeholder="搜索公告..." :border-radius="12"/>
        <!--隔离带-->
        <div style="flex-grow: 0.4;"/>
        <!--选择日期排序-->
        <el-switch v-model="sortByDate" active-text="按日期升序" inactive-text="按日期降序"></el-switch>
      </div>
    </template>
    <div class="announcement-content">
      <!-- 通知卡片所在区块的最大宽度-->
      <!--<el-timeline :style="{ maxHeight: '6000px', overflowY: 'auto' }">-->
      <el-timeline :style="{ maxHeight: '6000px', overflowY: '' }">
        <el-timeline-item
            v-for="item in paginatedItems"
            :key="item.id"
            :timestamp="item.date"
            placement="top"
            :color="getTimelineItemColor(item.priority)"
            hide-timestamp
        >
          <el-card class="announcement-card" shadow="always" :style="cardStyle(item.priority)">
            <div class="announcement-header">
              <!--<el-avatar :src="item.avatar"/>-->
              <h4 class="title">{{ item.title }}</h4>
              <el-tag :type="item.tagType" size="large" class="announcement-tag">{{ item.tag }}</el-tag>
            </div>
            <p v-if="expandedId === item.id">{{ item.content }}</p>
            <p v-else>{{ item.content.substring(0, 50) }}...</p>
            <small>{{ timeSince(item.date) }}</small>
            <!--分割线-->
            <el-divider direction="vertical" />
            <el-button type="text" @click="toggleExpand(item.id)">
              {{ expandedId === item.id ? '收起' : '展开' }}
            </el-button>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
    <!-- 分页 -->
    <el-pagination
        class="announcement-pagination"
        v-model:current-page="currentPage"
        :page-size="pageSize"
        :total="filteredItems.length"
        layout="prev, pager, next"
    />
  </el-card>
</template>

<script lang="js" setup>
import {ref, computed} from 'vue'
import {Back} from "@element-plus/icons-vue";
import RouteUtils from "@/utils/RouteUtils";
import SearchBox from "@/components/SearchBox/index.vue";

// 搜索功能
const searchTerm = ref('')

// 排序功能 (默认日期降序)
const sortByDate = ref(false);

//  颜色定义
const color = {
  urgent: '#f56d6d',
  high: '#ffa500',
  medium: '#4ea5ff',
  low: '#808080',
  solved: '#67c23a'
}

const items = ref([
  {
    id: 1,
    date: '2024-05-15',
    title: '公告标题 1',
    content: '这里是公告内这7里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内这里是公告内容 1',
    tag: '紧急',
    tagType: 'danger',
    avatar: 'https://www.example.com/avatar1.png',
    priority: 'urgent'
  },
  {
    id: 2,
    date: '2024-05-14',
    title: '公告标题 2',
    content: '这里是公告内容 2',
    tag: '通知',
    tagType: 'primary',
    avatar: 'https://www.example.com/avatar2.png',
    priority: 'medium'
  },
  {
    id: 3,
    date: '2024-05-13',
    title: '公告标题 3',
    content: '这里是公7告内容 3',
    tag: '普通',
    tagType: 'info',
    avatar: 'https://www.example.com/avatar3.png',
    priority: 'solved'
  },
  {
    id: 4,
    date: '2024-05-12',
    title: '公告标题 4',
    content: '这里是公告内容 4',
    tag: '普通',
    tagType: 'info',
    avatar: 'https://www.example.com/avatar4.png',
    priority: 'low'
  },
  {
    id: 5,
    date: '2024-05-11',
    title: '公告标题 5',
    content: '这里是公告内容 5',
    tag: '已解决',
    tagType: 'success',
    avatar: 'https://www.example.com/avatar5.png',
    priority: 'solved'
  },
  {
    id: 6,
    date: '2024-05-09',
    title: '公告标题 5',
    content: '这里是公告内容 5',
    tag: '已解决',
    tagType: 'success',
    avatar: 'https://www.example.com/avatar5.png',
    priority: 'solved'
  },

  // 更多公告...
])

// 按搜索框内容搜索
// const filteredItems = computed(() =>
//     items.value.filter(item => item.title.includes(searchTerm.value) || item.content.includes(searchTerm.value))
// )
// 按搜索框内容+ 日期排序 搜索
const filteredItems = computed(() => {
  const itemsCopy = [...items.value.filter(item => item.title.includes(searchTerm.value) || item.content.includes(searchTerm.value))];
  return sortByDate.value
      ? itemsCopy.sort((a, b) => new Date(b.date) - new Date(a.date))
      : itemsCopy.sort((a, b) => new Date(a.date) - new Date(b.date));
});


// 展开/折叠功能
const expandedId = ref(null)
const toggleExpand = (id) => {
  expandedId.value = expandedId.value === id ? null : id
}

// 计算发布时间到现在的时间
const timeSince = (dateString) => {
  const date = new Date(dateString)
  const now = new Date()
  const diff = Math.abs(now - date)
  const days = Math.floor(diff / (1000 * 60 * 60 * 24))
  return days > 0 ? `发布于 ${days} 天前` : '今天发布'
}

// 分页功能
const currentPage = ref(1)
const pageSize = 5
// 分页后的项目 （使用的时候将<el-timeline...标签中的filteredItems替换为paginatedItems即可）
const paginatedItems = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  const end = start + pageSize
  return filteredItems.value.slice(start, end)
})


// 根据优先级返回对应的颜色
const getTimelineItemColor = (priority) => {
  switch (priority) {
    case 'urgent':
      return `${color.urgent}`;
    case 'high':
      return `${color.high}`;
    case 'medium':
      return `${color.medium}`;
    case 'low':
      return `${color.low}`;
    case 'solved':
      return `${color.solved}`;
    default:
      return `${color.low}`; // 默认颜色
  }
}

// 根据优先级返回自定义卡片样式
const cardStyle = (priority) => {
  switch (priority) {
    default:
      return `border-left: 4px solid ${color.low};`;// 默认颜色
    case 'urgent':
      return `border-left: 4px solid ${color.urgent};`;
    case 'high':
      return `border-left: 4px solid ${color.high};`;
    case 'medium':
      return `border-left: 4px solid ${color.medium};`;
    case 'low':
      return `border-left: 4px solid ${color.low};`;
    case 'solved':
      return `border-left: 4px solid ${color.solved};`;
  }
}


</script>

<style lang="less" scoped>
.announcement-board {
  margin: -20px; // 关闭卡片模式
  //margin: 30px; // 开启卡片模式
  border-radius: 10px;
  //background-color: var(--background-color, #f9f9f9);
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.announcement-title {
  // 字体紫红金渐变
  color: transparent;
  background: linear-gradient(to right, #a135a1, #ff5e2b, #ffa500, #ffd700, burlywood);
  -webkit-background-clip: text; // 作用是将背景裁剪为文本的形状
}

.back-button {
  background-color: #896e9b;
}

.back-button:hover {
  background-color: #caafdc;
}

.announcement-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 18px;
  font-weight: bold;

  // 公告item标题
  .title {
    margin-top: -10px;
    margin-bottom: -10px;
    transition: all 0.3s ease-in-out; // 添加过渡效果
  }

  .title:hover {
    color: #896e9b;
    transform: translateX(5px); // 移动5像素
    cursor: pointer;// 变成鼠标手
  }
}

.announcement-content {
  margin-left: -20px;
}

.announcement-search {
  max-width: 300px;
}

.el-timeline-item {
  // 是否断开时间线的点
  margin-bottom: 10px;
}

.announcement-card {
  border-radius: 8px;
  background-color: #fff;
  transition: all 0.3s ease-in-out;
  text-align: left;
}

.announcement-card:hover {
  // 鼠标悬停时，卡片向上移动5像素
  transform: translateY(-5px);
}

.el-timeline-item:before {
  background-color: #ff5e2b;
}

.el-timeline-item__node {
  background-color: #ff5e2b;
}

.announcement-pagination {
  display: flex;
  justify-content: center;
}
</style>

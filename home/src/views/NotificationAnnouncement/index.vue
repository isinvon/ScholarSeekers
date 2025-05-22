<!--通知公告-->
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
        <!--搜索框 - 版本二-->
        <SearchBox class="announcement-search" v-model="searchTerm" placeholder="搜索公告..." :border-radius="12"/>
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
            <el-divider direction="vertical"/>
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
import {computed, onMounted, ref} from 'vue'
import {Back} from "@element-plus/icons-vue";
import RouteUtils from "@/utils/RouteUtils";
import SearchBox from "@/components/SearchBox/index.vue";
import NotificationAnnouncement from "@/api/notificationAnnouncement";

// 对接后的数据相关逻辑
const items = ref([])
const loading = ref(false)
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
  const hours = Math.floor(diff / (1000 * 60 * 60))
  return days > 0 ? `发布于 ${days} 天前` : hours > 0 ? `发布于 ${hours} 小时前` : '刚刚发布'
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


// 获取通知数据
const fetchNotifications = async () => {
  try {
    loading.value = true
    const res = await NotificationAnnouncement.list({})
    if (res.code === 200) {
      items.value = res.data.map(item => ({
        id: item.id,
        date: item.updateTime, // 使用更新时间
        title: getTitleByType(item.type), // 根据类型生成标题
        content: item.content,
        tag: getTagText(item.type),
        tagType: getTagType(item.type),
        priority: getPriority(item.type)
      }))
    }
  } catch (error) {
    console.error('获取通知失败:', error)
  } finally {
    loading.value = false
  }
}

// 类型映射方法
const getTagText = (type) => {
  const map = {
    0: '普通',
    1: '紧急',
    2: '已解决'
  }
  return map[type] || '通知'
}

const getTagType = (type) => {
  const map = {
    0: 'primary',
    1: 'danger',
    2: 'success'
  }
  return map[type] || 'info'
}

const getPriority = (type) => {
  const map = {
    0: 'medium',
    1: 'urgent',
    2: 'solved'
  }
  return map[type] || 'low'
}

const getTitleByType = (type) => {
  const prefixMap = {
    0: '系统通知',
    1: '紧急通知',
    2: '已解决通知'
  }
  return `${prefixMap[type] || '通知'} ${new Date().toLocaleDateString()}`
}


// 初始化获取数据
onMounted(fetchNotifications)

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
    cursor: pointer; // 变成鼠标手
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

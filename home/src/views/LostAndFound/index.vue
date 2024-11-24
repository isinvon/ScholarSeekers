<!--寻物启事-->
<template>
  <div class="card-wrapper">

    <div class="header">
      <!--返回上一页按钮-->
      <!--<el-button class="back-button" type="success" circle="circle" @click="RouteUtils.goBackToPreviousPage">-->
      <!--  <el-icon>-->
      <!--    <Back/>-->
      <!--  </el-icon>-->
      <!--</el-button>-->

      <!--搜索框-->
      <SearchBox class="search" v-model="searchTerm" placeholder="搜索文章" :border-radius="12"/>
      <!--隔离带-->
      <!--<div style="flex-grow: 0.4;"/>-->
      <!--隔离带-->
      <!--<div style="flex-grow: 0.4;"/>-->
      <!--选择日期排序-->
      <el-switch class="sort-by-date" v-model="sortByDate" active-text="按日期升序"
                 inactive-text="按日期降序"></el-switch>
    </div>

    <!-- 卡片 -->
    <el-card v-for="(item, index) in displayedCards" :key="index" class="box-card" shadow="hover">
      <!-- 卡片内部 -->
      <el-row class="box-card-content">
        <!-- 左列 -->
        <el-col :span="3" class="image-col">
          <!-- 文章图片 -->
          <!--<router-link :to="{ name: 'itemDetailPage', params: { id: item.id } }">-->
          <router-link :to="{ name: 'article', params: { id: item.id } }">
            <img
                :src="item.image"
                alt="文章图片"
                class="card-image"
            />
          </router-link>
        </el-col>

        <!-- 中列 -->
        <el-col :span="20" class="content-col">
          <el-row class="title">
            <el-col :span="24" class="align-left">
              <!--<router-link :to="{ name: 'itemDetailPage', params: { id: item.id } }">-->
              <router-link :to="{ name: 'article', params: { id: item.id } }">
                <el-link :underline="false" class="title-link">
                  <!-- 如果浏览量超过500，显示火苗图标 - 样式 一 -->
                  <!--<el-icon v-if="item.views > 500" class="fire-icon">-->
                  <!--  🔥-->
                  <!--</el-icon>-->
                  {{ item.title }}
                  <!-- 如果浏览量超过500，显示火苗图标 - 样式 二 -->
                  <!--间隔-->
                  <span style="margin: 0 5px;"></span>
                  <el-tag v-if="item.views > 500" class="fire-icon" effect="light" round type="danger">🔥
                  </el-tag>
                </el-link>
              </router-link>
            </el-col>
          </el-row>

          <el-row class="content">
            <el-col :span="24" class="align-left">
              <p>{{ item.content }}</p>
            </el-col>
          </el-row>

          <el-row class="footer">
            <el-col :span="24" class="align-left">
              <p>{{ item.date }}</p>
              <!-- 添加拾物启事/失物招领标签 -->
              <el-tag :type="item.type === 'lost' ? 'danger' : 'success'">
                {{ item.type === 'lost' ? '失物招领' : '拾物启事' }}
              </el-tag>
              <!--间隔-->
              <span style="margin: 0 5px;"></span>
              <!-- 显示浏览量 -->
              <el-tag>
                浏览量：{{ item.views }}
              </el-tag>
            </el-col>
          </el-row>
        </el-col>

        <!-- 右列 -->
        <el-col :span="1" class="comment-col">
          <!--<router-link :to="{ name: 'itemDetailPage', params: { id: item.id } }">-->
          <router-link :to="{ name: 'article', params: { id: item.id } }">
            <div class="comment-section">
              <el-icon>
                <ChatSquare/>
              </el-icon>
              <el-tag type="success" round size="large" class="comment-tag">
                <span class="comment-count">{{ item.comments }}</span>
              </el-tag>
            </div>
          </router-link>
        </el-col>
      </el-row>
    </el-card>

    <!-- 分页 -->
    <el-pagination
        class="pagination"
        v-model:currentPage="currentPage"
        :page-size="pageSize"
        :total="totalCards"
        layout="prev, pager, next"
        @current-change="handlePageChange"
    />
  </div>
</template>

<script lang="js" setup>
import {ref, computed} from 'vue'
import {Back, ChatSquare} from '@element-plus/icons-vue'
import RouteUtils from "@/utils/RouteUtils";
import SearchBox from "src/components/SearchBox";


// 搜索功能
const searchTerm = ref('')

// 排序功能 (默认日期降序)
const sortByDate = ref(false);

// 模拟数据
const articles = ref([
  {
    id: 1,
    title: '文章标题1',
    content: '这是一篇文章的内容简介1。',
    date: '2024-09-01',
    image: 'https://picsum.photos/200/200',
    comments: 5,
    type: 'found', // 拾物启事
    views: 350
  },
  {
    id: 2,
    title: '文章标题2',
    content: '这是一篇文章的内容简介2。',
    date: '2024-09-02',
    image: 'https://picsum.photos/200/200',
    comments: 8,
    type: 'lost', // 失物招领
    views: 550
  },
  {
    id: 3,
    title: '文章标题3',
    content: '这是一篇文章的内容简介3。',
    date: '2024-09-03',
    image: 'https://picsum.photos/200/200',
    comments: 3,
    type: 'found',
    views: 450
  },
  {
    id: 4,
    title: '文章标题4',
    content: '这是一篇文章的内容简介4。',
    date: '2024-09-04',
    image: 'https://picsum.photos/200/200',
    comments: 12,
    type: 'lost',
    views: 650
  },
  {
    id: 5,
    title: '文章标题5',
    content: '这是一篇文章的内容简介5。',
    date: '2024-09-05',
    image: 'https://picsum.photos/200/200',
    comments: 6,
    type: 'found',
    views: 300
  },
  {
    id: 6,
    title: '文章标题6',
    content: '这是一篇文章的内容简介6。',
    date: '2024-09-06',
    image: 'https://picsum.photos/200/200',
    comments: 10,
    type: 'lost',
    views: 720
  },
  // 更多模拟数据...
])


// 按搜索框内容搜索
// const filteredItems = computed(() =>
//     items.value.filter(item => item.title.includes(searchTerm.value) || item.content.includes(searchTerm.value))
// )
// 按搜索框内容 + 日期排序 搜索
const filteredItems = computed(() => {
  const itemsCopy = [...articles.value.filter(item => item.title.includes(searchTerm.value) || item.content.includes(searchTerm.value))];
  return sortByDate.value
      ? itemsCopy.sort((a, b) => new Date(a.date) - new Date(b.date))
      : itemsCopy.sort((a, b) => new Date(b.date) - new Date(a.date));
});


// 分页设置
const totalCards = ref(articles.value.length)
const pageSize = ref(5)
const currentPage = ref(1)

// 根据当前页码和分页大小计算显示的卡片
const displayedCards = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value
  const end = start + pageSize.value
  // return articles.value.slice(start, end)
  // filteredItems 就是 经过筛选处理过后的数据条目集合
  return filteredItems.value.slice(start, end)
})

// 处理页码变化的函数
const handlePageChange = (page) => {
  currentPage.value = page
}


</script>

<style lang="less" scoped>
/* 卡片整体布局容器 */
.card-wrapper {
  display: flex;
  flex-direction: column;
  gap: 20px; /* 卡片之间的间距 */
  //background-color: #f9f9fb; /* 更柔和的背景色 */
}

// 顶栏布局
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 18px;
  font-weight: bold;
}


/* 卡片样式 */
.box-card {
  border-radius: 15px; /* 增加圆角 */
  background-color: #ffffff; /* 卡片背景色 */
  border: 1px solid #e0e0e0; /* 添加边框 */
  transition: box-shadow 0.3s ease, transform 0.3s ease;
  &:hover {
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1); /* 鼠标悬浮时的阴影效果 */
    transform: translateY(-5px); /* 鼠标悬浮时卡片轻微上移 */
  }
}

/* 卡片内容布局 */
.box-card-content {
  display: flex;
  align-items: center;
}

/* 图片列 */
.image-col {
  display: flex;
  justify-content: center;
  align-items: center;
}

.card-image {
  width: 150px; /* 缩小图片尺寸 */
  height: 150px;
  border-radius: 12px;
  object-fit: cover; /* 保持图片比例 */
  transition: transform 0.3s ease;
  &:hover {
    transform: scale(1.05); /* 鼠标悬浮时图片轻微放大 */
  }
}

/* 中间列内容布局 */
.content-col {
  padding-left: 15px;
}

/* 标题样式 */
.title-link {
  font-size: 22px; /* 调整字体大小 */
  font-weight: bold;
  color: #333;
  transition: color 0.3s ease, transform 0.3s ease;

  &:hover {
    color: #409eff; /* 鼠标悬浮时的颜色变化 */
    transform: translateX(5px); /* 鼠标悬浮时轻微位移 */
  }
}

/* 火苗图标 */
.fire-icon {
  color: #ff6f00; /* 火苗图标的颜色 */
  margin-right: 5px;
}

/* 内容和日期样式 */
.content p,
.footer p {
  font-size: 16px;
  color: #666;
}

.footer p {
  font-size: 12px;
  color: #999;
  margin-top: 8px;
}

/* 评论区域布局 */
.comment-col {
  display: flex;
  align-items: center;
  justify-content: center;
}

.comment-section {
  display: flex;
  align-items: center;
  gap: 5px;
}

.comment-count {
  font-size: 14px;
  color: #666;
  font-weight: bold;
}

/* 确保标题、内容和日期都左对齐 */
.align-left {
  text-align: left;
}

.pagination {
  display: flex;
  justify-content: center;
}
</style>

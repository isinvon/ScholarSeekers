<!--我的发布-->


<!--竖着的-->

<!--<template>-->
<!--  <div class="lost-and-found-container">-->
<!--    &lt;!&ndash;<h2 class="page-title">我的失物招领发布</h2>&ndash;&gt;-->
<!--    &lt;!&ndash;搜索框&ndash;&gt;-->
<!--    <el-input v-model="searchQuery" placeholder="搜索公告"></el-input>-->
<!--    &lt;!&ndash;选择器筛选&ndash;&gt;-->
<!--    <el-select v-model="selectedTag" placeholder="筛选类型">-->
<!--      <el-option label="全部" value=""></el-option>-->
<!--      <el-option label="失物招领" value="失物招领"></el-option>-->
<!--      <el-option label="拾物启事" value="拾物启事"></el-option>-->
<!--    </el-select>-->

<!--    <el-row v-for="item in paginatedItems" :key="item.id"-->
<!--            :class="['announcement-row', { 'highlight': expandedId === item.id }]">-->
<!--      <el-card class="announcement-card" shadow="always">-->
<!--        <div class="header">-->
<!--          <h4 class="title">{{ item.title }}</h4>-->
<!--          <el-tag :type="item.tagType" size="medium">{{ item.tag }}</el-tag>-->
<!--        </div>-->
<!--        <transition name="expand">-->
<!--          <div class="content">-->
<!--            <div v-if="expandedId === item.id" class="expanded">-->
<!--              {{ item.content }}-->
<!--            </div>-->
<!--            <div v-else>-->
<!--              {{ item.content.substring(0, 50) }}...-->
<!--            </div>-->
<!--          </div>-->
<!--        </transition>-->

<!--        <div class="meta">-->
<!--          <small>{{ timeSince(item.date) }}</small>-->
<!--          <el-button type="text" icon="caret-down" @click="toggleExpand(item.id)">-->
<!--            {{ expandedId === item.id ? '收起' : '展开' }}-->
<!--          </el-button>-->
<!--        </div>-->
<!--        <div class="actions">-->
<!--          <el-button class="edit-btn" type="primary" icon="edit" size="small">编辑</el-button>-->
<!--          <el-button class="delete-btn" type="danger" icon="delete" size="small">删除</el-button>-->
<!--        </div>-->
<!--      </el-card>-->
<!--    </el-row>-->
<!--    &lt;!&ndash;分页&ndash;&gt;-->
<!--    <el-pagination-->
<!--        class="announcement-pagination"-->
<!--        v-model:current-page="currentPage"-->
<!--        :page-size="pageSize"-->
<!--        :total="filteredItems.length"-->
<!--        layout="prev, pager, next">-->
<!--    </el-pagination>-->
<!--  </div>-->
<!--</template>-->

<!--横向顺序排布-->

<template>
  <div class="lost-and-found-container">
    <div class="announcement-header">
      <!--搜索框 - 旧-->
      <!--<el-input class="announcement-search" v-model="searchQuery" placeholder="搜索物品"></el-input>-->
      <!--搜索框 - 新-->
      <SearchBox class="announcement-search" v-model="searchQuery" placeholder="搜索物品" :border-radius="12"/>
      <!--选择器筛选-->
      <el-select class="announcement-select" v-model="selectedTag" placeholder="筛选类型">
        <el-option label="全部" value=""></el-option>
        <el-option label="失物招领" value="失物招领"></el-option>
        <el-option label="拾物启事" value="拾物启事"></el-option>
      </el-select>
    </div>

    <el-row :gutter="20">
      <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="item in paginatedItems" :key="item.id">

        <el-card class="announcement-card" shadow="always">
          <div class="header">
            <h4 class="title">
              <div v-if="expandedTitleId === item.id" class="expanded">
                <!--物品序号标签-->
                <el-tag class="announcement-number" type="info" size="medium">{{ item.id }}</el-tag>
                {{ item.title }}
              </div>
              <div v-else>
                <!--物品序号标签-->
                <el-tag class="announcement-number" type="info" size="medium">{{ item.id }}</el-tag>
                {{ item.title.substring(0,6) }}...
              </div>
            </h4>
            <!--物品类型标签-->
            <el-tag class="announcement-tag lightning-button" :type="item.tagType" size="medium">{{ item.tag }}</el-tag>
          </div>
          <div class="announcement-content">
            <!--图片-->
            <el-image class="announcement-image" :src="item.imageUrl" fit="cover"
                      style="width: 100px; height: 100px;"></el-image>
            <!--内容-->
            <transition name="expand">
              <div class="content">
                <div v-if="expandedContentId === item.id" class="expanded">
                  {{ item.content }}
                </div>
                <div v-else>
                  {{ item.content.substring(0, 20) }}...
                </div>
              </div>
            </transition>

          </div>

          <div class="meta">
            <!--时间-->
            <small class="announcement-time">{{ timeSince(item.date) }}</small>
            <!--展开/收起-->
            <el-button type="text" icon="caret-down" @click="toggleExpand(item.id)">
              {{ expandedContentId === item.id ? '收起' : '展开' }}
            </el-button>
          </div>
          <div class="actions">
            <!--<el-button class="edit-btn" type="primary" icon="edit" size="small">编辑</el-button>-->
            <el-button class="edit-btn" type="primary" size="small">编辑</el-button>
            <!--<el-button class="delete-btn" type="danger" icon="delete" size="small">删除</el-button>-->
            <el-button class="delete-btn" type="danger" size="small">删除</el-button>
          </div>
        </el-card>
      </el-col>

    </el-row>
    <br>
    <br>
    <!--分页-->
    <el-pagination
        class="announcement-pagination"
        v-model:current-page="currentPage"
        :page-size="pageSize"
        :total="filteredItems.length"
        layout="prev, pager, next">
    </el-pagination>
  </div>
</template>

<script setup>
import {computed, ref} from 'vue';
import SearchBox from "@/components/SearchBox/index.vue";

// 当前展开的ID
const expandedContentId = ref(null);
const expandedTitleId = ref(null);

// 模拟数据
const lostAndFoundItems = [
  {
    id: 1,
    title: '寻找丢失的钱包',
    tag: '失物招领',
    tagType: 'primary',
    content: '我在公园丢失了一个黑色钱失了一个黑色钱包，里面有重要证件和现金。',
    priority: 'normal',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 2,
    title: '捡到一只宠物狗',
    tag: '拾物启事',
    tagType: 'success',
    content: '在小区门口捡到一只可爱的宠物狗，希望主人尽快联系我。',
    priority: 'low',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 3,
    title: '寻回我的手机',
    tag: '失物招领',
    tagType: 'primary',
    content: '我的手机被偷了，希望有同学能帮我找回来。',
    priority: 'high',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 4,
    title: '拾到一堆垃圾',
    tag: '拾物启事',
    tagType: 'success',
    content: '在公园捡到了一堆垃圾，希望有同学能帮我收一下。',
    priority: 'medium',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 5,
    title: '丢失我的钱包',
    tag: '失物招领',
    tagType: 'primary',
    content: '我丢失了钱包，希望有同学能帮我找回来。',
    priority: 'high',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 6,
    title: '拾到一堆垃圾',
    tag: '拾物启事',
    tagType: 'success',
    content: '在公园捡到了一堆垃圾，希望有同学能帮我收一下。',
    priority: 'medium',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 7,
    title: '丢失我的钱包',
    tag: '失物招领',
    tagType: 'primary',
    content: '我丢失了钱包，希望有同学能帮我找回来。',
    priority: 'high',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 8,
    title: '寻找丢失的钱包',
    tag: '失物招领',
    tagType: 'primary',
    content: '我在公园丢失了一个黑色钱失了一个黑色钱包，里面有重要证件和现金。',
    priority: 'normal',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 9,
    title: '捡到一只宠物狗',
    tag: '拾物启事',
    tagType: 'success',
    content: '在小区门口捡到一只可爱的宠物狗，希望主人尽快联系我。',
    priority: 'low',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 10,
    title: '寻回我的手机',
    tag: '失物招领',
    tagType: 'primary',
    content: '我的手机被偷了，希望有同学能帮我找回来。',
    priority: 'high',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 11,
    title: '拾到一堆垃圾',
    tag: '拾物启事',
    tagType: 'success',
    content: '在公园捡到了一堆垃圾，希望有同学能帮我收一下。',
    priority: 'medium',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 12,
    title: '丢失我的钱包',
    tag: '失物招领',
    tagType: 'primary',
    content: '我丢失了钱包，希望有同学能帮我找回来。',
    priority: 'high',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 13,
    title: '寻回我的手机',
    tag: '失物招领',
    tagType: 'primary',
    content: '我的手机被偷了，希望有同学能帮我找回来。',
    priority: 'high',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  },
  {
    id: 14,
    title: '拾到一堆垃圾',
    tag: '拾物启事',
    tagType: 'success',
    content: '在公园捡到了一堆垃圾，希望有同学能帮我收一下。',
    priority: 'medium',
    imageUrl: 'https://picsum.photos/100/100',
    date: new Date()
  }
];



// 计算发布时间
const timeSince = (date) => {
  const now = new Date();
  const diff = now.getTime() - date.getTime();
  const seconds = Math.floor(diff / 1000);
  if (seconds < 60) {
    return `${seconds} 秒前`;
  } else if (seconds < 3600) {
    const minutes = Math.floor(seconds / 60);
    return `${minutes} 分钟前`;
  } else if (seconds < 86400) {
    const hours = Math.floor(seconds / 3600);
    return `${hours} 小时前`;
  } else {
    const days = Math.floor(seconds / 86400);
    return `${days} 天前`;
  }
};

const toggleExpand = (id) => {
  expandedContentId.value = expandedContentId.value === id ? null : id;
};

// 搜索条件
const searchQuery = ref('');
// 筛选条件
const selectedTag = ref('');

// 计算过滤后的项目
const filteredItems = computed(() => {
  return lostAndFoundItems.filter(item => {
    // 搜索条件
    const matchesSearch = item.title.includes(searchQuery.value) || item.content.includes(searchQuery.value);
    // 筛选条件
    const matchesTag = selectedTag.value === '' || item.tag === selectedTag.value;
    // 同时满足搜索和筛选条件
    return matchesSearch && matchesTag;
  });
});


// 分页功能
const currentPage = ref(1);
const pageSize = 12; // 每页显示的条数
// 分页后的项目 （使用的时候将<el-row...标签中的filteredItems替换为paginatedItems即可）
const paginatedItems = computed(() => {
  const start = (currentPage.value - 1) * pageSize;
  const end = currentPage.value * pageSize;
  return filteredItems.value.slice(start, end);
});
</script>

<style lang="less" scoped>
@import "./index";
</style>

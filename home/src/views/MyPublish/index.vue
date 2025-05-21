<template>
  <div v-loading="loading" class="lost-and-found-container">
    <div class="announcement-header">
      <SearchBox
          class="announcement-search"
          v-model="searchParams.keyword"
          placeholder="搜索物品标题或描述"
          :border-radius="12"
          @search="fetchData"
      />
      <el-select
          class="announcement-select"
          v-model="searchParams.status"
          placeholder="认领状态"
          @change="handleStatusChange"
      >
        <el-option label="全部" value=""></el-option>
        <el-option label="待认领" value="0"></el-option>
        <el-option label="已认领" value="1"></el-option>
        <el-option label="已关闭" value="2"></el-option>
      </el-select>
    </div>

    <el-row :gutter="20">
      <template v-if="paginatedItems.length > 0">
        <el-col
            v-for="item in paginatedItems"
            :key="item.id"
            :xs="24"
            :sm="12"
            :md="8"
            :lg="6"
        >
          <el-card class="announcement-card" shadow="hover">
            <div class="card-header">
              <el-tag class="item-id">#{{ item.id }}</el-tag>
              <el-tag :type="statusTypeMap[item.status]">
                {{ statusTextMap[item.status] }}
              </el-tag>
            </div>

            <h3 class="item-title">
              {{ item.title }}
              <el-tooltip content="丢失时间" placement="top">
                <span class="lost-time">
                  <i class="el-icon-time"></i>
                  {{ formatDate(item.lostTime) }}
                </span>
              </el-tooltip>
            </h3>

            <div class="item-content">
              <el-image
                  class="announcement-image"
                  :src="getImageUrl(item.imageUrl)"
                  fit="cover"
                  style="width: 100px; height: 100px;"
                  :preview-src-list="[getImageUrl(item.imageUrl)]"
                  hide-on-click-modal
              >
                <template #error>
                  <div class="image-error">图片加载失败</div>
                </template>
              </el-image>
              <div class="item-desc">
                {{ truncateText(item.description, 50) }}
              </div>
            </div>

            <div class="item-footer">
              <div class="meta-info">
                <el-tooltip content="丢失地点" placement="bottom">
                  <span class="location">
                    <i class="el-icon-location-outline"></i>
                    {{ item.lostLocation }}
                  </span>
                </el-tooltip>
                <el-tooltip content="发布时间" placement="bottom">
                  <span class="create-time">
                    {{ timeSince(item.createTime) }}
                  </span>
                </el-tooltip>
              </div>
              <div class="item-actions">
                <el-button
                    size="mini"
                    @click="handleEdit(item.id)"
                >
                  编辑
                </el-button>
                <el-button
                    type="danger"
                    size="mini"
                    @click="handleDelete(item.id)"
                >
                  删除
                </el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </template>
      <el-empty v-else description="暂无失物记录"/>
    </el-row>

    <el-pagination
        v-if="total > 0"
        background
        layout="total, sizes, prev, pager, next"
        :page-sizes="[12, 24, 48]"
        :current-page="pagination.page"
        :page-size="pagination.size"
        :total="total"
        @current-change="handlePageChange"
        @size-change="handleSizeChange"
    />


      <!-- 添加编辑对话框 -->
    <EditDialog 
    v-model="editDialogVisible"
    :initial-data="currentItem"
    @submit="handleEditSubmit"
  />
  </div>
</template>

<script setup>
import {ref, computed, onMounted} from 'vue';
import {ElMessage, ElMessageBox} from 'element-plus';
import dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';
import { 
  lostItemListForCurrentUser, 
  editLostItem
} from '@/api/lostItem';
// 新增导入
import EditDialog from './editDialog/index';

dayjs.extend(relativeTime);



// 新增响应式数据
const editDialogVisible = ref(false);
const currentItem = ref({});


// 状态映射配置
const statusTextMap = {
  0: '待认领',
  1: '已认领',
  2: '已关闭'
};

const statusTypeMap = {
  0: 'warning',
  1: 'success',
  2: 'info'
};

// 分页参数
const pagination = ref({
  page: 1,
  size: 12
});

// 搜索参数
const searchParams = ref({
  keyword: '',
  status: ''
});


// 状态标签映射方法
const getStatusTag = (status) => {
  return {
    text: statusTextMap[status] || '未知状态',
    type: statusTypeMap[status] || 'danger'
  };
};

// 数据相关
const items = ref([]);
const total = ref(0);
const loading = ref(false);

// 使用Vite的官方访问方式
const getImageUrl = (path) => {
  // console.log('图片路径:', `${import.meta.env.VITE_BASE_URL}${path}`);
  return `http://localhost/dev-api${path}`;
};

// 获取数据
const fetchData = async () => {
  try {
    loading.value = true;
    const res = await lostItemListForCurrentUser();
    if (res.code === 200) {
      items.value = res.data.map(item => ({
        ...item,
        date: new Date(item.createTime),
        tag: getStatusTag(item.status).text,
        tagType: getStatusTag(item.status).type,
        content: item.description || '暂无详细描述',
        imageUrl: item.imageUrl // 直接使用后端返回的路径
      }));
    }
  } catch (error) {
    console.error('数据加载失败:', error);
    ElMessage.error('数据加载失败');
  } finally {
    loading.value = false;
  }
};

// 分页处理
const handlePageChange = (page) => {
  pagination.value.page = page;
  fetchData();
};

const handleSizeChange = (size) => {
  pagination.value.size = size;
  pagination.value.page = 1;
  fetchData();
};

// 状态筛选变化
const handleStatusChange = () => {
  pagination.value.page = 1;
  fetchData();
};

// 时间格式化
const formatDate = (date) => dayjs(date).format('YYYY-MM-DD');

// 相对时间计算
const timeSince = (date) => {
  const now = dayjs();
  const target = dayjs(date);
  const diff = now.diff(target, 'second');

  if (diff < 60) return `${diff}秒前`;
  if (diff < 3600) return `${Math.floor(diff / 60)}分钟前`;
  if (diff < 86400) return `${Math.floor(diff / 3600)}小时前`;
  return `${Math.floor(diff / 86400)}天前`;
};

// 文本截断
const truncateText = (text, maxLength) => {
  if (!text) return '暂无描述';
  return text.length > maxLength
      ? `${text.substring(0, maxLength)}...`
      : text;
};


const handleEdit = (id) => {
  const target = items.value.find(item => item.id === id);
  if (target) {
    currentItem.value = {
      ...target,
      // 转换时间格式（如果后端需要特定格式）
      lostTime: dayjs(target.lostTime).format('YYYY-MM-DD')
    };
    editDialogVisible.value = true;
  }
};

// 新增提交处理
const handleEditSubmit = async (formData) => {
  try {
    const { code } = await editLostItem({
      ...formData,
      // 转换时间类型（根据后端要求）
      lostTime: new Date(formData.lostTime).toISOString()
    });
    
    if (code === 200) {
      ElMessage.success('修改成功');
      fetchData(); // 刷新数据
    }
  } catch (error) {
    ElMessage.error(error.msg || '修改失败');
  }
};

// 初始化加载
onMounted(fetchData);

// 分页后的数据
const paginatedItems = computed(() => items.value);
</script>

<style lang="less" scoped>
@import "./index.less";

.lost-and-found-container {
  padding: 20px;

  .announcement-header {
    display: flex;
    gap: 15px;
    margin-bottom: 20px;

    .announcement-search {
      flex: 1;
    }

    .announcement-select {
      width: 150px;
    }
  }

  .announcement-card {
    margin-bottom: 20px;

    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 12px;

      .item-id {
        background-color: #f5f7fa;
        color: #909399;
      }
    }

    .item-title {
      margin: 0 0 12px;
      font-size: 16px;
      display: flex;
      justify-content: space-between;
      align-items: center;

      .lost-time {
        font-size: 12px;
        color: #909399;

        .el-icon-time {
          margin-right: 4px;
        }
      }
    }

    .item-content {
      display: flex;
      gap: 15px;
      margin-bottom: 12px;

      .item-image {
        width: 100px;
        height: 100px;
        border-radius: 4px;
        flex-shrink: 0;
      }

      .item-desc {
        flex: 1;
        color: #606266;
        line-height: 1.5;
      }
    }

    .item-footer {
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: 12px;
      color: #909399;

      .meta-info {
        display: flex;
        gap: 15px;

        .location {
          .el-icon-location-outline {
            margin-right: 4px;
          }
        }
      }

      .item-actions {
        button {
          padding: 6px 12px;
        }
      }
    }
  }

  .el-pagination {
    margin-top: 20px;
    justify-content: center;
  }
}

@media (max-width: 768px) {
  .lost-and-found-container {
    padding: 10px;

    .announcement-header {
      flex-direction: column;

      .announcement-select {
        width: 100%;
      }
    }

    .item-content {
      flex-direction: column;

      .item-image {
        width: 100% !important;
        height: 150px !important;
      }
    }

    .item-footer {
      flex-direction: column;
      align-items: flex-start !important;
      gap: 8px;
    }
  }
}
</style>
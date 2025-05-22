<template>
  <div class="profile-container">
    <div class="profile-header">
      <el-avatar :size="120" :src="getImageUrl(user.avatar) || 'https://dummyimage.com/100x100/999/fff'">
        <template #error>
          <div class="avatar-error">头像加载失败</div>
        </template>
      </el-avatar>
      <div class="profile-meta">
        <h1>{{ displayName }}</h1>
        <p class="account-id">用户ID: {{ user.id }}</p>
      </div>
    </div>

    <el-descriptions title="基本信息" :column="2" border>
      <el-descriptions-item label="用户名">
        {{ user.username }}
      </el-descriptions-item>

      <el-descriptions-item label="性别">
        <el-tag :type="genderTagType">
          {{ genderText }}
        </el-tag>
      </el-descriptions-item>

      <el-descriptions-item label="注册时间">
        {{ formatDate(user.createTime) }}
      </el-descriptions-item>

      <el-descriptions-item label="最后更新时间">
        {{ formatDate(user.updateTime) }}
      </el-descriptions-item>

      <el-descriptions-item label="手机号">
        {{ user.phone || '未绑定' }}
      </el-descriptions-item>

      <el-descriptions-item label="学院">
        {{ user.college || '未设置' }}
      </el-descriptions-item>

      <el-descriptions-item label="年级">
        {{ user.grade || '未设置' }}
      </el-descriptions-item>

      <el-descriptions-item label="地址" :span="2">
        {{ user.address || '未设置' }}
      </el-descriptions-item>
    </el-descriptions>

    <div class="description-section" v-if="user.introduction">
      <h3>个人简介</h3>
      <p class="description-content">
        {{ user.introduction }}
      </p>
    </div>
  </div>
</template>

<script setup>
import {ref, computed} from 'vue'
import dayjs from 'dayjs'
import User from '@/api/user'


const user = ref({
  id: 4,
  username: "15677141741",
  sex: null,
  avatar: null,      // 移除重复的 avatar 属性
  introduction: null,
  address: null,
  birthday: null,
  college: null,
  createBy: null,
  createTime: "2025-05-20 11:28:19",
  updateBy: null,
  updateTime: "2025-05-20 11:28:19",
  email: null,
  grade: null,
  isDeleted: false,
  password: "linxinhuan123",
  phone: "15677141741",
  remark: null,
  role: 0,
  status: 0,
  tag: null
})

// 使用Vite的官方访问方式
const getImageUrl = (path) => {
  // console.log('图片路径:', `${import.meta.env.VITE_BASE_URL}${path}`);
  return `http://localhost/dev-api${path}`;
};


// 计算属性
const displayName = computed(() => {
  return user.value.username || `用户_${user.value.id.slice(0, 6)}`
})

const genderText = computed(() => {
  const genderMap = {0: '男', 1: '女'}
  console.log("xxxxxxxxxx" + user.value.avatar)
  return user.value.sex !== null ? genderMap[user.value.sex] : '未设置'
})

const genderTagType = computed(() => {
  return user.value.sex === 1 ? 'danger' : 'primary'
})

// 方法
const formatDate = (dateStr) => {
  return dateStr ? dayjs(dateStr).format('YYYY-MM-DD HH:mm') : '--'
}

const loadUserInfo = async () => {
  try {
    const res = await User.getUserInfo()
    if (res.code === 200) {
      user.value = res.user
    }
  } catch (error) {
    console.error('加载用户信息失败:', error)
  }
}

// 初始化加载
loadUserInfo()
</script>


<style lang="less" scoped>
@primary-color: #409eff;
@success-color: #67c23a;
@warning-color: #e6a23c;
@danger-color: #f56c6c;
@text-primary: #303133;
@text-regular: #606266;
@text-secondary: #909399;

.profile-container {
  max-width: 800px;
  margin: 2rem auto;
  padding: 2.5rem;
  background: #fff;
  border-radius: 1rem;
  box-shadow: 0 12px 32px rgba(0, 0, 0, 0.08);

  .profile-header {
    display: flex;
    align-items: center;
    gap: 2rem;
    margin-bottom: 3rem;
    position: relative;

    .el-avatar {
      border: 4px solid #fff;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
      transition: transform 0.3s ease;

      &:hover {
        transform: scale(1.05);
      }

      ::v-deep(img) {
        object-fit: cover;
      }
    }

    .profile-meta {
      flex: 1;

      h1 {
        margin: 0;
        font-size: 1.8rem;
        color: @text-primary;
        letter-spacing: -0.5px;
        display: flex;
        align-items: center;
        gap: 0.8rem;

        &::before {
          content: '';
          width: 6px;
          height: 1.8rem;
          background: @primary-color;
          border-radius: 3px;
        }
      }

      .account-id {
        margin-top: 0.8rem;
        font-size: 0.9rem;
        color: @text-secondary;
        font-family: monospace;
      }
    }
  }

  :deep(.el-descriptions) {
    margin: 2.5rem 0;
    border-radius: 0.8rem;
    overflow: hidden;

    &__header {
      padding: 1.2rem 1.5rem;
      background: #f8fafc;

      .el-descriptions__title {
        font-size: 1.2rem;
        color: @text-primary;
        font-weight: 600;
      }
    }

    &__body {
      .el-descriptions__table {
        td, th {
          padding: 1.2rem 1.5rem;
        }

        .el-descriptions__label {
          width: 120px;
          color: @text-regular;
          font-weight: 500;
          background: #fafbfc !important;
        }

        .el-descriptions__content {
          color: @text-primary;
          font-weight: 500;
        }

        .el-tag {
          font-weight: 500;
          border: none;

          &--primary {
            background: rgba(@primary-color, 0.1);
            color: @primary-color;
          }

          &--danger {
            background: rgba(@danger-color, 0.1);
            color: @danger-color;
          }
        }
      }
    }
  }

  .description-section {
    margin-top: 2.5rem;
    padding: 1.5rem;
    background: #f8fafc;
    border-radius: 0.8rem;
    border-left: 4px solid @primary-color;

    h3 {
      margin: 0 0 1rem;
      font-size: 1.1rem;
      color: @text-primary;
      display: flex;
      align-items: center;
      gap: 0.5rem;

      &::before {
        content: '✍️';
        font-size: 1.2em;
      }
    }

    .description-content {
      margin: 0;
      line-height: 1.8;
      color: @text-regular;
      white-space: pre-wrap;
    }
  }

  // 响应式设计
  @media (max-width: 768px) {
    padding: 1.5rem;
    margin: 1rem;

    .profile-header {
      flex-direction: column;
      text-align: center;

      h1::before {
        display: none;
      }
    }

    :deep(.el-descriptions) {
      &__body .el-descriptions__table {
        display: block;

        tr {
          display: flex;
          flex-direction: column;
          border-bottom: 1px solid #ebeef5;

          &:last-child {
            border-bottom: none;
          }
        }

        td, th {
          width: 100% !important;
          padding: 0.8rem;
        }

        .el-descriptions__label {
          background: transparent !important;
          border-bottom: 1px dashed #ebeef5;
        }
      }
    }
  }
}
</style>
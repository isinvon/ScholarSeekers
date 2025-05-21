<template>
  <div class="profile-container">
    <div class="profile-header">
      <el-avatar :size="120" :src="user.avatar || 'https://dummyimage.com/100x100/999/fff'">
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
import { ref, computed } from 'vue'
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

// 计算属性
const displayName = computed(() => {
  return user.value.username || `用户_${user.value.id.slice(0, 6)}`
})

const genderText = computed(() => {
  const genderMap = { 0: '男', 1: '女' }
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
      user.value = res.data.user
    }
  } catch (error) {
    console.error('加载用户信息失败:', error)
  }
}

// 初始化加载
loadUserInfo()
</script>

<style lang="less" scoped>
.profile-container {
  .profile-header {
    position: relative;
    padding: 30px;
    background: linear-gradient(135deg, #f8f9fa 0%, #ffffff 100%);
    border-radius: 12px;
    margin-bottom: 40px;

    &::after {
      content: '';
      position: absolute;
      bottom: -20px;
      left: 50%;
      transform: translateX(-50%);
      width: 96%;
      height: 1px;
      background: #ebeef5;
    }
  }

  :deep(.el-descriptions) {
    margin-top: 40px;
    
    &__title {
      font-size: 20px;
      color: #303133;
      margin-bottom: 25px;
    }

    &__header {
      .el-descriptions__label {
        font-weight: 500;
        color: #606266;
      }
    }
  }

  .description-section {
    margin-top: 40px;
    padding: 25px;
    background: #f8fafc;
    border: 1px solid #e4e7ed;
    border-radius: 8px;

    h3 {
      font-size: 16px;
      color: #303133;
      margin-bottom: 15px;
    }
  }
}
</style>
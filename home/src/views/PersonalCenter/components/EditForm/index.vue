<template>
  <div class="edit-container">
    <el-form
        :model="formData"
        :rules="rules"
        label-width="100px"
        label-position="top"
    >
      <div class="form-section">
        <h3>基本信息</h3>

        <el-form-item label="用户名" prop="username">
          <el-input
              v-model="formData.username"
              disabled
              placeholder="系统生成用户名"
          />
        </el-form-item>

        <el-form-item label="显示名称" prop="nickname">
          <el-input
              v-model="formData.nickname"
              placeholder="请输入显示名称"
              maxlength="20"
              show-word-limit
          />
        </el-form-item>

        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="formData.sex">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>
      </div>

      <div class="form-section">
        <h3>联系信息</h3>

        <el-form-item label="手机号" prop="phone">
          <el-input
              v-model="formData.phone"
              placeholder="请输入手机号"
              maxlength="11"
          />
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
          <el-input
              v-model="formData.email"
              placeholder="请输入邮箱"
              type="email"
          />
        </el-form-item>
      </div>

      <div class="form-section">
        <h3>其他信息</h3>

        <el-form-item label="学院" prop="college">
          <el-input v-model="formData.college" placeholder="请输入学院名称"/>
        </el-form-item>

        <el-form-item label="年级" prop="grade">
          <el-input v-model="formData.grade" placeholder="请输入年级"/>
        </el-form-item>

        <el-form-item label="地址" prop="address">
          <el-input v-model="formData.address" placeholder="请输入详细地址"/>
        </el-form-item>

        <el-form-item label="个人简介" prop="introduction">
          <el-input
              v-model="formData.introduction"
              type="textarea"
              :rows="4"
              placeholder="介绍一下自己吧"
              maxlength="200"
              show-word-limit
          />
        </el-form-item>
      </div>

      <div class="form-actions">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" :loading="submitting" @click="handleSubmit">
          保存更改
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import {ref, onMounted} from "vue";
import {ElMessage} from "element-plus";
import dayjs from "dayjs";
import User from "@/api/user";

const formData = ref({
  id: null,
  username: "",
  nickname: "",
  sex: 0,
  phone: "",
  email: "",
  college: "",
  grade: "",
  address: "",
  introduction: "",
});

const rules = {
  phone: [
    {
      pattern: /^1[3-9]\d{9}$/,
      message: "请输入有效的手机号",
      trigger: "blur",
    },
  ],
  email: [{type: "email", message: "请输入有效的邮箱地址", trigger: "blur"}],
  introduction: [{max: 200, message: "不超过200个字符", trigger: "blur"}],
};

const submitting = ref(false);


const loadUserData = async () => {
  try {
    const res = await User.getUserInfo();
    if (res.code === 200) {
      const userData = res.user || {};

      formData.value = {
        id: userData.id, // 确保ID被正确赋值
        username: userData.username || '',
        nickname: userData.nickname || userData.username,
        sex: Number(userData.sex) ?? 0,
        phone: userData.phone || '',
        email: userData.email || '',
        college: userData.college || '',
        grade: userData.grade || '',
        address: userData.address || '',
        introduction: userData.introduction || ''
      };
    }
  } catch (error) {
    ElMessage.error('数据加载失败: ' + (error.message || error.msg))
  }
};

const handleSubmit = async () => {
  submitting.value = true;
  try {
    // 构造符合后端要求的参数
    const payload = {
      id: formData.value.id, // 必须包含ID
      username: formData.value.username,
      nickname: formData.value.nickname,
      sex: Number(formData.value.sex),
      phone: formData.value.phone || null,
      email: formData.value.email || null,
      college: formData.value.college || null,
      grade: formData.value.grade || null,
      address: formData.value.address || null,
      introduction: formData.value.introduction || null
    };

    const res = await User.editUserInfo(payload);
    if (res.code === 200) {
      ElMessage.success("更新成功");
      loadUserData();
    }
  } catch (error) {
    ElMessage.error(error.msg || "更新失败");
  } finally {
    submitting.value = false;
  }
};

const handleCancel = () => {
  // 返回上一页逻辑
};

onMounted(loadUserData);
</script>

<style lang="less" scoped>
@primary-color: #409eff;
@hover-color: #66b1ff;
@background: #f8fafc;
@border-color: #e4e7ed;

.edit-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 40px;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.05);

  .form-section {
    margin-bottom: 32px;
    padding: 28px;
    background: @background;
    border: 1px solid @border-color;
    border-radius: 10px;
    transition: all 0.25s cubic-bezier(0.4, 0, 0.2, 1);

    &:hover {
      transform: translateY(-2px);
      box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
    }

    h3 {
      font-size: 18px;
      font-weight: 600;
      color: #1a1a1a;
      margin-bottom: 28px;
      padding-bottom: 12px;
      border-bottom: 2px solid #ebedf0;
      position: relative;

      &::after {
        content: '';
        position: absolute;
        bottom: -2px;
        left: 0;
        width: 40px;
        height: 2px;
        background: @primary-color;
      }
    }

    .el-form-item {
      margin-bottom: 22px;

      &:last-child {
        margin-bottom: 0;
      }

      &__label {
        font-size: 14px;
        color: #606266;
        padding-bottom: 6px;
        font-weight: 500;
      }
    }
  }

  .form-actions {
    display: flex;
    justify-content: flex-end;
    gap: 16px;
    margin-top: 40px;
    padding-top: 32px;
    border-top: 1px solid #ebeef5;

    .el-button {
      min-width: 120px;
      height: 42px;
      border-radius: 8px;
      transition: all 0.25s ease;
      font-weight: 500;
      letter-spacing: 0.5px;

      &--primary {
        background: @primary-color;
        border-color: @primary-color;

        &:hover {
          background: @hover-color;
          transform: translateY(-1px);
          box-shadow: 0 4px 12px rgba(64, 158, 255, 0.3);
        }
      }

      &:active {
        transform: scale(0.98);
      }
    }
  }

  // 输入框增强样式
  :deep(.el-input) {
    &__inner {
      height: 42px;
      border-radius: 8px;
      padding: 0 16px;
      font-size: 14px;
      transition: all 0.2s ease;

      &::placeholder {
        color: #c0c4cc;
      }

      &:focus {
        border-color: @primary-color;
        box-shadow: 0 0 0 3px rgba(64, 158, 255, 0.15);
      }
    }

    &__count {
      bottom: -22px;
      right: 0;
    }
  }

  // 单选组样式优化
  :deep(.el-radio-group) {
    display: flex;
    gap: 24px;

    .el-radio {
      margin: 0;
      --el-radio-input-border-color-hover: @primary-color;

      &__label {
        font-size: 14px;
        padding-left: 8px;
      }

      &.is-checked {
        .el-radio__inner {
          border-color: @primary-color;
          background: @primary-color;
        }
      }
    }
  }

  // 文本域样式优化
  :deep(.el-textarea) {
    &__inner {
      min-height: 100px;
      padding: 12px 16px;
      line-height: 1.6;
      border-radius: 8px;
      resize: vertical;

      &:focus {
        border-color: @primary-color;
        box-shadow: 0 0 0 3px rgba(64, 158, 255, 0.15);
      }
    }

    &__count {
      bottom: -24px;
    }
  }

  @media (max-width: 768px) {
    padding: 24px;

    .form-section {
      padding: 20px;
      margin-bottom: 24px;

      h3 {
        font-size: 16px;
        margin-bottom: 20px;
      }
    }

    .form-actions {
      flex-direction: column;
      gap: 12px;

      .el-button {
        width: 100%;
      }
    }

    :deep(.el-radio-group) {
      flex-direction: column;
      gap: 12px;
    }
  }
}
</style>
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
.edit-container {
  .form-section {
    margin-bottom: 35px;
    padding: 25px;
    background: #f8fafc;
    border: 1px solid #e4e7ed;
    border-radius: 8px;
    transition: all 0.3s ease;

    &:hover {
      box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
    }

    h3 {
      font-size: 17px;
      color: #303133;
      margin-bottom: 25px;
      padding-bottom: 10px;
      border-bottom: 1px solid #ebeef5;
    }
  }

  .form-actions {
    display: flex;
    justify-content: flex-end;
    gap: 15px;
    margin-top: 40px;
    padding-top: 25px;
    border-top: 1px solid #ebeef5;

    .el-button {
      width: 120px;
      height: 38px;
      border-radius: 6px;
      transition: all 0.2s ease;

      &:active {
        transform: scale(0.98);
      }
    }
  }

  /* 增强输入框样式 */

  :deep(.el-input) {
    &__inner {
      border-radius: 6px;
      padding: 10px 15px;
      transition: border-color 0.2s ease;

      &:focus {
        border-color: #409eff;
        box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.1);
      }
    }
  }

  /* 单选组样式优化 */

  :deep(.el-radio-group) {
    .el-radio {
      margin-right: 25px;

      &__label {
        font-size: 14px;
      }
    }
  }
}
</style>

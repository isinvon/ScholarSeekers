<!--个人信息编辑-->
<template>
  <div class="edit-form">
    <el-form :model="formData" ref="editForm" label-width="100px">
      <el-form-item label="姓名">
        <el-input v-model="formData.name" placeholder="请输入姓名"></el-input>
      </el-form-item>

      <el-form-item label="邮箱">
        <el-input v-model="formData.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>

      <el-form-item label="手机号">
        <el-input v-model="formData.phone" placeholder="请输入手机号"></el-input>
      </el-form-item>

      <el-form-item label="性别">
        <div class="gender-tags">
          <el-tag
              v-for="(gender, index) in genders"
              :key="index"
              :type="formData.gender === gender ? (gender === '男' ? 'primary' : 'danger') : 'info'"
              :effect="formData.gender === gender ? 'dark' : 'light'"
              @click="selectGender(gender)"
              class="gender-tag"
          >
            {{ gender }}
          </el-tag>
        </div>
      </el-form-item>

      <el-form-item label="生日">
        <el-date-picker v-model="formData.birthday" type="date" placeholder="选择日期"></el-date-picker>
      </el-form-item>

      <el-form-item label="家庭住址">
        <el-input v-model="formData.address" placeholder="请输入家庭住址"></el-input>
      </el-form-item>

      <el-form-item label="个人介绍">
        <el-input
            type="textarea"
            v-model="formData.introduction"
            placeholder="请输入个人介绍"
        ></el-input>
      </el-form-item>

      <el-form-item label="标签">
        <el-input
            v-model="formData.tags"
            placeholder="请输入标签，用逗号分隔"
        ></el-input>
      </el-form-item>

      <!-- 操作按钮区域 -->
      <div class="form-actions">
        <el-button type="primary" size="small" class="save-btn" @click="saveChanges">保存</el-button>
        <el-button size="small" class="cancel-btn" @click="cancelEdit">取消</el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import RouteUtils from "@/utils/RouteUtils";

// 模拟用户数据，可以从props传入实际数据
const formData = ref({
  name: "刘禹锡",
  email: "123456789@qq.com",
  phone: "123456789",
  gender: "女", // 默认选择的性别
  birthday: "2002-10-03",
  address: "牡丹省樱花市雨花区莲花街道55号",
  introduction: "老子从不听天由命，尊从本心。",
  tags: ['好学生', '优秀开发者', '超牛黑客', 'Github大牛'],
});

const genders = ["男", "女"]; // 性别选项

const router = useRouter();

// 选择性别
const selectGender = (gender) => {
  formData.value.gender = gender; // 更新选中的性别
};

// 保存表单数据
const saveChanges = () => {
  // 在此处处理表单验证或提交逻辑
  ElMessage.success("个人信息已更新！");
  // 保存后返回个人中心
  RouteUtils.goBackToPreviousPageAndRefresh()
};

// 取消编辑，返回查看页面
const cancelEdit = () => {
  // 返回上个页面并且重新刷新
  ElMessage.warning("已取消编辑！");
  RouteUtils.goBackToPreviousPageAndRefresh()
};
</script>

<style scoped>
.edit-form {
  padding: 30px;
  background-color: #fafafa; /* 柔和背景色，保持简约风格 */
  border-radius: 10px; /* 圆角增加现代感 */
  border: 1px solid #ebebeb; /* 轻微边框，更具现代感 */
}

.el-form-item {
  margin-bottom: 20px; /* 增加表单项之间的间距，提升视觉舒适感 */
}

.gender-tags {
  display: flex; /* 使用flex布局 */
  gap: 10px; /* 标签间距 */
}

.gender-tag {
  cursor: pointer; /* 鼠标悬停时显示为指针 */
}

/* 保存按钮，使用简约的主色调 */
.save-btn {
  background-color: #409eff;
  border-color: #409eff;
  color: #fff;
  border-radius: 6px;
  width: 100px;
}

/* 取消按钮，采用幽灵按钮风格 */
.cancel-btn {
  background-color: transparent;
  border-color: #dcdfe6;
  color: #606266;
  border-radius: 6px;
  width: 100px;
}

/* 通用输入框样式 */
.el-input, .el-date-picker {
  width: 100%; /* 使表单输入框占满宽度 */
}
</style>

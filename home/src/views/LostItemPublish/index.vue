<template>
  <div class="lost-item-publish">
    <el-card shadow="hover" class="form-card">
      <el-form :model="form" ref="formRef" label-width="120px" label-position="top">
        <!-- 失物信息 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="失物名称" prop="itemName" :rules="[{ required: true, message: '请输入失物名称', trigger: 'blur' }]">
              <el-input v-model="form.itemName" placeholder="请输入失物名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="找到物品者" prop="finderName" :rules="[{ required: true, message: '请输入找到物品者姓名', trigger: 'blur' }]">
              <el-input v-model="form.finderName" placeholder="请输入找到物品者姓名"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="失物描述" prop="description" :rules="[{ required: true, message: '请输入失物描述', trigger: 'blur' }]">
          <el-input type="textarea" v-model="form.description" placeholder="请输入失物描述" rows="4"></el-input>
        </el-form-item>

        <!-- 联系方式 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系方式" prop="contactInfo" :rules="[{ required: true, message: '请输入联系方式', trigger: 'blur' }]">
              <el-input v-model="form.contactInfo" placeholder="请输入联系方式"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 图片上传 -->
        <el-form-item label="上传图片" prop="image">
          <el-upload
              list-type="picture-card"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :file-list="fileList"
              :limit="3"
              :on-exceed="handleExceed"
              class="image-uploader"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>

        <!-- 提交按钮 -->
        <el-form-item class="form-buttons">
          <el-row justify="end">
            <el-button type="primary" @click="submitForm" size="large" round>提交信息</el-button>
            <el-button @click="resetForm" size="large" round>重置</el-button>
          </el-row>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 图片预览 -->
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';

const form = reactive({
  itemName: '',
  description: '',
  finderName: '',
  contactInfo: '',
  image: null,
});

const fileList = ref([]);
const dialogImageUrl = ref('');
const dialogVisible = ref(false);

const handlePreview = (file) => {
  dialogImageUrl.value = file.url;
  dialogVisible.value = true;
};

const handleRemove = (file, fileList) => {
  console.log(file, fileList);
};

const handleExceed = () => {
  ElMessage.warning('最多上传3张图片');
};

const submitForm = () => {
  console.log('表单提交:', form);
};

const resetForm = () => {
  form.itemName = '';
  form.description = '';
  form.finderName = '';
  form.contactInfo = '';
  fileList.value = [];
};
</script>

<style scoped lang="less">
.lost-item-publish {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

.form-card {
  background-color: #fdfdfd;
  border-radius: 12px;
  padding: 24px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  transition: box-shadow 0.3s ease;

  &:hover {
    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
  }
}

.el-form-item {
  margin-bottom: 20px;
}

.el-input {
  border-radius: 8px;
}

.el-upload {
  display: flex;
  justify-content: flex-start;
  flex-wrap: nowrap;
}

.image-uploader .el-upload-list {
  display: flex;
  flex-direction: row;
  gap: 10px;
}

.el-button {
  margin-left: 10px;
}

.form-buttons {
  margin-top: 30px; /* 添加顶部间距，避免按钮贴近其他元素 */
}

.el-dialog__body img {
  border-radius: 8px;
}
</style>

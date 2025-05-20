<template>
  <div class="lost-item-publish">
    <el-card shadow="hover" class="form-card">
      <el-form :model="form" ref="formRef" :rules="rules" label-width="120px" label-position="top">
        <!-- 失物信息 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="物品标题" prop="title">
              <el-input v-model="form.title" placeholder="请输入物品标题"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="物品分类" prop="categoryId">
              <el-select
                  v-model="form.categoryId"
                  placeholder="请选择分类"
                  style="width: 100%"
                  clearable
              >
                <el-option
                    v-for="item in categoryList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="丢失地点" prop="lostLocation">
              <el-input v-model="form.lostLocation" placeholder="请输入丢失地点"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="丢失时间" prop="lostTime">
              <el-date-picker
                  v-model="form.lostTime"
                  type="datetime"
                  placeholder="选择日期时间"
                  style="width: 100%"
                  value-format="YYYY-MM-DD HH:mm:ss"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="详细描述" prop="description">
          <el-input
              type="textarea"
              v-model="form.description"
              placeholder="请输入详细描述"
              :rows="4"
              maxlength="500"
              show-word-limit
          ></el-input>
        </el-form-item>

        <!-- 联系方式 -->
        <el-form-item label="联系方式" prop="contactInfo">
          <el-input
              v-model="form.contactInfo"
              placeholder="请输入联系方式（电话/微信）"
          ></el-input>
        </el-form-item>

        <!-- 图片上传 -->
        <el-form-item label="物品图片">
          <el-upload
              :on-remove="handleRemove"
              list-type="picture-card"
              :file-list="fileList"
              :limit="3"
              :on-exceed="handleExceed"
              :on-success="handleUploadSuccess"
              :before-upload="beforeUpload"
              :action="uploadUrl"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>

        <!-- 提交按钮 -->
        <el-form-item class="form-buttons">
          <el-row justify="center">
            <el-button
                type="primary"
                @click="submitForm"
                size="large"
                round
                :loading="submitting"
            >
              提交信息
            </el-button>
            <el-button
                @click="resetForm"
                size="large"
                round
            >
              重置
            </el-button>
          </el-row>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from 'vue'
import {ElMessage} from 'element-plus'
import {useUserStore} from '@/store/user'
import {getCategoryList} from '@/api/category'
import {createLostItem} from '@/api/lostItem'
import dayjs from "dayjs";

// 用户信息
const userStore = useUserStore()

// 获取用户ID
const userId = userStore.userId

// 获取token
const token = userStore.token

// 表单数据
const form = reactive({
  title: '',
  categoryId: null,
  lostLocation: '',
  lostTime: '',
  description: '',
  contactInfo: '',
  imageUrl: ''
})

// 验证规则
const rules = reactive({
  title: [{required: true, message: '请输入物品标题', trigger: 'blur'}],
  categoryId: [{required: true, message: '请选择物品分类', trigger: 'change'}],
  lostLocation: [{required: true, message: '请输入丢失地点', trigger: 'blur'}],
  lostTime: [{required: true, message: '请选择丢失时间', trigger: 'change'}],
  description: [{required: true, message: '请输入详细描述', trigger: 'blur'}],
  contactInfo: [
    {required: true, message: '请输入联系方式', trigger: 'blur'},
    {pattern: /^1[3-9]\d{9}$|^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/, message: '格式不正确'}
  ]
})

// 分类数据
const categoryList = ref([])
// 图片列表
const fileList = ref([])
// 提交状态
const submitting = ref(false)
// 上传配置
const uploadUrl = `${process.env.VITE_API_BASE}/upload`

onMounted(async () => {
  try {
    // 添加查询参数，获取parentId为null的顶级分类
    const res = await getCategoryList({parentId: null})
    console.log('分类加载成功:', res)
    categoryList.value = res.rows // 注意若依默认返回数据字段是rows
  } catch (error) {
    ElMessage.error('获取分类失败')
    console.error('分类加载错误:', error)
  }
})

// 图片上传处理
const handleUploadSuccess = (response, file) => {
  if (response.code === 200) {
    // 若依默认上传返回格式
    const newUrl = response.url
    if (form.imageUrl) {
      form.imageUrl += ',' + newUrl
    } else {
      form.imageUrl = newUrl
    }
    fileList.value.push({url: newUrl})
  }
}

// 添加删除图片处理
const handleRemove = (file) => {
  const index = fileList.value.findIndex(f => f.url === file.url)
  if (index !== -1) {
    fileList.value.splice(index, 1)
    form.imageUrl = fileList.value.map(f => f.url).join(',')
  }
}

const submitForm = async () => {
  // 添加表单验证
  if (!formRef) return
  const valid = await formRef.validate()
  if (!valid) return

  submitting.value = true
  try {
    const payload = {
      ...form,
      publisherId: userStore.userId,
      status: 0,
      // 确保时间格式正确
      lostTime: form.lostTime ? dayjs(form.lostTime).format('YYYY-MM-DD HH:mm:ss') : null
    }

    const {code} = await createLostItem(payload)
    if (code === 200) {
      ElMessage.success('提交成功')
      resetForm()
    } else {
      ElMessage.error('提交失败')
    }
  } catch (error) {
    console.error('提交错误:', error)
    ElMessage.error(error.msg || '提交失败')
  } finally {
    submitting.value = false
  }
}

const beforeUpload = (file) => {
  const isImage = file.type.startsWith('image/')
  const isLt5M = file.size / 1024 / 1024 < 5

  if (!isImage) {
    ElMessage.error('只能上传图片文件')
    return false
  }
  if (!isLt5M) {
    ElMessage.error('图片大小不能超过5MB')
    return false
  }
  return true
}

const handleExceed = () => {
  ElMessage.warning('最多上传3张图片')
}

// 表单重置
const resetForm = () => {
  Object.assign(form, {
    title: '',
    categoryId: null,
    lostLocation: '',
    lostTime: '',
    description: '',
    contactInfo: '',
    imageUrl: ''
  })
  fileList.value = []
}
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

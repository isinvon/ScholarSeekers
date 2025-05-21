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
                  filterable
                  clearable
                  style="width: 100%"
              >
                <el-option
                    v-for="item in categoryList"
                    :key="item.id"
                    :label="item.fullName"
                    :value="item.id"
                >
                  <span style="float: left">{{ item.fullName }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">
      (ID: {{ item.id }})
    </span>
                </el-option>
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
              :http-request="customUpload"
              :before-upload="beforeUpload"
              :on-remove="handleRemove"
              :limit="3"
              :file-list="fileList"
              list-type="picture-card"
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
import {getCategoryList, getCategoryList1} from '@/api/category'
// 新增上传接口引用
import {createLostItem, uploadFile} from '@/api/lostItemPublish'

const formRef = ref(null)

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

// 修改分类数据加载逻辑
onMounted(async () => {
  try {

    console.log('开始加载分类数据...')

    // 添加try-catch块捕获异步错误
    // const res = await getCategoryList({
    //   parentId: null,
    //   pageSize: 1000,  // 获取所有分类
    //   pageNum: 1
    // })

    const res = await getCategoryList({})

    // 若依分页接口返回结构验证
    console.log('分类接口响应:', res) // 调试输出

    if (res.code === 200) {
      // 修正数据结构访问路径
      const rawData = res.rows || []

      // 处理前数据验证
      console.log('原始分类数据:', rawData)

      categoryList.value = processCategories(rawData)

      // 处理后数据验证
      console.log('处理后的分类数据:', categoryList.value)
    } else {
      ElMessage.error('分类数据加载失败: ' + (res.msg || '未知错误'))
    }
  } catch (error) {
    console.error('分类加载异常:', error)
    ElMessage.error('分类加载失败: ' + (error.message || '网络异常'))
  }
})



// 添加删除图片处理
const handleRemove = (file) => {
  const index = fileList.value.findIndex(f => f.url === file.url)
  if (index !== -1) {
    fileList.value.splice(index, 1)
    form.imageUrl = fileList.value.map(f => f.url).join(',')
  }
}


// 提交表单（优化版）
const submitForm = async () => {
  try {
    await formRef.value.validate()

    const payload = {
      ...form,
      publisherId: userStore.userId,
      status: 0,
      lostTime: form.lostTime || null,
      // 转换路径为相对地址
      imageUrl: form.imageUrl
    }

    const {code} = await createLostItem(payload)
    if (code === 200) {
      ElMessage.success('提交成功')
      resetForm()
    }
  } catch (error) {
    console.error('提交失败:', error)
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

const processCategories = (list) => {
  const categoryMap = new Map()

  // 建立ID映射
  list.forEach(item => {
    categoryMap.set(item.id, {
      ...item,
      fullName: item.name,
      level: 0
    })
  })

  // 构建层级名称
  categoryMap.forEach(item => {
    let parent = categoryMap.get(item.parentId)
    let current = item
    while (parent) {
      current.fullName = `${parent.name} / ${current.fullName}`
      parent = categoryMap.get(parent.parentId)
    }
  })

  return Array.from(categoryMap.values())
      .sort((a, b) => a.fullName.localeCompare(b.fullName))
}

// 图片上传处理（最终版）
const customUpload = async (options) => {
  try {
    // 关键修改2：直接传递文件对象，不手动创建FormData
    const res = await uploadFile(options.file)

    if (res.code === 200) {
      // 处理返回路径
      const newUrl = res.fileName // 使用fileName字段

      // 更新表单字段
      form.imageUrl = form.imageUrl
          ? `${form.imageUrl},${newUrl}`
          : newUrl

      // 更新文件列表显示
      fileList.value.push({
        uid: options.file.uid,
        name: options.file.name,
        url: newUrl
      })

      // 必须调用onSuccess回调
      options.onSuccess(res)
    }
  } catch (error) {
    // 错误处理
    ElMessage.error('上传失败: ' + (error.msg || error.message))
    // 必须调用onError回调
    options.onError(error)
  }
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
@import "./index";
</style>

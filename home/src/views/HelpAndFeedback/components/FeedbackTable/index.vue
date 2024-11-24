<!--反馈表格记录-->
<template>
  <div class="feedback-container">
    <!-- 提交反馈区域 -->
    <div class="feedback-form">
      <!--<h2 class="form-title">提交反馈</h2>-->
      <el-form :model="formData" ref="feedbackForm" label-width="80px">
        <el-form-item label="类型" prop="type">
          <el-select v-model="formData.type" placeholder="请选择反馈类型">
            <el-option label="功能问题" value="功能问题"/>
            <el-option label="界面优化" value="界面优化"/>
            <el-option label="其他反馈" value="其他反馈"/>
          </el-select>
        </el-form-item>

        <el-form-item label="内容" prop="content">
          <el-input type="textarea" v-model="formData.content" placeholder="简要描述您的反馈"/>
        </el-form-item>

        <el-form-item label="日期" prop="date">
          <el-date-picker v-model="formData.date" type="date" placeholder="选择日期"/>
        </el-form-item>

        <div class="submit-btn-container">
          <el-button type="primary" @click="submitFeedback" class="submit-btn">提交</el-button>
        </div>
      </el-form>
    </div>

    <!-- 反馈记录区域 -->
    <div class="feedback-table">
      <!--<h2 class="table-title">反馈记录</h2>-->
      <el-table
          :data="tableData"
          :row-class-name="tableRowClassName"
          style="width: 100%;"
          border
      >
        <el-table-column prop="date" label="日期" width="120"/>
        <el-table-column prop="type" label="类型" width="120">
          <template #default="scope">
            <el-tag
                :type="getTagType(scope.row.type)"
                size="small"
            >
              {{ scope.row.type }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="content" label="反馈内容"/>
      </el-table>
    </div>
  </div>
</template>

<script lang="js" setup>
import {ref} from 'vue'
import {ElMessage} from 'element-plus'

const formData = ref({
  type: '',
  content: '',
  date: ''
})

const tableData = ref([
  {
    date: '2024-10-01',
    type: '功能问题',
    content: '页面加载速度较慢🐢。',
  },
  {
    date: '2024-09-15',
    type: '界面优化',
    content: '建议优化表单的布局😡。',
  },
  {
    date: '2024-09-01',
    type: '功能问题',
    content: '页面加载速度较慢。',
  },
  {
    date: '2024-08-15',
    type: '其他反馈',
    content: '能不能把管理员辞退了😫🔥。',
  },
  {
    date: '2024-07-15',
    type: '其他反馈',
    content: '真是服了你们这网站也太🌶︎🐔了。',
  },
])

const tableRowClassName = ({rowIndex}) => rowIndex % 2 === 0 ? 'even-row' : ''

const getTagType = (type) => {
  const types = {
    '功能问题': 'danger',
    '界面优化': 'warning',
    '其他反馈': 'info',
  }
  return types[type] || 'info'
}

const formatDate = (date) => {
  return new Date(date).toLocaleDateString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit'
  }).replace(/\//g, '-')
}

const submitFeedback = () => {
  if (!formData.value.type || !formData.value.content || !formData.value.date) {
    ElMessage.error('请填写完整的反馈信息！')
    return
  }

  tableData.value.push({
    date: formatDate(formData.value.date),
    type: formData.value.type,
    content: formData.value.content
  })

  formData.value = {type: '', content: '', date: ''}

  ElMessage.success('反馈已提交！')
}
</script>

<style lang="less" scoped>
.feedback-container {
  display: flex;
  flex-direction: column;
  gap: 40px;
  padding: 20px;
}

.feedback-form, .feedback-table {
  background-color: #fff;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.06);
}

.form-title, .table-title {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 16px;
  color: #333;
}

.el-form-item__label {
  font-weight: 500;
  color: #666;
}

.el-input, .el-select, .el-date-editor {
  width: 100%;
}

.submit-btn-container {
  display: flex;
  justify-content: center;
  margin-top: 16px;
}

.submit-btn {
  padding: 10px 40px;
  background-color: #409eff;
  color: #fff;
  border-radius: 8px;
  font-size: 16px;
}

.even-row {
  background-color: #f9f9f9;
}

.el-tag {
  border-radius: 6px;
  padding: 4px 12px;
  font-size: 12px;
}

.feedback-table h2 {
  font-size: 18px;
  color: #333;
  margin-bottom: 20px;
}
</style>

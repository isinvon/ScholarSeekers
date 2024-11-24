<!--<template>-->

<!--  &lt;!&ndash; 分割线 &ndash;&gt;-->
<!--  <el-divider>个人认领记录</el-divider>-->

<!--  <el-table :data="tableData" height="250" style="width: 100%">-->
<!--    <el-table-column prop="date" label="Date" width="180"/>-->
<!--    <el-table-column prop="name" label="Name" width="180"/>-->
<!--    <el-table-column prop="address" label="Address"/>-->
<!--  </el-table>-->
<!--</template>-->

<!--<script lang="js" setup>-->
<!--const tableData = [-->
<!--  {-->
<!--    date: '2016-05-03',-->
<!--    name: 'Tom',-->
<!--    address: 'No. 189, Grove St, Los Angeles',-->
<!--  },-->
<!--  {-->
<!--    date: '2016-05-02',-->
<!--    name: 'Tom',-->
<!--    address: 'No. 189, Grove St, Los Angeles',-->
<!--  },-->
<!--  {-->
<!--    date: '2016-05-04',-->
<!--    name: 'Tom',-->
<!--    address: 'No. 189, Grove St, Los Angeles',-->
<!--  },-->
<!--  {-->
<!--    date: '2016-05-01',-->
<!--    name: 'Tom',-->
<!--    address: 'No. 189, Grove St, Los Angeles',-->
<!--  },-->
<!--  {-->
<!--    date: '2016-05-08',-->
<!--    name: 'Tom',-->
<!--    address: 'No. 189, Grove St, Los Angeles',-->
<!--  },-->
<!--  {-->
<!--    date: '2016-05-06',-->
<!--    name: 'Tom',-->
<!--    address: 'No. 189, Grove St, Los Angeles',-->
<!--  },-->
<!--  {-->
<!--    date: '2016-05-07',-->
<!--    name: 'Tom',-->
<!--    address: 'No. 189, Grove St, Los Angeles',-->
<!--  },-->
<!--]-->
<!--</script>-->


<!--<style scoped>-->
<!--@import url('index.less');-->

<!--</style>-->

<!--版本二-->


<!--<template>-->
<!--  <div class="claim-records">-->
<!--    &lt;!&ndash; 分割线 &ndash;&gt;-->
<!--    <el-divider>认领记录</el-divider>-->

<!--    &lt;!&ndash; 搜索框 &ndash;&gt;-->
<!--    <div class="search-bar">-->
<!--      <el-input v-model="searchQuery" placeholder="搜索记录" clearable />-->
<!--    </div>-->

<!--    &lt;!&ndash; 筛选和排序功能 &ndash;&gt;-->
<!--    <div class="filters">-->
<!--      <el-date-picker-->
<!--          v-model="dateRange"-->
<!--          type="daterange"-->
<!--          format="yyyy-MM-dd"-->
<!--          start-placeholder="开始日期"-->
<!--          end-placeholder="结束日期"-->
<!--      />-->
<!--      <el-select v-model="statusFilter" placeholder="选择状态">-->
<!--        <el-option label="全部" value="all" />-->
<!--        <el-option label="已认领" value="claimed" />-->
<!--        <el-option label="未认领" value="unclaimed" />-->
<!--      </el-select>-->
<!--      <el-button @click="exportData" type="primary">导出数据</el-button>-->
<!--    </div>-->

<!--    &lt;!&ndash; 表格 &ndash;&gt;-->
<!--    <el-table :data="paginatedData" height="400" style="width: 100%" stripe>-->
<!--      <el-table-column prop="date" label="认领日期" width="180" sortable />-->
<!--      <el-table-column prop="name" label="姓名" width="180" sortable />-->
<!--      <el-table-column prop="address" label="地址"/>-->
<!--      <el-table-column prop="status" label="状态" width="150">-->
<!--        <template #default="{ row }">-->
<!--          <el-tag :type="getStatusType(row.status)" disable-transitions>{{ row.status }}</el-tag>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="操作" width="150">-->
<!--        <template #default="scope">-->
<!--          <el-button @click="viewDetails(scope.row)" size="mini" type="primary">查看</el-button>-->
<!--          <el-button @click="editRecord(scope.row)" size="mini" type="success">编辑</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->

<!--    &lt;!&ndash; 分页 &ndash;&gt;-->
<!--    <el-pagination-->
<!--        @current-change="handlePageChange"-->
<!--        :current-page="currentPage"-->
<!--        :page-size="pageSize"-->
<!--        :total="filteredData.length"-->
<!--        layout="prev, pager, next"-->
<!--    />-->
<!--  </div>-->
<!--</template>-->

<!--<script lang="js" setup>-->
<!--import { ref, computed } from 'vue'-->
<!--import { ElMessageBox } from 'element-plus'-->

<!--const tableData = ref([-->
<!--  { date: '2016-05-03', name: 'Tom', address: 'No. 189, Grove St, Los Angeles', status: 'unclaimed' },-->
<!--  { date: '2016-05-02', name: 'Tom', address: 'No. 189, Grove St, Los Angeles', status: 'claimed' },-->
<!--  { date: '2016-05-04', name: 'Tom', address: 'No. 189, Grove St, Los Angeles', status: 'unclaimed' },-->
<!--  { date: '2016-05-01', name: 'Tom', address: 'No. 189, Grove St, Los Angeles', status: 'claimed' },-->
<!--  { date: '2016-05-08', name: 'Tom', address: 'No. 189, Grove St, Los Angeles', status: 'unclaimed' },-->
<!--  { date: '2016-05-06', name: 'Tom', address: 'No. 189, Grove St, Los Angeles', status: 'claimed' },-->
<!--  { date: '2016-05-07', name: 'Tom', address: 'No. 189, Grove St, Los Angeles', status: 'unclaimed' },-->
<!--])-->

<!--const searchQuery = ref('')-->
<!--const dateRange = ref([null, null])-->
<!--const statusFilter = ref('all')-->
<!--const currentPage = ref(1)-->
<!--const pageSize = ref(5)-->

<!--const filteredData = computed(() => {-->
<!--  return tableData.value.filter(item => {-->
<!--    const matchesQuery = item.name.toLowerCase().includes(searchQuery.value.toLowerCase()) ||-->
<!--        item.address.toLowerCase().includes(searchQuery.value.toLowerCase())-->
<!--    const matchesDateRange = dateRange.value[0] ?-->
<!--        new Date(item.date) >= new Date(dateRange.value[0]) && new Date(item.date) <= new Date(dateRange.value[1]) :-->
<!--        true-->
<!--    const matchesStatus = statusFilter.value === 'all' || item.status === statusFilter.value-->

<!--    return matchesQuery && matchesDateRange && matchesStatus-->
<!--  })-->
<!--})-->

<!--const paginatedData = computed(() => {-->
<!--  const start = (currentPage.value - 1) * pageSize.value-->
<!--  const end = start + pageSize.value-->
<!--  return filteredData.value.slice(start, end)-->
<!--})-->

<!--const handlePageChange = (page) => {-->
<!--  currentPage.value = page-->
<!--}-->

<!--const exportData = () => {-->
<!--  // Example implementation, use a library like xlsx for exporting-->
<!--  ElMessageBox.alert('导出功能暂未实现', '提示')-->
<!--}-->

<!--const viewDetails = (row) => {-->
<!--  ElMessageBox.alert(`查看记录: ${JSON.stringify(row)}`, '详细信息')-->
<!--}-->

<!--const editRecord = (row) => {-->
<!--  ElMessageBox.confirm(`编辑记录: ${JSON.stringify(row)}`, '确认', {-->
<!--    confirmButtonText: '确定',-->
<!--    cancelButtonText: '取消',-->
<!--  }).then(() => {-->
<!--    // Implement edit functionality here-->
<!--    ElMessageBox.alert('编辑功能暂未实现', '提示')-->
<!--  })-->
<!--}-->

<!--const getStatusType = (status) => {-->
<!--  return status === 'claimed' ? 'success' : 'warning'-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--@import url('index.less');-->

<!--.claim-records {-->
<!--  padding: 20px;-->
<!--}-->

<!--.search-bar {-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.filters {-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.el-table {-->
<!--  background-color: #fff;-->
<!--  border-radius: 4px;-->
<!--}-->

<!--.el-table th {-->
<!--  background-color: #f5f7fa;-->
<!--}-->

<!--.el-table td {-->
<!--  padding: 10px;-->
<!--}-->

<!--.el-table .el-button {-->
<!--  margin: 0;-->
<!--}-->
<!--</style>-->

<!--版本三-->
<template>
  <div class="claim-records">
    <!-- 筛选和排序功能 -->
    <div class="filters">
      <!-- 搜索框 -->
      <el-input class="search-bar" :size="'default'" v-model="searchQuery" placeholder="搜索记录" clearable/>
      <!--<SearchBox v-model="searchQuery" placeholder="搜索记录"/>-->
      <el-date-picker
          :size="'default'"
          v-model="dateRange"
          type="daterange"
          format="yyyy-MM-dd"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          size="small"
          class="filter-item"
      />

      <!-- 状态筛选下拉框-->
      <el-select :size="'default'" v-model="statusFilter" placeholder="选择状态" size="small" class="filter-item">
        <el-option label="全部" value="all"/>
        <el-option label="已认领" value="claimed"/>
        <el-option label="未认领" value="unclaimed"/>
      </el-select>
      
      <!-- 删除选中按钮 -->
      <el-button @click="deleteSelected" type="danger" size="default" :disabled="selectedRows.length === 0">删除选中
      </el-button>

      <!--导入数据按钮-->
      <el-button @click="importData" type="success" size="default">导入数据</el-button>

      <!--导出数据按钮 - 版本一 -->
      <!--<el-button @click="exportDataToCSV" type="primary" size="small">导出数据</el-button>-->
      <!--<el-button @click="exportDataToExcel" type="primary" size="small">导出数据</el-button>-->
      <!--导出数据按钮 - 版本二 -->
      <el-popconfirm
          title="选择导出格式"
          :icon="InfoFilled"
          @confirm="exportDataToCSV"
          @cancel="exportDataToExcel"
          :confirm-button-text="'CSV'"
          :confirm-button-type="'primary'"
          :cancel-button-text="'Excel'"
          :cancel-button-type="'success'"
          :show-icon="false"
      >
        <template #reference>
          <el-button type="primary" size="default">导出数据</el-button>
        </template>
        <template #actions="{ confirm, cancel }">
          <el-button @click="cancel" type="primary">导出为 CSV</el-button>
          <!--<el-button @click="confirm" :disabled="!clicked" type="danger">导出为 Excel</el-button>-->
          <el-button @click="confirm" type="success">导出为 Excel</el-button>
        </template>
      </el-popconfirm>
    </div>

    <!-- 表格 -->
    <el-table
        :data="paginatedData"
        height="400"
        style="width: 100%"
        stripe
        size="small"
        :row-class-name="rowClassName"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="date" label="发布日期" width="180" sortable/>
      <el-table-column prop="name" label="发布人" width="180" sortable/>
      <el-table-column prop="address" label="地址"/>
      <el-table-column prop="status" label="状态" width="150">
        <template #default="{ row }">
          <el-tag :type="getStatusType(row.status)" disable-transitions>{{ row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button @click="viewDetails(scope.row)" size="mini" type="primary">
            <i class="el-icon-view"></i> 查看
          </el-button>
          <el-button @click="editRecord(scope.row)" size="mini" type="success" disabled>
            <i class="el-icon-edit"></i> 编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
        @current-change="handlePageChange"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="filteredData.length"
        layout="prev, pager, next"
        class="pagination"
    />

    <!-- 详情对话框 -->
    <el-dialog :visible.sync="dialogVisible" title="详细信息">
      <div>
        <p><strong>发布日期:</strong> {{ currentRecord.date }}</p>
        <p><strong>发布人:</strong> {{ currentRecord.name }}</p>
        <p><strong>地址:</strong> {{ currentRecord.address }}</p>
        <p><strong>状态:</strong>
          <el-tag :type="getStatusType(currentRecord.status)">{{ currentRecord.status }}</el-tag>
        </p>
        <el-input v-model="currentRecord.comment" type="textarea" rows="4" placeholder="添加备注"/>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveDetails">保存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script lang="js" setup>
import {ref, computed} from 'vue'
import {ElMessageBox, ElMessage} from 'element-plus'
import * as XLSX from 'xlsx'
import {InfoFilled} from "@element-plus/icons-vue";
// import SearchBox from '@/components/SearchBox'


// 确认导出数据
const clicked = ref(false)

// 显示导出确认对话框
function onCancel() {
  clicked.value = true
}

const tableData = ref([
  {date: '2016-05-03', name: '张伟', address: '桂林理工大学科技楼', status: 'unclaimed', comment: ''},
  {date: '2016-05-02', name: '李娜', address: '桂林理工大学图书馆', status: 'claimed', comment: ''},
  {date: '2016-05-04', name: '王磊', address: '桂林理工大学实验楼', status: 'unclaimed', comment: ''},
  {date: '2016-05-01', name: '赵强', address: '桂林理工大学校医院', status: 'claimed', comment: ''},
  {date: '2016-05-08', name: '刘洋', address: '桂林理工大学体育馆', status: 'unclaimed', comment: ''},
  {date: '2016-05-06', name: '陈静', address: '桂林理工大学办公楼', status: 'claimed', comment: ''},
  {date: '2024-09-07', name: '杨敏', address: '桂林理工大学南门', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '黄莉', address: '桂林理工大学东门', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '周杰', address: '桂林理工大学西门', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '吴芳', address: '桂林理工大学北门', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '徐丽', address: '桂林理工大学研究生楼', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '冯涛', address: '桂林理工大学教学楼', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '何强', address: '桂林理工大学食堂', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '蔡飞', address: '桂林理工大学停车场', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '唐玲', address: '桂林理工大学科研楼', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '沈伟', address: '桂林理工大学活动中心', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '钟晓', address: '桂林理工大学青年之家', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '蒋刚', address: '桂林理工大学外语楼', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '潘雪', address: '桂林理工大学社团活动室', status: 'unclaimed', comment: ''},
  {date: '2024-09-07', name: '邓波', address: '桂林理工大学舞蹈教室', status: 'unclaimed', comment: ''},
])

const searchQuery = ref('')
const dateRange = ref([null, null])
const statusFilter = ref('all')
const currentPage = ref(1)
const pageSize = ref(9)
const selectedRows = ref([])
const dialogVisible = ref(false)
const currentRecord = ref({})

// 计算筛选后的数据
const filteredData = computed(() => {
  return tableData.value.filter(item => {
    const matchesQuery = item.name.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
        item.address.toLowerCase().includes(searchQuery.value.toLowerCase())
    const matchesDateRange = dateRange.value[0] ?
        new Date(item.date) >= new Date(dateRange.value[0]) && new Date(item.date) <= new Date(dateRange.value[1]) :
        true
    const matchesStatus = statusFilter.value === 'all' || item.status === statusFilter.value

    return matchesQuery && matchesDateRange && matchesStatus
  })
})

// 计算分页后的数据
const paginatedData = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value
  const end = start + pageSize.value
  return filteredData.value.slice(start, end)
})

// 分页变化时触发
const handlePageChange = (page) => {
  currentPage.value = page
}

// 导出为Excel文件 （需要导入Excel包：npm install xlsx --save）
const exportDataToExcel = () => {
  const ws = XLSX.utils.json_to_sheet(filteredData.value)
  const wb = XLSX.utils.book_new()
  XLSX.utils.book_append_sheet(wb, ws, 'Claim Records')
  XLSX.writeFile(wb, 'claim_records.xlsx')
}
// 导出为CSV文件
const exportDataToCSV = () => {
  const csvContent = "data:text/csv;charset=utf-8,"
      + filteredData.value.map(e => Object.values(e).join(",")).join("\n");

  const encodedUri = encodeURI(csvContent);
  const link = document.createElement("a");
  link.setAttribute("href", encodedUri);
  link.setAttribute("download", "claim_records.csv");
  document.body.appendChild(link);
  link.click();
}

// 导入数据
const importData = () => {
  ElMessageBox.alert('导入功能暂未实现', '提示')
}

// 删除选中的记录
const deleteSelected = () => {
  ElMessageBox.confirm('确定删除选中的记录吗？', '确认删除', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
  }).then(() => {
    tableData.value = tableData.value.filter(item => !selectedRows.value.includes(item))
    ElMessage.success('删除成功')
  })
}

// 查看详细信息
const viewDetails = (row) => {
  currentRecord.value = {...row}
  dialogVisible.value = true
}

// 保存详细信息
const saveDetails = () => {
  const index = tableData.value.findIndex(item => item.date === currentRecord.value.date && item.name === currentRecord.value.name)
  if (index !== -1) {
    tableData.value[index] = {...currentRecord.value}
    ElMessage.success('保存成功')
    dialogVisible.value = false
  }
}

// 编辑记录
const editRecord = (row) => {
  ElMessageBox.confirm(`编辑记录: ${JSON.stringify(row)}`, '确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
  }).then(() => {
    // Implement edit functionality here
    ElMessage.success('编辑功能暂未实现')
  })
}

// 获取状态类型
const getStatusType = (status) => {
  return status === 'claimed' ? 'success' : 'warning'
}

// 选择的记录
const handleSelectionChange = (rows) => {
  selectedRows.value = rows
}

// 行样式
const rowClassName = ({row}) => {
  return row.status === 'claimed' ? 'claimed-row' : ''
}
</script>

<style scoped>
@import url('index.less');

.claim-records {
  //padding: 20px;
}


.search-bar {
  width: 400px;
}

.filters {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 20px;
}

.filter-item {
  margin-right: 10px;
  width: 200px;
}

.el-table {
  background-color: #fff;
  border-radius: 4px;
  border: 1px solid #dcdfe6;
}

.el-table th {
  background-color: #f5f7fa;
  color: #606266;
  font-weight: bold;
}

.el-table td {
  padding: 10px;
}

.el-table .el-button {
  margin: 0 5px;
}

.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

.claimed-row {
  background-color: #f0f9eb;
}

</style>

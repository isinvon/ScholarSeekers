<!-- 认领记录表格 -->
<!--<template>-->
<!--  <el-table :data="tableData" height="250" style="width: 100%">-->
<!--    <el-table-column prop="date" label="Date" width="180" />-->
<!--    <el-table-column prop="name" label="Name" width="180" />-->
<!--    <el-table-column prop="address" label="Address" />-->
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
<!--@import url('./index.less');-->

<!--</style>-->

<template>
  <div>
    <!-- 校园失物认领记录表格 -->
    <el-table :data="tableData" height="245" style="width: 100%" stripe>
      <el-table-column prop="claimDate" label="认领日期" width="160" sortable />
      <el-table-column prop="itemName" label="失物名称" width="180" />
      <el-table-column prop="pickLocation" label="拾取地点" width="200" />
      <el-table-column prop="claimer" label="认领人" width="180" />
      <el-table-column prop="itemDescription" label="失物描述" />

      <!-- 操作列 -->
      <el-table-column label="操作" width="160">
        <template #default="scope">
          <el-button @click="viewDetails(scope.row)" type="primary" size="small">查看详情</el-button>
          <!--<el-button @click="confirmClaim(scope.row)" type="success" size="small">确认认领</el-button>-->
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';

// 模拟数据：校园失物认领记录
const tableData = ref([
  { claimDate: '2023-09-01', itemName: '钥匙', pickLocation: '教学楼前', claimer: '张三', itemDescription: '银色钥匙扣，带3把钥匙' },
  { claimDate: '2023-09-02', itemName: '钱包', pickLocation: '食堂', claimer: '李四', itemDescription: '黑色皮夹，内有校园卡' },
  { claimDate: '2023-09-03', itemName: '雨伞', pickLocation: '图书馆', claimer: '王五', itemDescription: '蓝色折叠伞' },
  { claimDate: '2023-09-04', itemName: '水杯', pickLocation: '操场', claimer: '赵六', itemDescription: '绿色运动水杯' },
  { claimDate: '2023-09-05', itemName: '耳机', pickLocation: '自习室', claimer: '钱七', itemDescription: '白色无线耳机' },
  { claimDate: '2023-09-06', itemName: '书包', pickLocation: '宿舍楼', claimer: '孙八', itemDescription: '黑色双肩包' },
  { claimDate: '2023-09-07', itemName: '手表', pickLocation: '校医院', claimer: '周九', itemDescription: '银色金属表带手表' },
]);
// 查看详情功能
const viewDetails = (row) => {
  ElMessage.info(`查看详情: ${row.itemName} - ${row.itemDescription}`);
};

// 确认认领功能
const confirmClaim = (row) => {
  ElMessage.success(`${row.claimer} 已认领 ${row.itemName}`);
};
</script>

<style lang="less" scoped>
/* 美化表格 */
//.el-table {
//  background-color: #f9f9f9;
//  border-radius: 10px;
//  overflow: hidden;
//  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
//}

.el-table th {
  background-color: #f2f2f2;
  color: #333;
  font-weight: bold;
}

.el-table .el-button {
  margin: 0 5px;
}

.el-pagination {
  display: flex;
  justify-content: center;
}

.el-message {
  margin-top: 10px;
}
</style>

<!--最近事件-->
<template>
  <el-table :data="tableData" style="width: 100%">
    <!-- Name 列添加点击链接 -->
    <el-table-column prop="name" label="Name">
      <template #default="scope">
        <el-link type="primary" :href="scope.row.link" target="_blank">{{ scope.row.name }}</el-link>
      </template>
    </el-table-column>
    <!-- Date 列保持不变 -->
    <el-table-column prop="date" label="Date" width="100"/>
  </el-table>
</template>
<script lang="js" setup>


const tableData = [
  {
    date: '2016-05-03',
    name: '北京大学召开校1领导班子主题教育调研成果交流会',
    link: 'https://www.baidu.com'
  },
  {
    date: '2016-05-02',
    name: '北京大学召开校领导班子主题教育调研成果交流会',
    link: 'https://www.baidu.com'
  },
  {
    date: '2016-05-04',
    name: '北大党委理论学习中心组开展主题教育专题民主生活会会前集中学习',
    link: "https://www.baidu.com"
  },
  {
    date: '2016-05-01',
    name: '北京大学第53期干部研讨班开班式、集中培训与理想信念实践教育举行',
    link: 'https://www.baidu.com'
  },
  {
    date: '2016-06-01',
    name: '文气涵正气，诗心诵丹心——人文学部深入开展学生党团日联合主题教育',
    link: 'https://www.baidu.com'
  },
]
</script>

<style scoped>
@import url('./index.less');

</style>
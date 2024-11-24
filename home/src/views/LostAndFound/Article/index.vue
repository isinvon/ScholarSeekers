<template>
  <div class="common-layout">
    <el-row>
      <el-col :span="24">
        <div class="grid-content ep-bg-purple"/>
        <div class="issue-header">
          <!--文章标题-->
          <h1 class="issue-title">
            <!--文章标题-->
            <span class="issue-title-text">
                <i class="el-icon-document"/>
                <span class="issue-title-text">这是一篇好文章</span>
            </span>
          </h1>
          <!--文章信息-->
          <div class="issue-info">
            <!--文章状态（标签）-->
            <el-tag class="issue-tag" size="large" type="success" effect="dark" round>
              <span class="issue-tag-text">公开</span>
            </el-tag>
            <!--文章信息-->
            <span class="issue-info-item">
              <i class="el-icon-user-solid"/>
              <span class="issue-info-text">作者：</span>
              <span class="issue-info-text">sinvon</span>
            </span>
            <el-divider direction="vertical"/>
            <span class="issue-info-item">
              <i class="el-icon-date"/>
              <span class="issue-info-text">发布时间：</span>
             <span class="issue-info-text">2023-03-01</span>
            </span>
            <el-divider direction="vertical"/>
            <!--编辑按钮-->
            <el-button class="issue-edit" type="text" size="default" style="border-radius: 100px">编辑</el-button>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <!--<el-col :span="4">-->
      <!--</el-col>-->
      <!--左边-->
      <el-col :span="18">
        <div class="grid-content ep-bg-purple"/>
        <!--文章主体-->
        <ArticleMain/>
      </el-col>
      <!--右边-->
      <el-col :span="6">
        <div class="grid-content ep-bg-purple-light"/>
        <!--描述卡片-->
        <RightAside/>
      </el-col>
      <!--<el-col :span="4">-->
      <!--</el-col>-->
    </el-row>
  </div>

</template>

<script lang="js" setup>
import RightAside from './RightAside'
import ArticleMain from './ArticleMain/index.vue'

</script>


<style scoped lang="less">
.issue-header {
  //display: flex;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 15px;
  padding-bottom: 15px;
  align-items: center; // 垂直居中
  //justify-content: space-between; // 两边对齐
  justify-content: start; // 左对齐
  .issue-title {
    display: flex;
    justify-content: start;
    margin: 0;
    font-size: 36px;
    font-weight: normal;
  }

  .issue-info {
    display: flex;
    padding-top: 5px;
    justify-content: start; // 左对齐
    align-items: center;
    font-size: 14px;
    gap: 10px;

  }

  // 边框
  border-bottom: 1px solid #e9e9e9;
}

.common-layout {
}

.el-row {
  //margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  //min-height: 36px;
}

// 弹性布局
.flex-line {
  flex: 1;
}
</style>
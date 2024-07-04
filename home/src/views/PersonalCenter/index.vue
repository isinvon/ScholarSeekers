<template>
  <div>
    <!-- 使用v-if控制个人资料表单的显示 -->
    <PersonalForm v-if="!isEditing" style="padding-top: 50px"/>

    <!-- router-view根据编辑状态显示或隐藏 -->
    <router-view v-if="isEditing"></router-view>

    <!--换一行-->
    <br>

    <!-- 编辑按钮，仅在未编辑时显示 -->
    <el-button v-if="!isEditing" type="primary" @click="edit">
      编辑
    </el-button>

    <!-- 返回按钮，仅在编辑模式时显示 -->
    <el-button v-if="isEditing" type="primary" @click="returnToProfile">
      返回
    </el-button>

    <!-- 分割线 -->
    <el-divider>认领记录</el-divider>

    <!-- 认领记录 -->
    <LostAndFoundRecord/>
  </div>
</template>

<script setup>
import './index.less'
import PersonalForm from './components/PersonalForm';
import LostAndFoundRecord from "./components/LostAndFoundRecord";
import {useRouter} from 'vue-router';
import {ref} from "vue";

const router = useRouter();
const isEditing = ref(false); // 新增一个响应式变量来控制编辑状态

const edit = () => {
  // 切换到编辑状态，隐藏表单，显示编辑界面
  isEditing.value = true;
  // 跳转到编辑页面逻辑可以保留，但根据情况可能不需要
  router.push('/personalCenter/edit');
};

const returnToProfile = () => {
  // 返回到查看个人资料状态，显示表单，隐藏编辑界面
  isEditing.value = false;
  // 如果需要，可以在此处添加逻辑来处理返回时的路由变化
  router.push('/personalCenter');
};
</script>


<style lang="less" scoped>

</style>
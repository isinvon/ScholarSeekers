<!--<template>-->
<!--  <div>-->
<!--    <el-form ref="replyFormRef" :model="replyForm" :rules="replyRules" label-width="80px">-->
<!--      <el-form-item prop="replyContent">-->
<!--        <el-input v-model="replyForm.replyContent" placeholder="请输入回复内容"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" @click="submit">提交</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
<!--  </div>-->
<!--</template>-->

<!--<script setup>-->
<!--import { ref, reactive } from 'vue';-->

<!--const emit = defineEmits(['submit']);-->

<!--const replyForm = reactive({ replyContent: '' });-->
<!--const replyFormRef = ref(null);-->
<!--const replyRules = reactive({-->
<!--  replyContent: [{ required: true, message: '请输入回复内容', trigger: 'blur' }],-->
<!--});-->

<!--const submit = () => {-->
<!--  replyFormRef.value.validate(valid => {-->
<!--    if (valid) {-->
<!--      emit('submit', replyForm);-->
<!--      replyForm.replyContent = '';-->
<!--    }-->
<!--  });-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--/* 样式 */-->
<!--</style>-->

<template>
  <el-form @submit.native.prevent="submit">
    <el-input
        type="textarea"
        v-model="content"
        placeholder="请输入回复内容..."
        :autosize="{ minRows: 4, maxRows: 8 }"
    ></el-input>
    <el-button type="primary" native-type="submit">回复</el-button>
  </el-form>
</template>

<script setup>
import './index.less'

import {ref} from 'vue';

const content = ref('');

const emit = defineEmits(['submit']);

const submit = () => {
  if (content.value.trim() !== '') {
    emit('submit', content.value);
    content.value = '';
  }
};
</script>

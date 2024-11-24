<!--个人资料表单-->
<template>
  <!--控制卡片大小按钮-->
  <!--<el-radio-group v-model="size" style="float: right">-->
  <!--  <el-radio value="large">Large</el-radio>-->
  <!--  <el-radio value="default">Default</el-radio>-->
  <!--  <el-radio value="small">Small</el-radio>-->
  <!--</el-radio-group>-->
  <div class="cell-item">
  </div>
  <!--阴影环绕-->
  <el-avatar class="avatar" :size="100" :src="props.data.avatar"/>
  <el-descriptions
      class="margin-top"
      title=""
      :column="1"
      :size="size"
      border
  >
    <!--操作按钮-->
    <!--<template #extra>-->
    <!--  <el-button type="primary">Operation</el-button>-->
    <!--</template>-->

    <!--圆形头像-->
    <!--<el-descriptions-item>-->
    <!--  <template #label>-->
    <!--    <div class="cell-item">-->
    <!--      &lt;!&ndash;<el-icon :style="iconStyle">&ndash;&gt;-->
    <!--      &lt;!&ndash;  <user/>&ndash;&gt;-->
    <!--      &lt;!&ndash;</el-icon>&ndash;&gt;-->
    <!--      &lt;!&ndash;头像&ndash;&gt;-->
    <!--    </div>-->
    <!--  </template>-->
    <!--  <el-avatar :size="100" :src="props.data.avatar"/>-->
    <!--</el-descriptions-item>-->
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          姓名
        </div>
      </template>
      {{ props.data.name }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          性别
        </div>
      </template>
      <!--标签：如果性别为女,则显示粉色♀,否则显示蓝色♂-->
      <el-tag v-if="props.data.sex === '女'" size="small" type="danger">♀</el-tag>
      <el-tag v-else size="small" type="primary">♂</el-tag>
      {{ props.data.sex }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          年龄
        </div>
      </template>
      {{ props.data.age }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          出生日期
        </div>
      </template>
      {{ props.data.birthdate }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <iphone/>
          </el-icon>
          手机号
        </div>
      </template>
      {{ props.data.phone }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          邮箱
        </div>
      </template>
      {{ props.data.email }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <tickets/>
          </el-icon>
          学院
        </div>
      </template>
      <el-tag size="small">{{ props.data.college }}</el-tag>
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <tickets/>
          </el-icon>
          专业班级
        </div>
      </template>
      <el-tag size="small">{{ props.data.major }} {{ props.data.class }}</el-tag>
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          标签
        </div>
      </template>
      <!--props.data.tags是一个['nice', 'developer']类型的数组,将其遍历渲染为标签-->
      <el-tag v-for="(tag, index) in props.data.tags" :key="index" style="margin-right: 10px">
        {{ tag }}
      </el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <office-building/>
          </el-icon>
          家庭住址
        </div>
      </template>
      {{ props.data.address }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          个人介绍
        </div>
      </template>
      {{ props.data.introduction }}
    </el-descriptions-item>
  </el-descriptions>

</template>

<script setup lang="js">

import {computed, ref} from 'vue'
import {
  Iphone,
  Location,
  OfficeBuilding,
  Tickets,
  User,
} from '@element-plus/icons-vue'

const props = defineProps({
  data: {
    type: Object,
    default: () => ({
      name: '刘禹锡',
      age: 18,
      sex: '女',
      college: '计算机科学与工程学院',
      major: '网络工程',
      class: '21-2班',
      address: '牡丹省樱花市雨花区莲花街道55号',
      birthdate: '2002-10-03',
      tags: ['好学生', '优秀开发者', '超牛黑客', 'Github大牛'],
      phone: '123456789',
      email: '123456789@qq.com',
      introduction: '老子从不听天由命，尊从本心。',
      avatar: 'https://picsum.photos/200/200',
    }),
  },
})

const size = ref('default')

const iconStyle = computed(() => {
  const marginMap = {
    large: '8px',
    default: '6px',
    small: '4px',
  }
  return {
    marginRight: marginMap[size.value] || marginMap.default,
  }
})
const blockMargin = computed(() => {
  const marginMap = {
    large: '32px',
    default: '28px',
    small: '24px',
  }
  return {
    marginTop: marginMap[size.value] || marginMap.default,
  }
})
</script>

<style lang="less" scoped>
@import url('./index.less');

</style>

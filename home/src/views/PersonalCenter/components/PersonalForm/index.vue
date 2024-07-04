<!--个人资料表单-->
<template>
  <!--控制卡片大小按钮-->
  <!--<el-radio-group v-model="size" style="float: right">-->
  <!--  <el-radio value="large">Large</el-radio>-->
  <!--  <el-radio value="default">Default</el-radio>-->
  <!--  <el-radio value="small">Small</el-radio>-->
  <!--</el-radio-group>-->

  <el-descriptions
      class="margin-top"
      title=""
      :column="3"
      :size="size"
      border
  >
    <!--操作按钮-->
    <!--<template #extra>-->
    <!--  <el-button type="primary">Operation</el-button>-->
    <!--</template>-->

    <!--圆形头像-->
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <!--<el-icon :style="iconStyle">-->
          <!--  <user/>-->
          <!--</el-icon>-->
          <!--头像-->
        </div>
      </template>
      <el-avatar :size="100" :src="props.data.avatar"/>
    </el-descriptions-item>
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
            <location/>
          </el-icon>
          坐标
        </div>
      </template>
      {{ props.data.place }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <tickets/>
          </el-icon>
          单位
        </div>
      </template>
      <el-tag size="small">{{ props.data.unit }}</el-tag>
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
            <tickets/>
          </el-icon>
          职业
        </div>
      </template>
      <el-tag size="small">{{ props.data.career }}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <tickets/>
          </el-icon>
          部门
        </div>
      </template>
      <el-tag size="small">{{ props.data.department }}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <tickets/>
          </el-icon>
          工作时间
        </div>
      </template>
      {{ props.data.workTime }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <tickets/>
          </el-icon>
          学历
        </div>
      </template>
      <el-tag size="small">{{ props.data.education }}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          专业
        </div>
      </template>
      {{ props.data.major }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          毕业时间
        </div>
      </template>
      {{ props.data.graduation }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          毕业院校
        </div>
      </template>
      {{ props.data.graduationSchool }}
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
            <user/>
          </el-icon>
          网站
        </div>
      </template>
      {{ props.data.website }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          网站
        </div>
      </template>
      {{ props.data.website }}
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

    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          个人介绍
        </div>
      </template>
      <el-tag v-for="(tag, index) in props.data.title" :key="index" style="margin-right: 10px">
        {{ tag }}
      </el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user/>
          </el-icon>
          公司
        </div>
      </template>
      {{ props.data.company }}
    </el-descriptions-item>

  </el-descriptions>

  <!--<el-descriptions-->
  <!--    class="margin-top"-->
  <!--    title="Without border"-->
  <!--    :column="3"-->
  <!--    :size="size"-->
  <!--    :style="blockMargin"-->
  <!--&gt;-->
  <!--  <template #extra>-->
  <!--    <el-button type="primary">Operation</el-button>-->
  <!--  </template>-->
  <!--  <el-descriptions-item label="Username">kooriookami</el-descriptions-item>-->
  <!--  <el-descriptions-item label="Telephone">18100000000</el-descriptions-item>-->
  <!--  <el-descriptions-item label="Place">Suzhou</el-descriptions-item>-->
  <!--  <el-descriptions-item label="Remarks">-->
  <!--    <el-tag size="small">School</el-tag>-->
  <!--  </el-descriptions-item>-->
  <!--  <el-descriptions-item label="Address">-->
  <!--    No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province-->
  <!--  </el-descriptions-item>-->
  <!--</el-descriptions>-->
</template>

<script setup lang="js">
import './index.less'
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
      sex: '男',
      career: '前端开发工程师',
      department: '前端组',
      workTime: '2021-01-01',
      education: '本科',
      major: '计算机科学与技术',
      graduation: '2021-01-01',
      graduationSchool: '上海大学',
      address: '牡丹省樱花市雨花区莲花街道55号',
      place: '苏州',
      birthdate: '2002-10-03',
      tags: ['好学生', '优秀开发者', '超牛黑客', 'Github大牛'],
      icon: 'el-icon-edit',
      phone: '123456789',
      email: '123456789@qq.com',
      website: 'https://www.baidu.com',
      introduction: '老子从不听天由命，尊从本心。',
      // avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      avatar: 'https://picsum.photos/200/200',
      title: ['CEO', '马拉松冠军', 'Github之星', 'Lin语言创立者'],
      unit: '学校',
      company: 'Alibaba',
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

</style>

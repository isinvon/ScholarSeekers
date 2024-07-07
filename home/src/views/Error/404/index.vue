<template>
  <div class="container">
    <h1 id="text" @click="ClickUtils.click(remainBoxElement)" style="font-size: 200px;font-family: Alef;">404</h1>
    <!--很抱歉，您访问的页面不存在-->
    <h5 style="margin-top: -150px">很抱歉，您访问的页面不存在...</h5>
    <!--返回首页-->
    <el-button type="info" style="margin-top: 25px;border-radius: 15px;box-shadow: white 0 0 15px;"
               @click="() => RouteUtils.goToHomePage()">
      返回首页
    </el-button>
    <!--抽象碎碎念 -->
    <!--不显示RemainBox组件, 供id="text"调用-->
    <RemainBox id="remain-box" :messages="notifications" style="display: none"/>
  </div>
</template>

<script setup>
import './index.less';
import {onMounted, ref} from "vue";
import RemainBox from './components/RemainBox';
import {notification404PageMenu} from "@/menuData/notificationMenu";
import RouteUtils from "@/utils/RouteUtils";
import ClickUtils from '@/utils/ClickUtils'

const notifications = ref([...notification404PageMenu]);

let remainBoxElement = ref(null);
// 通过id="remain-box"获取元素
onMounted(() => {
  remainBoxElement.value = document.getElementById('remain-box');
})

onMounted(() => {
  const maxShadowLength = 10; // 阴影最大长度
  const blurRadius = 10; // 阴影模糊半径
  const textElement = document.getElementById('text');
  const viewportSize = {width: window.innerWidth, height: window.innerHeight};

  document.addEventListener('mousemove', function (event) {
    const centerX = viewportSize.width / 2;
    const centerY = viewportSize.height / 2;
    const mouseX = event.clientX;
    const mouseY = event.clientY;

    // 计算鼠标与页面中心的直线距离，模拟太阳角度
    const distanceFromCenter = Math.sqrt(Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2));
    const maxDistance = Math.sqrt(viewportSize.width ** 2 + viewportSize.height ** 2) / 2; // 页面对角线的一半

    // 随着鼠标远离中心，阴影长度线性增加
    let shadowLength = distanceFromCenter / maxDistance * maxShadowLength;
    shadowLength = Math.min(shadowLength, maxShadowLength); // 确保阴影长度不超过最大值

    // 阴影方向，假设光源在页面中心上方，阴影与鼠标位置相反
    const offsetX = -(mouseX - centerX) / maxDistance * shadowLength;
    const offsetY = -(mouseY - centerY) / maxDistance * shadowLength;

    // 应用动态阴影，减小模糊距离以提高清晰度
    textElement.style.textShadow = `${offsetX}px ${offsetY}px ${blurRadius}px rgba(255, 255, 255, ${Math.min(shadowLength / maxShadowLength, 1)})`;
  });

  document.addEventListener('mouseleave', function () {
    textElement.style.textShadow = 'none';
  });
})


</script>

<style lang="less" scoped>
</style>
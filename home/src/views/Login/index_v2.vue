<template>
  <div class="container">
    <div class="header">
      <!--<img src="@/assets/logo.png" alt="Brand Logo" class="logo"/> &lt;!&ndash; 添加品牌 Logo &ndash;&gt;-->
      <!-- 欢迎信息 -->
      <!--打字机-->
      <Typewriter/>
    </div>
    <div class="form-container">
      <!-- 根据当前状态决定显示 LoginCard 还是 RegisterCard (这种写法无法正常显示，未知原因)-->
      <!--<component :is="isLogin ? 'LoginCard' : 'RegisterCard'" />-->
      <!-- 根据当前状态决定显示 LoginCard 还是 RegisterCard、FindPasswordCard -->
      <RegisterCard v-if="currentCard === 'register'"/><!-- 默认显示 RegisterCard -->
      <LoginCard v-if="currentCard === 'login'"/><!-- LoginCard 默认不显示 -->
      <FindPasswordCard v-if="currentCard === 'findPassword'"/> <!--Find Password Card 默认不显示-->
    </div>
    <!--后续扩展第三方登录-->
    <!--<div class="social-login">-->
    <!--  <el-button type="text">使用微信登录</el-button>-->
    <!--  <el-button type="text">使用QQ登录</el-button>-->
    <!--</div>-->
    <div class="footer-links">
      <!--登录页面显示：1、注册账户 2、忘记密码-->
      <div v-if="currentCard=== 'login'">
        <el-button href="#" class="el-button register-button" color="#626aef" :dark="isDark" plain
                   @click="showCard('register')">注册账户
        </el-button>
        <!--间隔-->
        <span style="margin-left: 20px"></span>
        <el-button href="#" class="el-button find-password-button" color="#626aef" :dark="isDark" plain
                   @click="showCard('findPassword')">忘记密码
        </el-button>
      </div>
      <!--注册页面显示：1、立即登录-->
      <el-button v-if="currentCard=== 'register'" href="#" class="el-button login-link" color="#626aef" :dark="isDark"
                 plain

                 @click="showCard('login')">已有账号？立刻登录
      </el-button>
      <!--找回密码页面显示：1、立即登录-->
      <el-button v-if="currentCard=== 'findPassword'" href="#" color="#626aef" :dark="isDark" plain
                 class="el-button login-link"
                 @click="showCard('login')">返回登录
      </el-button>
    </div>
    <div class="copyright">© 2024 Sinvon | Powered by Vue3. All rights reserved.</div>
  </div>
</template>

<script setup>
//动态切换暗色模式
import {useDarkMode} from '@/composables/useDarkMode';
import {ref, onMounted} from 'vue';
import LoginCard from "@/views/Login/LoginCard/index.vue";
import RegisterCard from "@/views/Login/RegisterCard/index.vue";
import FindPasswordCard from "@/views/Login/FindPasswordCard/index.vue";
import Typewriter from "@/views/Login/components/Typewriter.vue";

// const { isDark, toggleDarkMode } = useDarkMode(); //动态切换暗色模式
const currentCard = ref('login'); // 默认显示 LoinCard

// 显示不同卡片的函数
const showCard = (card) => {
  currentCard.value = card; // 更新当前显示的卡片
};
</script>

<style lang="less" scoped>
.container {
  /* 设置背景渐变色 */
  background: linear-gradient(to right, #3b6586, #d77b52, #8c4c94); /* 你可以更改颜色 */

  /* 设置容器为全屏 */
  height: 100vh;

  /* 使用 Flexbox 将内容居中 */
  display: flex;
  /* 垂直居中 */
  flex-direction: column;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  position: relative; /* 相对定位，以便水印能够定位 */
}

.header {
  display: flex;
  flex-direction: row; // 确保内部元素水平排列
  align-items: center; // 垂直居中
  justify-content: space-between; // 在主轴上空间均匀分布
}


.el-button {
  border-radius: 10px;
  background-color: #151617;
  //边框大小
  border: none;
}

.copyright {
  /* 设置版权信息样式 */
  position: absolute; /* 绝对定位 */
  bottom: 20px; /* 距离底部20px */
  left: 50%; /* 水平居中 */
  transform: translateX(-50%); /* 使用 transform 进行精确居中 */
  color: rgba(255, 255, 255, 0.5); /* 设置文本颜色和透明度 */
  font-size: 14px; /* 设置字体大小 */
  text-align: center; /* 文本居中 */
}

.logo {
  position: absolute; // 绝对定位
  top: 20px; // 距离顶部20px
  left: 50%; // 水平居中
  transform: translateX(-50%); // 精确居中
  width: 25px;
}

.social-login {
  display: flex; // Flexbox布局
  justify-content: center; // 水平居中
  margin-top: 20px; // 上间距
}

.social-login button {
  margin: 0 10px; // 按钮间距
}

.footer-links {
  text-align: center; // 文本居中
  margin-top: 20px; // 上间距
}

.footer-links a {
  color: rgba(255, 255, 255, 0.8); // 链接颜色
}

.login-link {
  cursor: pointer; /* 设置光标为指针 */
}

//动态切换暗色模式
//.dark-mode {
//  background-color: #333; /* 设置暗黑模式背景颜色 */
//  color: white; /* 设置暗黑模式文字颜色 */
//}
</style>

<!-- login 页面 -->
<template>
  <div class="container">
    <div class="login-wrapper">
      <!--头部提示-->
      <div class="header">用户登录</div>

      <!-- 添加 form 标签，并监听 submit 事件 -->
      <form @submit.prevent="login">
        <div class="form-wrapper">
          <!--用户名-->
          <input type="text" v-model="username" placeholder="请输入用户名" class="input-item">
          <!--密码-->
          <input type="password" v-model="password" placeholder="请输入密码" class="input-item">
          <!-- 登录按钮可以保持原样，因为现在主要通过表单的submit事件处理登录 -->
          <input type="submit" id="login" class="btn" value="登录">
        </div>
      </form>
      <div class="msg">
        没有账号？ <a href="">立即注册</a>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import {useRouter} from 'vue-router'


defineOptions({
  name: 'Login'
})

const username = ref('')
const password = ref('')
const router = useRouter()

console.log('用户登录：', username.value, password.value)
const login = () => {
  if (username.value === 'sinvon' && password.value === 'sinvon') {
    console.log('登录成功')
    router.push('/home')
  } else {
    alert('用户名或密码错误,请重新输入')
  }
}
</script>

<style scoped lang="scss">
/*容器样式*/
.container {
  // 自动计算为当前可视屏幕的百分之百
  height: calc(100vh);

  /*背景颜色，线性渐变*/
  background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
}

/*表单样式*/
.login-wrapper {
  background-color: #fff;
  width: 250px;
  padding: 0 50px;
  position: relative;
  left: 50%;
  border-radius: 15px;
  top: 50%;
  transform: translate(-50%, -50%);
}

/*标题样式*/
.login-wrapper .header {
  font-size: 30px;
  font-weight: bold;
  text-align: center;
  line-height: 200px;
}

/*输入框样式*/
.login-wrapper .form-wrapper .input-item {
  display: block;
  width: 100%;
  margin-bottom: 20px;
  border: none;
  padding: 10px;
  border-bottom: 2px solid rgb(128, 125, 125);
  font-size: 15px;
  outline: none;
}

.login-wrapper .form-wrapper .input-item::placeholder {
  text-transform: uppercase;
}

/*登录按钮的样式*/
.login-wrapper .form-wrapper .btn {
  display: inline-block;
  text-align: center;
  width: 100%;
  border: none;
  padding: 5px;
  margin: 40px auto 0;
  background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
  color: #fff;
  border-radius: 5px;
}

/*鼠标悬停样式*/
.login-wrapper .form-wrapper .btn:hover {
  cursor: pointer;
  color: rgba(13, 36, 36, 0.76);
}

/*底部提示文字颜色*/
.login-wrapper .msg {
  text-align: center;
  line-height: 80px;
}

.login-wrapper .msg a {
  text-decoration: none;
  color: #a6c1ee;
}
</style>
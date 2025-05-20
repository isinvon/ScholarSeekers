<template>
  <form @submit.prevent="handleLogin">
    <div class="login">
      <div class="logintext">用户登录</div>

      <!-- 用户名/手机号输入 -->
      <div class="field">
        <input
            type="text"
            v-model="form.username"
            placeholder=" "
            :disabled="loading"
        >
        <div class="placeholder">用户名</div>
      </div>

      <!-- 密码输入 -->
      <div class="field">
        <input
            type="password"
            v-model="form.password"
            placeholder=" "
            :disabled="loading"
        >
        <div class="placeholder">密码</div>
      </div>

      <!-- 登录按钮 -->
      <el-button
          native-type="submit"
          class="loginbtn"
          :loading="loading"
      >
        {{ loading ? '登录中...' : '立即登录' }}
      </el-button>
    </div>
  </form>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import LoginApi from '@/api/login'
import { useUserStore } from '@/store/user'
import {setToken} from '@/utils/Token'

const router = useRouter()
const userStore = useUserStore()

// 表单数据
const form = reactive({
  username: '',
  password: ''
})

// 加载状态
const loading = ref(false)

const handleLogin = async () => {
  try {
    // 空值校验
    if (!form.username || !form.password) {
      ElMessage.warning('请输入用户名和密码')
      return
    }

    loading.value = true

    // 调用登录接口
    const { code, data } = await LoginApi.login({
      username: form.username,
      password: form.password
    })

    if (code === 200) {
      // 存储token
      setToken(data.token)

      // 获取用户信息
      await userStore.getInfo()

      ElMessage.success('登录成功')
      await router.push('/home')
    }
  } catch (error) {
    console.error('登录失败:', error)
    const msg = error.response?.data?.msg || '登录失败，请检查网络连接'
    ElMessage.error(msg)
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');

*,
*:before,
*:after {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

body {
  margin: 0;
  height: 100vh;
  width: 100%;
  color: black;
  background: #0c0c0b;
  overflow: hidden;
  display: grid;
  place-content: center;
}


.login {
  background: #151617;
  width: 20rem;
  padding: 1.5rem;
  border-radius: 1.5rem;
  display: grid;
  gap: 0.5rem;
}

.logintext {
  font-family: "Inter";
  color: #6779f5;
  text-align: center;
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 1rem;
}

@property --anim {
  syntax: "<number>";
  inherits: true;
  initial-value: 0;
}

.field {
  background: #6779f5;
  border-radius: 0.75rem;
  position: relative;
  height: 2.5rem;
  --anim: 0;
  transition: --anim 500ms ease;

  background: linear-gradient(to right,
  #6779f5 calc(clamp(0, (var(--anim) - 0.75) / 0.25, 1) * 33%),
  transparent calc(clamp(0, (var(--anim) - 0.75) / 0.25, 1) * 33%),
  transparent calc(100% - clamp(0, (var(--anim) - 0.75) / 0.25, 1) * 33%),
  #6779f5 calc(100% - clamp(0, (var(--anim) - 0.75) / 0.25, 1) * 33%)),
  linear-gradient(to top,
      transparent calc(15% + clamp(0, (var(--anim) - 0.65) / 0.1, 1) * 70%),
      #202122 calc(15% + clamp(0, (var(--anim) - 0.65) / 0.1, 1) * 70%)),
  linear-gradient(to right,
      transparent calc(50% - clamp(0, var(--anim) / 0.65, 1) * 50%),
      #6779f5 calc(50% - clamp(0, var(--anim) / 0.65, 1) * 50%),
      #6779f5 calc(50% + clamp(0, var(--anim) / 0.65, 1) * 50%),
      transparent calc(50% + clamp(0, var(--anim) / 0.65, 1) * 50%)),
  linear-gradient(#202122, #202122);
}

.field:has(input:focus) {
  --anim: 1;
}


.field > .placeholder {
  pointer-events: none;
  position: absolute;
  inset: 0;
  display: grid;
  place-content: center;
  color: #7d8dff;
  font-family: "Inter";
  transition: transform 500ms ease;
}

.field:has(input:focus) > .placeholder,
.field:has(input:not(:placeholder-shown)) > .placeholder {
  transform: translateY(-50%) scale(0.85)
}

.field > input {
  display: flex;
  align-items: center;
  padding-left: 1rem;
  color: white;
  position: absolute;
  inset: 0.125rem;
  border-radius: 0.625rem;
  border: none;
  outline: none;
  background: #202122
}

.loginbtn {
  margin-top: 0.5rem;
  background: radial-gradient(circle at center, #6779f5 calc(-50% + var(--anim) * 150%), #202122 calc(var(--anim) * 100%));
  border-radius: 0.75rem;
  position: relative;
  height: 2.5rem;
  display: grid;
  place-content: center;
  color: #7d8dff;
  font-family: "Inter";
  --anim: 0;
  transition: --anim 500ms ease, color 500ms ease;
}

.loginbtn:hover {
  --anim: 1;
  color: white;
  cursor: pointer;
}
</style>
<template>
  <div class="container">
    <div class="login-wrapper">
      <div class="header">用户登录</div>
      <form @submit.prevent="handleLogin">
        <div class="form-wrapper">
          <input
              type="text"
              v-model="form.username"
              placeholder="请输入用户名"
              class="input-item"
              required
          >
          <input
              type="password"
              v-model="form.password"
              placeholder="请输入密码"
              class="input-item"
              required
          >
          <button
              type="submit"
              class="btn"
              :disabled="loading"
          >
            {{ loading ? '登录中...' : '登录' }}
          </button>
        </div>
      </form>
      <div class="msg">
        没有账号？
        <router-link to="/register">立即注册</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref, reactive} from 'vue'
import {useRouter} from 'vue-router'
import LoginApi from '@/api/login'  // 导入接口模块
import {useUserStore} from '@/store/user'
import {setToken} from '@/utils/Token'

const router = useRouter()
const userStore = useUserStore()

const form = reactive({
  username: '',
  password: ''
})

const loading = ref(false)

const handleLogin = async () => {
  try {
    loading.value = true

    // 调用登录接口
    const {code, data, msg} = await LoginApi.login({
      username: form.username,
      password: form.password
    })

    if (code === 200) {
      // 存储 token 到 pinia 和 localStorage
      setToken(data.token)

      // 获取用户信息
      const userInfo = await userStore.getInfo()
      console.log('登录成功', userInfo)

      // 跳转到首页
      await router.push('/home')
    } else {
      throw new Error(msg || '登录失败')
    }
  } catch (error) {
    console.error('登录错误:', error)
    alert(error.message || '登录失败，请检查账号密码')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped lang="scss">
/* 原有样式保持不变 */
.btn:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}
</style>
<template>
  <div class="login">
    <div class="logintext">Register</div>
    <div class="field">
      <input
          type="tel"
          v-model="formData.phone"
          placeholder=""
          @input="clearError"
          maxlength="11"
      >
      <div class="placeholder">Phone Number</div>
    </div>
    <div class="field">
      <input
          type="password"
          v-model="formData.password"
          placeholder=""
          @input="clearError"
      >
      <div class="placeholder">Password</div>
    </div>
    <div class="field">
      <input
          type="password"
          v-model="formData.confirmPassword"
          placeholder=""
          @input="clearError"
      >
      <div class="placeholder">Confirm Password</div>
    </div>
    <div class="error-message" v-if="errorMessage">{{ errorMessage }}</div>
    <div class="loginbtn" @click="handleRegister">Continue</div>
  </div>
</template>

<script setup>
import {reactive, ref} from 'vue'
import Login from '@/api/login'
import {ElMessage} from 'element-plus'

const formData = reactive({
  phone: '',
  password: '',
  confirmPassword: ''
})

const errorMessage = ref('')

const validateForm = () => {
  if (!formData.phone.trim()) {
    errorMessage.value = '手机号不能为空'
    return false
  }

  if (!/^1[3-9]\d{9}$/.test(formData.phone)) {
    errorMessage.value = '请输入有效的手机号'
    return false
  }

  if (formData.password.length < 6) {
    errorMessage.value = '密码长度不能小于6位'
    return false
  }

  if (formData.password !== formData.confirmPassword) {
    errorMessage.value = '两次输入的密码不一致'
    return false
  }

  return true
}

const clearError = () => {
  errorMessage.value = ''
}

const handleRegister = async () => {
  if (!validateForm()) return

  try {
    const response = await Login.register({
      phone: formData.phone,
      password: formData.password
    })

    console.log(response)

    if (response.code === 200) {
      ElMessage.success({
        message: '注册成功',
        duration: 3000
      })
      // 这里可以添加跳转逻辑
    } else {
      ElMessage.error({
        message: response.msg || '注册失败',
        duration: 3000
      })
    }
  } catch (error) {
    console.error('注册失败:', error)
    ElMessage.error({
      message: '注册失败，请稍后重试',
      duration: 3000
    })
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

/* 添加错误提示样式 */
.error-message {
  color: #ff4d4f;
  margin: 10px 0;
  text-align: center;
}

.loginbtn {
  /* 添加点击效果 */
  cursor: pointer;
  transition: background-color 0.3s;
}

.loginbtn:hover {
  background-color: #409eff;
}
</style>
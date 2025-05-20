// src/store/user.js
import { defineStore } from 'pinia'
import { getToken, removeToken, setToken } from '@/utils/Token'
import User from '@/api/user'

export const useUserStore = defineStore('user', {
    state: () => ({
        token: getToken() || '',      // 从本地存储初始化token
        userId: null,                 // 用户ID
        userName: '',                 // 用户名
        roles: [],                   // 用户角色
        permissions: [],             // 权限列表
        avatar: ''                   // 头像
    }),

    actions: {
        // 登录操作
        async login(loginData) {
            try {
                const res = await User.loginApi(loginData)
                if (res.code === 200) {
                    this.token = res.token
                    setToken(res.token) // 持久化存储token
                    await this.getInfo() // 登录后自动获取用户信息
                }
                return res
            } catch (error) {
                return Promise.reject(error)
            }
        },

        // 获取用户信息
        async getInfo() {
            try {
                const res = await User.getUserInfo()
                if (res.code === 200) {
                    const data = res.user
                    this.userId = data.userId
                    this.userName = data.userName
                    this.roles = data.roles || []
                    this.permissions = data.permissions || []
                    this.avatar = data.avatar || ''
                }
                return res
            } catch (error) {
                return Promise.reject(error)
            }
        },

        // 退出登录
        async logout() {
            try {
                await User.logoutApi()
                this.resetToken()
                return Promise.resolve()
            } catch (error) {
                return Promise.reject(error)
            }
        },

        // 重置token
        resetToken() {
            this.token = ''
            this.roles = []
            this.permissions = []
            removeToken()
        }
    },

    getters: {
        // 获取简化的用户信息
        simpleInfo: (state) => {
            return {
                userId: state.userId,
                userName: state.userName,
                avatar: state.avatar
            }
        },

        // 判断是否登录
        isLogin: (state) => !!state.token
    }
})
// src/store/user.js
import { defineStore } from 'pinia'
import User from '@/api/user'

export const useUserStore = defineStore('user', {
    state: () => ({
        id: null,
        username: '',
        phone: '',
        email: '',
        role: 0,
        avatar: '',
        status: 0,
        isDeleted: false,
        college: null,
        sex: null,
        grade: null,
        address: null,
        introduction: null,
        createTime: null,
        updateTime: null
    }),

    actions: {
        // 登录操作
        async login(loginData) {
            try {
                const res = await User.loginApi(loginData)
                if (res.code === 200) {
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
                if (res.code === 200 && res.data?.user) {
                    const userData = res.data.user
                    this.$patch({
                        id: userData.id,
                        username: userData.username,
                        phone: userData.phone,
                        email: userData.email,
                        role: userData.role,
                        avatar: userData.avatar,
                        status: userData.status,
                        isDeleted: userData.isDeleted,
                        college: userData.college,
                        sex: userData.sex,
                        grade: userData.grade,
                        address: userData.address,
                        introduction: userData.introduction,
                        createTime: userData.createTime,
                        updateTime: userData.updateTime
                    })
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
                this.resetUser()
                return Promise.resolve()
            } catch (error) {
                return Promise.reject(error)
            }
        },

        // 重置用户信息
        resetUser() {
            this.$reset()
        }
    },

    getters: {
        // 获取简化的用户信息
        simpleInfo: (state) => ({
            id: state.id,
            username: state.username,
            avatar: state.avatar,
            role: state.role,
            status: state.status
        }),

        // 判断是否登录（根据用户ID）
        isLogin: (state) => !!state.id,

        // // 用户角色显示
        // roleName: (state) => {
        //     const roles = {
        //         0: '普通用户',
        //         1: '管理员',
        //         2: '教师',
        //         3: '学生'
        //         // 根据实际角色定义扩展
        //     }
        //     return roles[state.role] || '未知角色'
        // }
    }
})
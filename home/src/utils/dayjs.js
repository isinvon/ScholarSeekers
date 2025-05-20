import dayjs from 'dayjs'

export const formatDateTime = (date, format = 'YYYY-MM-DD HH:mm:ss') => {
    return dayjs(date).format(format)
}

// 在main.js中全局注册
// import { formatDateTime } from '@/utils/dayjs'
// app.config.globalProperties.$formatDate = formatDateTime
// 请求封装
import axios from 'axios';

// 创建axios实例
const service = axios.create({
  // baseURL: process.env.VUE_APP_BASE_API, // api的base_url
  timeout: 5000, // 请求超时时间
});

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 在发送请求之前做些什么，例如添加Token
    // config.headers['Authorization'] = `Bearer ${token}`;
    return config;
  },
  (error) => {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (response) => {
    // 对响应数据做点什么
    const data = response.data;
    if (data.code !== 200) {
      // 根据你的后台返回的code进行错误处理
      return Promise.reject(new Error(data.message || 'Error'));
    } else {
      return data;
    }
  },
  (error) => {
    // 对响应错误做点什么
    console.error('请求出错:', error);
    return Promise.reject(error);
  }
);

export default service;

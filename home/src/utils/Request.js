import axios from "axios";
import { isObject } from "lodash";
import { ElNotification } from "element-plus";
import qs from "qs";
import { getToken, removeToken } from "./Token";
import router from "@/router";

/*
Axios使用参考文档(中文): https://axios.nodejs.cn/docs 或者 https://axios.org.cn/docs/
Axios使用参考文档(英文): https://axios-http.com/docs
*/

/**
 * 默认配置
 */
const defaults = {
    baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
    timeout: 5000,
    headers: {
        "Content-Type": "application/json",
    },
};

/**
 * 请求类，封装了axios的请求方法，并提供了统一的请求和响应拦截器。
 */
class Request {
    /**
     * 创建Request实例。
     * @param {Object} [config={}] - Axios实例的额外配置项。
     */
    constructor(config) {
        this.axiosInstance = axios.create({ ...defaults, ...config });
        this.configureInterceptors();
    }
    /**
     * 配置请求和响应拦截器。
     */
    configureInterceptors() {
        this.axiosInstance.interceptors.request.use(
            (config) => {
                const token = getToken();
                if (token) {
                    // 添加token到请求头
                    // config.headers["token"] = token;
                    config.headers.Authorization = `Bearer ${token}`;
                }
                return config;
            },
            (error) => {
                // console.log("Request error:", error); // for debug
                Promise.reject(error);
            }
        );

        this.axiosInstance.interceptors.response.use(
            (response) => {
                // 如果response.data为null或undefined，则res为一个空对象。
                const res = response.data || {};
                if (res.code !== 200) {
                    // 未授权
                    if (res.code === 401) {
                        // 弹出提示框
                        ElNotification.error({
                            duration: 2000, // 提示框显示时间2s, 如果设置为 0，通知将不会自动关闭。
                            showClose: true, // 显示关闭按钮
                            title: "🚨未授权提示", // 提示框标题
                            message: "登录失效，请重新登录", // 提示框内容
                            // position: "top-right", // 提示框位置
                            // onClose:无论手动关闭, 还是自动关闭, 都会触发
                            onClose: () => {
                                const token = getToken();
                                // 强制注销
                                if (token) {
                                    removeToken();
                                    router.push({
                                        name: "login",
                                    }); // 只更新应用的部分视图。
                                    // window.location.href = "/login"; // 会触发页面的完全刷新,重新请求
                                }
                            },
                        });
                    }

                    // 501: Illegal token; 502: Other clients logged in; 503: Token expired;
                    if ([501, 502, 503].includes(res.code)) {
                        // 弹出提示框
                        ElNotification.error({
                            duration: 2000, // 提示框显示时间2s, 如果设置为 0，通知将不会自动关闭。
                            showClose: true, // 显示关闭按钮
                            title: "🚨未授权提示", // 提示框标题
                            message: res.msg, // 提示框内容
                            onClose: () => {
                                // 需要登录
                                router.push({
                                    name: "login",
                                });
                            },
                        });
                    }
                    // return Promise.reject(response.data);
                    return Promise.reject(new Error(res.msg || "Error"));
                } else {
                    // ElNotification.success({
                    //   title: "🎉请求成功",
                    //   message: res.msg,
                    //   duration: 2000,
                    //   showClose: true,
                    // }); for debug
                    return res;
                }
            },
            (error) => {
                // console.error("Error:", error); // for debug
                return Promise.reject(error);
            }
        );
    }

    /**
     * 发送GET请求。
     * @param {string} url - 请求的URL。
     * @param {Object} [params={}] - 请求的查询参数。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    // config可以为其他参数,具体可跳转此方法get中的源码, config示例：{ params: { id: 1 }, headers: { 'Content-Type': 'application/json' } }
    // 由get源码可知, get只接受两个参数(url,config),而config限定了某些参数 比如headers: { 'Content-Type': 'application/json' }, baseURL: 'https://localhost:8080', timeout: 5000 等等
    async get(url, params = {}, config = {}) {
        // 将params合并到config中(params不需要展开, 根据AxiosRequestConfig<D>源码, params是一个关键字
        const mergedConfig = { ...config, params };
        return (await this.axiosInstance.get(url, mergedConfig)).data;
    }

    /**
     * 发送POST请求。
     * @param {string} url - 请求的URL。
     * @param {*} data - 请求体数据。
     * @param {string} [isForm] - 表单类型，可以是'FORM'或'FILE'。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    // 由post源码可知, post只接受三个参数(url,data,config),data可以是任意内容（由开发者自行传入），而config限定了某些参数 比如headers: { 'Content-Type': 'application/json' }, baseURL: 'https://localhost:8080', timeout: 5000 等等
    async post(url, data, isForm, config = {}) {
        let requestConfig = {};

        if (isForm === "FORM") {
            requestConfig = {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=utf-8",
                },
                transformRequest: [(data) => qs.stringify(data)],
            };
        } else if (isForm === "FILE") {
            const formHeaders = {
                "Content-Type": "multipart/form-data",
            };

            const formData = new FormData();
            formData.append("file", data);
            data = formData;

            requestConfig = { headers: formHeaders };
        }

        const finalConfig = { ...requestConfig, ...config };
        return (await this.axiosInstance.post(url, data, finalConfig)).data;
    }

    /**
     * 发送PUT请求。
     * @param {string} url - 请求的URL。
     * @param {*} data - 请求体数据。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    // 由put源码可知, put只接受三个参数(url,data,config),data可以是任意内容（由开发者自行传入）, 而config限定了某些参数 比如headers: { 'Content-Type': 'application/json' }, baseURL: 'https://localhost:8080', timeout: 5000 等等
    async put(url, data, config = {}) {
        return (await this.axiosInstance.put(url, data, config)).data;
    }

    /**
     * 发送DELETE请求。
     * @param {string} url - 请求的URL。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    // 由delete源码可知, delete只接受两个参数(url,config),而config限定了某些参数 比如headers: { 'Content-Type': 'application/json' }, baseURL: 'https://localhost:8080', timeout: 5000 等等
    async delete(url, config = {}) {
        return (await this.axiosInstance.delete(url, config)).data;
    }

    // ====================================其他方法======================================================

    /**
     * 发送自定义请求。
     * @param {Object} config - 请求配置。
     * @returns {Promise} 解析后的响应数据。
     */
    async request(config = {}) {
        return (await this.axiosInstance.request(config)).data;
    }
    /**
     * 上传文件。
     * @param {string} url - 请求的URL。
     * @param {*} data - 文件数据。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    async upload(url, data, config = {}) {
        return (await this.post(url, data, "FIEL", config)).data;
    }
    /**
     * 下载文件。
     * @param {string} url - 请求的URL。
     * @param {*} data - 请求体数据。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    async download(url, data, config = {}) {
        return (await this.post(url, data, "FIEL", config)).data;
    }
    /**
     * 获取资源的完整URL。
     * @param {Object} config - 请求配置。
     * @returns {string} 完整的URL。
     */
    async getUri(config = {}) {
        // 使用axios实例的getUri方法构造带有参数和配置的完整URL
        return this.axiosInstance.getUri(config);
    }

    /**
     * 发送OPTIONS请求。
     * @param {string} url - 请求的URL。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    async options(url, config = {}) {
        // 发送OPTIONS请求到指定URL，并返回响应的数据部分
        return (await this.axiosInstance.options(url, config)).data;
    }

    /**
     * 发送HEAD请求。
     * @param {string} url - 请求的URL。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    async head(url, config = {}) {
        // 发送HEAD请求到指定URL，并返回响应的数据部分
        return (await this.axiosInstance.head(url, config)).data;
    }

    /**
     * 发送PATCH请求。
     * @param {string} url - 请求的URL。
     * @param {*} data - 请求体数据。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    async patch(url, data, config = {}) {
        // 发送PATCH请求到指定URL，附带数据和配置，并返回响应的数据部分
        return (await this.axiosInstance.patch(url, data, config)).data;
    }

    // ============axios 支持以下快捷方法：postForm、putForm、patchForm 就是对应的 HTTP 方法，content-type 标头预设为 multipart/form-data。

    /**
     * 发送POST请求，使用表单数据格式。
     * @param {string} url - 请求的URL。
     * @param {*} data - 请求体数据。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    async postForm(url, data, config = {}) {
        // 发送POST请求到指定URL，使用表单数据格式，附带数据和配置，并返回响应的数据部分
        return (await this.axiosInstance.postForm(url, data, config)).data;
    }

    /**
     * 发送PUT请求，使用表单数据格式。
     * @param {string} url - 请求的URL。
     * @param {*} data - 请求体数据。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    async putForm(url, data, config = {}) {
        // 发送PUT请求到指定URL，使用表单数据格式，附带数据和配置，并返回响应的数据部分
        return (await this.axiosInstance.putForm(url, data, config)).data;
    }

    /**
     * 发送PATCH请求，使用表单数据格式。
     * @param {string} url - 请求的URL。
     * @param {*} data - 请求体数据。
     * @param {Object} [config={}] - 请求的额外配置。
     * @returns {Promise} 解析后的响应数据。
     */
    async patchForm(url, data, config = {}) {
        // 发送PATCH请求到指定URL，使用表单数据格式，附带数据和配置，并返回响应的数据部分
        return (await this.axiosInstance.patchForm(url, data, config)).data;
    }
}

export default new Request();

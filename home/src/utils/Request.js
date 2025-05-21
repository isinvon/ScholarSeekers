import axios from "axios";
import {isObject} from "lodash";
import {ElNotification} from "element-plus";
import qs from "qs";
import {getToken, removeToken} from "./Token";
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
    timeout: parseInt(process.env.VUE_APP_REQUEST_TIMEOUT) || 5000,
    // 上传单独配置
    uploadTimeout: parseInt(process.env.VUE_APP_UPLOAD_TIMEOUT) || 30000,
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
        this.axiosInstance = axios.create({...defaults, ...config});
        this.configureInterceptors();
    }

    /**
     * 配置请求和响应拦截器。
     */
    configureInterceptors() {
        this.axiosInstance.interceptors.response.use(
            response => {
                const res = response.data || {};
                if (res.code !== 200) {
                    // 统一处理授权错误
                    this.handleAuthErrors(res);
                    // 返回带错误信息的Promise
                    return Promise.reject(new Error(res.msg || `请求失败，错误码：${res.code}`));
                }
                return res;
            },
            error => {
                // 增强错误处理
                let errorMessage = '请求异常';

                if (error.response) {
                    // 处理HTTP状态码
                    const status = error.response.status;
                    errorMessage = `请求失败：${status}`;

                    // 根据状态码细化提示
                    const statusMessages = {
                        400: '请求参数错误',
                        401: '身份验证失败',
                        403: '拒绝访问',
                        404: '资源不存在',
                        500: '服务器内部错误',
                        502: '网关错误',
                        503: '服务不可用'
                    };

                    errorMessage = statusMessages[status] || errorMessage;

                    // 优先使用服务器返回的错误信息
                    if (error.response.data && error.response.data.msg) {
                        errorMessage = error.response.data.msg;
                    }
                } else if (error.request) {
                    // 请求未收到响应
                    if (error.code === 'ECONNABORTED') {
                        errorMessage = '请求超时，请检查网络连接';
                    } else {
                        errorMessage = '网络异常，请检查网络连接';
                    }
                }

                ElNotification.error({
                    title: '请求错误',
                    message: errorMessage,
                    duration: 3000
                });

                return Promise.reject(new Error(errorMessage));
            }
        );
    }

    // 处理授权相关错误
    handleAuthErrors(res) {
        if ([401, 501, 502, 503].includes(res.code)) {
            ElNotification.error({
                duration: 2000,
                showClose: true,
                title: "授权提示",
                message: res.msg || '登录状态已失效',
                onClose: () => {
                    removeToken();
                    router.push({name: "login"});
                }
            });
        }
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
        const mergedConfig = {...config, params};
        return (await this.axiosInstance.get(url, mergedConfig)).data;
    }

    /**
     * 发送POST请求（智能处理多种数据格式）
     * @param {string} url - 请求地址（必填）
     * @param {*} data - 请求数据，支持以下格式：
     *                  - File/Blob：单个文件上传（需配合type='FILE'）
     *                  - File[]：多文件上传（需配合type='FILE'）
     *                  - Object：自动处理为JSON格式（默认行为）
     *                  - FormData：直接发送表单数据（自动识别）
     * @param {'FORM'|'FILE'|Object} [type] - 请求类型或配置对象，可选值：
     *                  - 'FORM'：表单编码格式（application/x-www-form-urlencoded）
     *                  - 'FILE'：文件上传格式（multipart/form-data）
     *                  - Object：直接传入配置对象时的特殊处理（兼容旧调用方式）
     * @param {Object} [config={}] - Axios请求配置，支持所有axios配置项，优先级：
     *                  - 配置中的headers会与自动生成的headers合并
     *                  - 显式配置项会覆盖自动生成的配置
     * @returns {Promise<Object>} 返回Promise对象，解析后包含：
     *                  - code: 状态码
     *                  - data: 响应数据
     *                  - msg: 服务器消息
     * @example 普通JSON请求
     * post('/api/items', { name: '手机' })
     *
     * @example 文件上传
     * post('/upload', file, 'FILE')
     *
     * @example 带配置的表单请求
     * post('/submit', formData, 'FORM', { timeout: 5000 })
     *
     * @example 兼容旧调用方式（第三个参数为配置对象）
     * post('/api', data, { headers: { 'X-Custom': '123' } })
     */
    // 由post源码可知, post只接受三个参数(url,data,config),data可以是任意内容（由开发者自行传入），而config限定了某些参数 比如headers: { 'Content-Type': 'application/json' }, baseURL: 'https://localhost:8080', timeout: 5000 等等
    async post(url, data, type, config = {}) {
        // 参数智能解析（兼容新旧调用方式）
        let requestType = type;
        let finalConfig = config;

        // 处理参数顺序：当第三个参数是配置对象时
        if (typeof type === 'object' && config === undefined) {
            finalConfig = type;
            requestType = undefined;
        }

        let finalData = data;
        const headers = {
            ...finalConfig.headers // 保留用户自定义headers
        };

        // 文件上传处理（保持原有逻辑）
        if (requestType === "FILE") {
            const formData = new FormData();
            if (data instanceof File || data instanceof Blob) {
                formData.append("file", data);
            } else if (Array.isArray(data)) {
                data.forEach(file => formData.append("files", file));
            }
            finalData = formData;
            headers["Content-Type"] = "multipart/form-data";
        }
        // 表单编码处理（可选）
        else if (requestType === "FORM") {
            headers["Content-Type"] = "application/x-www-form-urlencoded";
            finalData = qs.stringify(data);
        }
        // 智能处理普通JSON请求
        else {
            // 自动处理对象/数组类型数据
            if (isObject(data) || Array.isArray(data)) {
                headers["Content-Type"] = "application/json";
                finalData = JSON.stringify(data);
            }
        }

        // 合并最终配置
        const mergedConfig = {...finalConfig, headers};
        return this.axiosInstance.post(url, finalData, mergedConfig);
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
        return this.post(url, data, "FILE", {
            ...config,
            timeout: defaults.uploadTimeout // 上传超时时间
        });
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

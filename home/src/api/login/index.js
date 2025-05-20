import request from "@/utils/Request";

class Login {

    /**
     * 登录
     * @returns {Promise}
     */
    login(data) {
        return request.post("/home/user/auth/login", data)
    }

    /**
     * 注册
     * @param data
     * @returns {Promise}
     */
    register(data) {
        return request.post("/home/user/register", data)
    }

    /**
     *
     * @param data
     * @returns {Promise}
     */
    logout(data) {
        return request.post("/home/user/auth/logout", data)
    }
}

export default new Login();
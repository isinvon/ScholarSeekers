import request from "@/utils/Request";

class Login {

    /**
     * 登录
     * @returns {Promise}
     */
    login(data) {
        return request.post("/home/user/login", data)
    }

    /**
     * 注册
     * @param data
     * @returns {Promise}
     */
    register(data) {
        return request.post("/home/user/register", data)
    }
}

export default new Login();
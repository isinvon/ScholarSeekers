import request from "@/utils/Request";

class User {

    /**
     * 编辑用户信息
     * @returns {Promise}
     */
    edit(data) {
        return request.post("/home/user/edit", data)
    }
}

export default new User();
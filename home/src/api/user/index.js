import request from "@/utils/Request";

class User {

    loginApi(data) {
        return request.post('/auth/login', data)
    }

    logoutApi(data) {
        return request.post('/auth/logout', data)
    }

    getUserInfo(data) {
        return request.post('/home/user/getInfo', data)
    }
}

export default new User();
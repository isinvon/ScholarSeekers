import request from "@/utils/Request";

class Home {
    constructor() {
        this.init();
    }

    init() {
    }
    /**
     * 获取轮播图
     * @returns {Promise}
     */
    getCarouselList() {
        return request.get("/home/getCarouselList")
    }

    /**
     * 获取最近事件
     * @returns {Promise}
     */
    getRecentEvent() {
        return request.get("/home/recentEvent")
    }

    /**
     * 最近丢失
     * @returns {Promise}
     */
    getRecentLost() {
        return request.get("/home/getRecentLost")
    }
}

export default new Home();
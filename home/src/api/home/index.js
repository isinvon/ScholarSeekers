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
     * @returns {Promise<axios.AxiosResponse<any>>}
     */
    getRecentEvent() {
        return request.get("/home/recentEvent")
    }
}

export default new Home();
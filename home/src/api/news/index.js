// 新闻API
import request from "@/utils/Request";

class News {
    static async getNewsById(id) {
        try {
            const response = await request({
                url: "/news/getNewsById",
                method: "get",
                params: {
                    id: id
                }
            })
            return response.data;
        } catch (error) {
            throw error;
        }
    }
    static async getNewsList() {
        try {
            const response = await request({
                url: "/news/getNewsList",
                method: "get",
            })
            return response.data;
        } catch (error) {
            throw error;
        }
    }
}

export default News;

// 新闻API
import request from "@/utils/Request";

class News {
    getNewsById(id) {
        return request.get("/news/getNewsById", {
            params: {
                id: id
            }
        })
    }
    getNewsList() {
        return request.get("/news/getNewsList")
    }
}
export default new News();

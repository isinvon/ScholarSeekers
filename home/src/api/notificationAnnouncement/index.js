import request from "@/utils/Request";

class NotificationAnnouncement {

    list(data) {
        return request.post('/home/notice/list', data)
    }
}

export default new NotificationAnnouncement();
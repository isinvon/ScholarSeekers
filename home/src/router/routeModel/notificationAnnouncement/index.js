// 个人中心路由
export const notificationAnnouncementRoute = [
    {
        path: "/notificationAnnouncement",
        name: "notificationAnnouncement",
        component: () => import("@/views/NotificationAnnouncement"),
        meta: {
            title: "通知公告",
            // keepAlive: true,
            // isTab: true,
            // isAuth: true
        },
    },
];

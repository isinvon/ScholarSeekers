// 个人中心路由
export const myPublishRoute = [
    {
        path: "/myPublish",
        name: "myPublish",
        component: () => import("@/views/MyPublish"),
        meta: {
            title: "我的发布",
            // keepAlive: true,
            // isTab: true,
            // isAuth: true
        },
    },
];

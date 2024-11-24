// 失物发布路由
export const lostItemPublishRoute = [
    {
        path: "/lostItemPublish",
        name: "lostItemPublish",
        component: () => import("@/views/LostItemPublish"),
        meta: {
            title: "失物发布",
            // keepAlive: true,
            // isTab: true,
            // isAuth: true
        },
    },
];

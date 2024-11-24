// 认领记录路由
export const lostAndFoundRoute = [
    {
        path: "/lostAndFound",
        name: "lostAndFound",
        component: () => import("@/views/LostAndFound"),
        meta: {
            title: "寻物启事",
            // keepAlive: true,
            // isTab: true,
            // isAuth: true
        },
    },
];

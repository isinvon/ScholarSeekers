// 认领记录路由
export const lostAndFoundRecordRoute = [
    {
        path: "/allReleaseRecord",
        name: "allReleaseRecord",
        component: () => import("src/views/AllReleaseRecord"),
        meta: {
            title: "个人认领记录",
            // keepAlive: true,
            // isTab: true,
            // isAuth: true
        },

    },
];

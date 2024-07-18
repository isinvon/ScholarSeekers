// 最新丢失路由
export const latestLostRoute = [
    {
        path: "/latestLost",
        name: "latestLost",
        component: () => import("@/views/LatestLost"),
        meta: {
            title: '最新失物招领',
            // icon: 'el-icon-s-order',
            // roles: ['admin', 'editor']
        },
    }
]
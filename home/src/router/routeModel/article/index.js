// 文章路由
export const articleRoute = [
    {
        path: "/article/:id",
        name: "article",
        component: () => import("@/views/LostAndFound/Article"),
        meta: {
            title: "文章",
            // keepAlive: true,
            // isTab: true,
            // isAuth: true
        }
    },
];

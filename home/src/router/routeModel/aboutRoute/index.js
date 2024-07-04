// 关于我们路由
export const aboutRoute = [
    {
        path: '/about',
        name: 'about',
        component: () => import('@/views/About'),
        meta: {
            title: '关于我们',
            keepAlive: true,
            // ...
        }
    }
]

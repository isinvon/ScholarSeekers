// 帮助与反馈路由
export const helpAndFeedbackRoute = [
    {
        path: '/helpAndFeedback',
        name: 'helpAndFeedback',
        component: () => import('@/views/HelpAndFeedback'),
        meta: {
            title: '帮助与反馈',
            // keepAlive: true,
            // isTab: true,
            // isAuth: true
        },
    }
]
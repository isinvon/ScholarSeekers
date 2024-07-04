// 已经认领路由
export const alreadyClaimedRoute = [
    {
        path: '/alreadyClaimed',
        component: () => import('@/views/AlreadyClaimed'),
        name: 'alreadyClaimed',
        meta: {
            title: '已认领',
            // icon: 'el-icon-s-claim',
            // roles: ['admin', 'editor']
        }
    }
]
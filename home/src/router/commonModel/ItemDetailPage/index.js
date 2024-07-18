// 物品详情页
export const ItemDetailPageRoute = [
    {
        path: '/item/:id',
        name: 'itemDetailPage',
        component: () => import('@/components/ItemDetailPage'),
        meta: {
            title: '物品详情',
            // keepAlive: true,// 是否缓存
            // ...
        }
    }
]

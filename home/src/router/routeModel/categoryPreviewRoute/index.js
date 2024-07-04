// 分类预览路由
export const categoryPreviewRoute = [
    {
        path: '/categoryPreview',
        name: 'categoryPreview',
        component: () => import('@/views/CategoryPreview'),
        meta: {
            title: '分类预览'
        },
    }
]
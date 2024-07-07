export const errorRoute = [
    {
        path: '/:catchAll(.*)',
        name: '404 Not Found',
        component: () => import('@/views/Error/404')
    }
]
// 首页路由
export const homeRoute = [
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/Home'),
    children: [
    ]
  }
]

// export const homeRoute = [
//     {
//       path: '/',
//       name: 'home',
//       component: () => import('@/views/Home'),
//       meta: {
//         title: '首页',
//         // icon: 'el-icon-s-home',
//         // affix: true,
//         // noCache: true,
//         // hidden: false,
//         // alwaysShow: false,
//         // roles: ['admin', 'editor']
//       }
      
//     }
//   ]
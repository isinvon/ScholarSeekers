// 首页路由
export const loginRoute = [
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/Login/index_v2.vue"),
    meta: {
      title: "登录",
      // icon: 'el-icon-s-home',
      // affix: true,
      // noCache: true,
      // hidden: false,
      // alwaysShow: false,
      // roles: ['admin', 'editor']
    },
  },
];

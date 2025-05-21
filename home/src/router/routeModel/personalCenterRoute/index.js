// 个人中心路由
export const personalCenterRoute = [
  {
    path: "/personalCenter",
    name: "personalCenter",
    component: () => import("@/views/PersonalCenter"),
    meta: {
      title: "个人中心",
      // keepAlive: true,
      // isTab: true,
      // isAuth: true
    },
    children: [
      {
        path: "",
        name: "Profile",
        component: () =>
          import("@/views/PersonalCenter/components/PersonalForm"),
      },
      {
        path: "edit",
        name: "EditProfile",
        component: () => import("@/views/PersonalCenter/components/EditForm"),
      },
    ],
    // children: [
    //   {
    //     path: "/personalCenter/edit",
    //     name: "edit",
    //     component: () => import("@/views/PersonalCenter/components/EditForm"),
    //     meta: {
    //       title: "编辑用户信息",
    //       // keepAlive: true,
    //       // isTab: true,
    //       // isAuth: true
    //     },
    //   },
    // ],
  },
];

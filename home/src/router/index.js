import {createRouter, createWebHistory} from "vue-router";
// 引入通用路由
import {routeModel} from "@/router/routeModel";
import {commonModel} from "@/router/commonModel"
// 进度条
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'// nprogress样式文件
// 进度条配置
NProgress.configure({
    easing: 'ease',  // 动画方式
    speed: 500,  // 递增进度条的速度
    showSpinner: false, // 是否显示加载ico
    trickleSpeed: 200, // 自动递增间隔
    minimum: 0.3 // 初始化时的最小百分比
})


// 整合所有路由模块
let modelRoute = []
    .concat(routeModel)
    .concat(commonModel);

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes: [...modelRoute],
});

//当路由开始跳转时
router.beforeEach((to, from , next) => {
    // 开启进度条
    NProgress.start();
    // 这个一定要加，没有next()页面不会跳转的。这部分还不清楚的去翻一下官网就明白了
    next();
});
//当路由跳转结束后
router.afterEach(() => {
    // 关闭进度条
    NProgress.done()
})

// TODO 需求: 完善路由守卫登录Token拦截-2024.7.20新建任务
// 在加载路由前，判断是否登录
// router.beforeEach((to, from, next) => {
//     // 如果是登录页面，直接放行
//     if (to.path === "/login") {
//         next();
//         return;
//     }
//     // 如果不是登录页面，判断是否登录
//     if (localStorage.getItem("token")) {
//         next();
//     } else {
//         next("/login");
//     }
// });

export default router;

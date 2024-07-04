import { createRouter, createWebHistory } from "vue-router";

// 引入通用路由
import { routeModel } from "@/router/routeModel/index.js";

// 整合所有路由模块
let modelRoute = [].concat(routeModel);

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes: [...modelRoute],
});

export default router;

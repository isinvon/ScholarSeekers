import {createApp} from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
// 全局样式
import "./style/index.less";
import * as echarts from "echarts";
import {solveChangeSizeCausedProblem} from "@/utils/SolveChangeSizeCausedProblem";

console.log(`\x1b[32m🟢🟢🟢当前版本是：${process.env.VUE_APP_VERSION}🟢🟢🟢\x1b[0m`);
console.log(`\x1b[32m🟢🟢🟢当前环境是：${process.env.NODE_ENV}🟢🟢🟢\x1b[0m`);
console.log(`\x1b[32m🟢🟢🟢当前接口为${process.env.VUE_APP_BASE_API}🟢🟢🟢\x1b[0m`);
console.log(`\x1b[32m🟢🟢🟢当前时间是：${new Date().toLocaleString()}🟢🟢🟢\x1b[0m`);

// 开发环境下才开启mock
if (process.env.NODE_ENV === "development") {
    try {
        // 导入mock
        const Mock = require("mockjs");
        require("./mock/index.js");
        Mock.setup({
            timeout: "200-600",
        });
        console.log(`\x1b[32m🟢🟢🟢mock模拟数据启动成功🟢🟢🟢\x1b[0m`);
    } catch (error) {
        console.log(
            `\x1b[32m🟢🟢🟢mock模拟数据启动失败,请检查Mock相关导入或者配置是否正确🟢🟢🟢\x1b[0m`
        );
    }
} else {
    console.log(`\x1b[32m🟢🟢🟢mock模拟数据关闭🟢🟢🟢\x1b[0m`);
}

const app = createApp(App);

// 挂载echarts
app.config.globalProperties.$echart = echarts;

app.use(store).use(router).use(ElementPlus).mount("#app");

solveChangeSizeCausedProblem();
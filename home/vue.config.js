const {defineConfig} = require("@vue/cli-service");
// 配置环境变量
module.exports = defineConfig({
    // 默认
    transpileDependencies: true,
    // 配置环境变量
    publicPath: process.env.NODE_ENV === "production" ? "" : "/", // 通常用于确定是开发或生产环境
    // 打包文件输出目录, 默认是dist
    outputDir: "dist",
    // 放置静态资源的位置
    assetsDir: "static",
    configureWebpack: {
        // 别名配置
        resolve: {
            // 配置别名
            alias: {
                // 默认已配置
                src: "@",
                assets: "@/assets",
                common: "@/common",
                components: "@/components",
                views: "@/views",
                api: "@/api",
                store: "@/store",
                router: "@/router",
                utils: "@/utils",
                plugins: "@/plugins",
            },
        },
    },
    devServer: {
        // 当前程序运行域名
        host: "localhost",
        // 当前程序运行端口
        port: 3000,
        // 配置后端接口代理
        proxy: {
            "/api": {
                // 后端接口域名
                target: "http://localhost:8080",
                // 允许跨域
                changeOrigin: true,
                // 重写路径
                pathRewrite: {
                    // 将api 替换成空
                    "^/api": "",
                },
            },
            // 其他配置
            //  "/hotapi": {}
        },
    },
});
const cdn = {
    css: [
    ],
    js: [
        // gsap: 用于动画
        "https://cdnjs.cloudflare.com/ajax/libs/gsap/3.9.1/gsap.min.js"
    ]
}

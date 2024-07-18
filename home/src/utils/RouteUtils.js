// url工具类
import router from "@/router";

class RouteUtils {
    // 返回上一个页面
    static goBackToPreviousPage() {
        window.history.back();
    }

    // 跳转到物品详情页(新标签)
    static goToItemDetailPage(id) {
        window.open(`/item/${id}`, "_blank");
    }

    // 跳转到首页
    static goToHomePage() {
        window.location.href = '/home';
    }
    // 跳转到指定页面
    static goToPage(url) {
        window.location.href = url;
    }
    // 跳转到指定页面并刷新
    static goToPageAndRefresh(url) {
        window.location.href = url;
        window.location.reload();
    }
    // 只刷新当前页面
    static refreshCurrentPage() {
        window.location.reload();
    }
    // 清除路由历史
    static clearHistory() {
        window.history.replaceState(null, null, window.location.href);
    }

    //跳转到指定路由
    static goToRoute(routeName, params) {
        router.push({
            name: routeName,
            params: params
        });
    }
}

export default RouteUtils;


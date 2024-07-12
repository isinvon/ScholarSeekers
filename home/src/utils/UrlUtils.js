/**
 * @author: sinvon
 * @Date: 2024-7-8 2:27 上午
 * @Last Modified by: sinvon
 * @Last Modified time: 2024-7-8 2:27 上午
 * @Description: Url工具类
 */
class UrlUtils {
    /**
     * 通过url分解尾部参数
     * @param url
     * @returns {{Object}}
     */
    getUrlParams(url) {
        const params = {};
        const urlArr = url.split('?');
        if (urlArr.length > 1) {
            const paramsArr = urlArr[1].split('&');
            paramsArr.forEach((item) => {
                const itemArr = item.split('=');
                params[itemArr[0]] = itemArr[1];
            });
        }
        return params;
    }

    /**
     * 获取url的baseurl
     * @param url
     * @returns {*}
     */
    getBaseUrl(url) {
        const urlArr = url.split('?');
        return urlArr[0];
    }

    // 在新标签中跳转链接
    openUrl(url) {
        window.open(url, '_blank');
    }
}

export default new UrlUtils();
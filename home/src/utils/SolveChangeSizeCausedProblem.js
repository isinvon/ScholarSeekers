// 解决改变尺寸导致界面报错的问题(影响组件: el-table), 报错信息: ResizeObserver loop completed with undelivered notifications.
export const solveChangeSizeCausedProblem = () => {
    const debounce = (fn, delay) => {
        let timer
        return (...args) => {
            if (timer) {
                clearTimeout(timer)
            }
            timer = setTimeout(() => {
                fn(...args)
            }, delay)
        }
    }
    const _ResizeObserver = window.ResizeObserver;
    window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
        constructor(callback) {
            callback = debounce(callback, 200);
            super(callback);
        }
    }
}
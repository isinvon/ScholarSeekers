import {onMounted, ref} from "vue";

/**
 * @author: sinvon
 * @date: 2024/7/7 21:10 下午
 * @version: 1.0.0
 * @File: ClickUtils
 * @description 点击工具类
 */
class ClickUtils {

    /**
     * 点击元素一次
     * @param element
     */
    static click(element) {
        element.click();
    }

    /**
     * 点击某个元素 times 次
     * @param element
     * @param times
     */
    static clickTimes(element, times) {
        for (let i = 0; i < times; i++) {
            element.click();
        }
    }


    /**
     * 持续点击一个元素，每隔time秒点击一次
     * @param element
     * @param time
     */
    static clickContinuously(element, time) {
        setInterval(() => {
            element.click();
        }, time * 1000);
    }

    /**
     * 按元素数组顺序点击元素
     * @param elements
     * @param index
     */
    static clickByOrder(elements, index) {
        elements[index].click();
    }

    /**
     * 点击一个元素, 次数随机
     * @param element
     */
    static clickRandomOnce(element) {
        element[Math.floor(Math.random() * element.length)].click();
    }

    /**
     * 随机点击元素,需要一个元素数组
     * @param elements
     */
    static clickRandom(elements) {
        elements[Math.floor(Math.random() * elements.length)].click();
    }

    /**
     * 随机点击元素,需要一个元素数组，点击次数为 times
     * @param elements
     * @param times
     */
    static clickRandomTimes(elements, times) {
        for (let i = 0; i < times; i++) {
            elements[Math.floor(Math.random() * elements.length)].click();
        }
    }

    /**
     * 随机点击元素,需要一个元素数组，点击次数为 times，点击间隔为 time
     * @param elements
     * @param times
     * @param time
     */
    static clickRandomTimesInterval(elements, times, time) {
        for (let i = 0; i < times; i++) {
            setTimeout(() => {
                elements[Math.floor(Math.random() * elements.length)].click();
            }, time * 1000);
        }
    }

    /**
     * 斐波那契数列时间间隔持续点击一个元素
     * @param element
     */
    static clickFibonacciContinuously(element) {
        let fib = [0, 1]; // 斐波那契数列的前两个数字

        function nextFibonacci() {
            fib.push(fib[fib.length - 1] + fib[fib.length - 2]);
            return fib[fib.length - 1];
        }

        function clickElementWithFibonacciDelay() {
            element.click();
            const nextInterval = nextFibonacci();
            setTimeout(clickElementWithFibonacciDelay, nextInterval);
        }

        // 立即开始第一次点击，之后按斐波那契数列时间间隔点击
        clickElementWithFibonacciDelay();
    }

    /**
     * 随机数序列: 使用随机数生成器来决定每次点击之间的延迟时间，这可以使得点击行为看起来更加自然，不容易被检测为自动化行为。
     * @param element
     */
    static clickRandomSequence(element) {

        function nextRandomInterval() {
            return Math.random() * 1000; // 生成随机数，范围为0到1，乘以1000得到随机毫秒数
        }

        function clickElement() {
            element.click();
            const nextInterval = nextRandomInterval();
            setTimeout(clickElement, nextInterval);
        }

        setTimeout(clickElement, nextRandomInterval());
    }

    /**
     * 指数增长序列: 类似于斐波那契数列，但每次延迟时间基于前一次的指数倍增，例如每次延迟时间翻倍。
     * @param element
     */
    static clickExponentialSequence(element) {
        function nextExponentialInterval() {
            return Math.pow(2, Math.random()) * 1000; // 生成随机数，范围为0到1，乘以2的幂次方得到随机毫秒数
        }

        function clickElement() {
            element.click();
            const nextInterval = nextExponentialInterval();
            setTimeout(clickElement, nextInterval);
        }

        setTimeout(clickElement, nextExponentialInterval());
    }

    /**
     * 质数序列: 使用质数作为点击间隔，这可以确保间隔时间不会形成明显的模式。
     * @param element
     */
    static clickPrimeSequence(element) {
        let currentPrime = 2; // 第一个质数

        function isPrime(n) {
            if (n <= 1) return false;
            for (let i = 2; i <= Math.sqrt(n); i++) {
                if (n % i === 0) return false;
            }
            return true;
        }

        function findNextPrime() {
            let candidate = currentPrime + 1;
            while (!isPrime(candidate)) {
                candidate++;
            }
            currentPrime = candidate;
            return currentPrime;
        }

        function clickElement() {
            element.click();
            const nextInterval = findNextPrime() * 1000; // 转换为毫秒
            setTimeout(clickElement, nextInterval);
        }

        // 立即开始第一次点击
        clickElement();
    }

    /**
     * 平方数序列: 使用平方数作为间隔，如1, 4, 9, 16, 25... 这种序列的增长速度比线性快，但比指数慢。
     * @param element
     */
    static clickSquareSequence(element) {
        let currentSquare = 1; // 第一个平方数

        function nextSquare() {
            currentSquare++;
            return currentSquare * currentSquare;
        }

        function clickElement() {
            element.click();
            const nextInterval = nextSquare() * 1000; // 转换为毫秒
            setTimeout(clickElement, nextInterval);
        }

        // 立即开始第一次点击
        clickElement();
    }

    /**
     * 三角数序列: 三角数序列是连续整数的累加，如1, 3, 6, 10, 15... 这个序列的增长速度介于线性和平方之间。
     * @param element
     */
    static clickTriangularSequence(element) {
        let currentTriangular = 1; // 第一个三角数

        function nextTriangular() {
            currentTriangular++;
            return currentTriangular * (currentTriangular + 1) / 2;
        }

        function clickElement() {
            element.click();
            const nextInterval = nextTriangular() * 1000; // 转换为毫秒
            setTimeout(clickElement, nextInterval);
        }

        // 立即开始第一次点击
        clickElement();
    }

    /**
     * 黄金分割比例: 使用黄金分割比例（约等于1.618）来决定每次点击间隔的增加量，可以产生一种自然的节奏感。
     * @param element
     */
    static clickGoldenRatioSequence(element) {
        let currentGoldenRatio = 1; // 第一个黄金分割数

        function nextGoldenRatio() {
            currentGoldenRatio += 0.618;
            return currentGoldenRatio;
        }

        function clickElement() {
            element.click();
            const nextInterval = nextGoldenRatio() * 1000; // 转换为毫秒
            setTimeout(clickElement, nextInterval);
        }

        // 立即开始第一次点击
        clickElement();
    }

    /**
     * 混沌系统: 利用混沌理论中的某些模型，如洛伦兹吸引子或罗伯特森吸引子，来生成非周期性的间隔时间。
     * @param element
     */
    static clickChaosSequence(element) {
        let x = 0.5; // 初始值
        const r = 3.9; // 控制参数，接近4时系统表现出混沌特性

        function logisticMap(x) {
            return r * x * (1 - x);
        }

        function getNextInterval() {
            x = logisticMap(x);
            return x * 1000; // 将混沌变量映射到毫秒范围
        }

        function clickElement() {
            element.click();
            const nextInterval = getNextInterval();
            setTimeout(clickElement, nextInterval);
        }

        // 立即开始第一次点击
        clickElement();
    }

    /**
     * 傅立叶级数: 可以使用傅立叶级数来生成周期性的模式，这可以通过叠加多个正弦波来实现。
     * @param element
     */
    static clickFourierSeriesSequence(element) {
        let time = 0; // 当前时间点
        const coefficients = [
            {amplitude: 1, frequency: 0.01},
            {amplitude: 0.5, frequency: 0.02},
            {amplitude: 0.3, frequency: 0.03}
        ]; // 傅立叶系数数组，包含振幅和频率

        function calculateInterval() {
            let sum = 0;
            coefficients.forEach(coef => {
                sum += coef.amplitude * Math.sin(2 * Math.PI * coef.frequency * time);
            });
            time += 1; // 更新时间点
            return Math.abs(sum * 1000); // 返回正的间隔时间，单位转换为毫秒
        }

        function clickElement() {
            element.click();
            const nextInterval = calculateInterval();
            setTimeout(clickElement, nextInterval);
        }

        // 立即开始第一次点击
        clickElement();
    }

    /**
     * 动态调整算法: 根据外部条件（如网络延迟、服务器响应时间等）动态调整点击间隔，使点击更加适应当前环境。
     * @param element
     */
    static clickDynamicAdjustmentSequence(element) {
        let lastResponseTime = 1000; // 初始响应时间设为1s
        let minInterval = 500; // 最小点击间隔
        let maxInterval = 3000; // 最大点击间隔

        function getServerResponseTime() {
            // 这里应替换为实际获取服务器响应时间的代码
            // 示例返回一个随机数模拟响应时间
            return Math.floor(Math.random() * 1000) + 500;
        }

        function adjustInterval(responseTime) {
            // 根据响应时间调整点击间隔
            let adjustedInterval = responseTime * 1.5; // 假设点击间隔为响应时间的1.5倍
            return Math.max(minInterval, Math.min(maxInterval, adjustedInterval));
        }

        function clickElement() {
            element.click();
            lastResponseTime = getServerResponseTime();
            const nextInterval = adjustInterval(lastResponseTime);
            setTimeout(clickElement, nextInterval);
        }

        // 立即开始第一次点击
        clickElement();
    }

    // /**
    //  * 机器学习预测: 使用机器学习模型预测最佳的点击时机，比如基于历史数据预测服务器的低负载时段。
    //  * @param element
    //  */
    //  clickMachineLearningPredictionSequence(element) {
    // }
}

export default ClickUtils;

// -----------------此工具类的使用示例:-------------------
/*
现在有一个id="myButton"的按钮，你可以使用以下方式来调用点击工具:

<script setup>
import ClickUtils from './ClickUtils';
import {ref, onMounted} from 'vue';

const buttonElement = ref(null)
// 通过id="remain-box"获取元素
onMounted(() => {
    buttonElement.value = document.getElementById('myButton');
})
</script>

// 标签中使用点击工具
<template>
    <button id="myButton" @click="ClickUtils.click(buttonElement)">Click Me</button>
</template>*/

// --------------------------------------------------------------------
// 注意: 对于持续点击的场景，推荐在组件中使用状态管理来截停点击事件，
//      避免无限循环, 此类不给出截停方法, 是因为调用截停函数耦合性太大,
//      容易造成全局变量的污染, 并且会影响多个函数, 降低代码的可读性和可维护性。
// --------------------------------------------------------------------


// ---------使用状态管理截停点击事件的示例--------------
/*
<template>
  <button :disabled="isProcessing" @click="handleClick">
    Submit
  </button>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const isProcessing = ref(false);

const handleClick = () => {
  if (isProcessing.value) return;

  isProcessing.value = true;

  // 模拟异步操作
  setTimeout(() => {
    console.log('Action completed');

    // 异步操作完成后，将 isProcessing 设回 false
    isProcessing.value = false;
  }, 2000);
};

// 可选：在组件挂载后重置状态
onMounted(() => {
  isProcessing.value = false;
});
</script>
 */
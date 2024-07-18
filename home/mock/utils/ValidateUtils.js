import { execSync } from "child_process";

class ValidateUtils {
  constructor() {}


  // 校验IP地址是否可以ping通
  validPing(ip) {
    if (!ip || typeof ip !== "string" || ip.trim() === "") {
      return false;
    }

    try {
      const command =
        process.platform === "win32" ? `ping -n 1 ${ip}` : `ping -c 1 ${ip}`;
      execSync(command, { stdio: "ignore" });
      return true;
    } catch (error) {
      return false;
    }
  }
  /**
   * 校验是否为ipv4地址
   * @param {*} ip
   * @returns
   */
  validIPv4(ip) {
    if (
      ip === null ||
      ip === undefined ||
      typeof ip !== "string" ||
      ip.trim() === ""
    ) {
      return false;
    }
    const ipv4Pattern =
      /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
    return ipv4Pattern.test(ip);
  }

  /**
   * 校验是否为ipv6地址
   * @param {*} ip
   * @returns
   */
  validIPv6(ip) {
    if (
      ip === null ||
      ip === undefined ||
      typeof ip !== "string" ||
      ip.trim() === ""
    ) {
      return false;
    }
    const ipv6Pattern = /^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$/;
    return ipv6Pattern.test(ip);
  }

  /**
   * 校验URL是否符合规范
   * @param {*} url
   * @returns
   */
  validURL(url) {
    if (url === null || url === undefined) {
      return false;
    }
    if (typeof url !== "string") {
      return false;
    }
    if (url.trim() === "") {
      return false;
    }
    if (/^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$/.test(url)) {
      return true;
    }
    return false;
  }
  /**
   * 校验字符是否为中文
   * @param {*} String
   * @returns
   */
  validChinese(String) {
    if (String === null || String === undefined) {
      return false;
    }
    if (typeof String !== "string") {
      return false;
    }
    if (String.trim() === "") {
      return false;
    }
    if (/^[\u4e00-\u9fa5]+$/.test(String)) {
      return true;
    }
    return false;
  }

  /**
   * 校验是否为中文姓名
   * @param {*} name
   * @returns
   */
  validChineseName(name) {
    if (name === null || name === undefined) {
      return false;
    }
    if (typeof name !== "string") {
      return false;
    }
    if (name.trim() === "") {
      return false;
    }
    if (/^[\u4e00-\u9fa5]{2,4}$/.test(name)) {
      return true;
    }
    return false;
  }

  /**
   * 校验手机号是否符合规则
   * @param {*} mobile
   * @returns
   */
  validMobile(mobile) {
    if (mobile === null || mobile === undefined) {
      return false;
    }
    if (typeof mobile !== "string") {
      return false;
    }
    if (mobile.trim() === "") {
      return false;
    }
    if (/^1[3-9]\d{9}$/.test(mobile)) {
      return true;
    }
    return false;
  }
  /**
   * 校验是否为邮箱
   * @param {*} email
   * @returns
   */
  validEmail(email) {
    if (email === null || email === undefined) {
      return false;
    }
    if (typeof email !== "string") {
      return false;
    }
    if (email.trim() === "") {
      return false;
    }
    if (/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email)) {
      return true;
    }
    return false;
  }
  /**
   * 校验是否为身份证号
   * @param {*} idCard
   * @returns boolean
   */
  validIdCard(idCard) {
    // 增加对空值和非字符串的检查
    if (idCard === null || idCard === undefined || typeof idCard !== "string") {
      return false;
    }
    // 去除首尾空格
    idCard = idCard.trim();
    // 检查字符串是否为空
    if (idCard === "") {
      return false;
    }
    // 预编译正则表达式以提高性能，同时考虑X和x的情况
    const idCardRegex =
      /^(?:[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x))$/;

    // 使用预编译的正则表达式进行匹配
    if (idCardRegex.test(idCard)) {
      return true;
    }
    return false;
  }

  /**
   * 校验是否为银行卡
   * @param {*} bankCard
   * @returns boolean
   */
  validBankCard(bankCard) {
    // 增加对空值和非字符串的检查
    if (
      bankCard === null ||
      bankCard === undefined ||
      typeof bankCard !== "string"
    ) {
      return false;
    }
    // 去除首尾空格
    bankCard = bankCard.trim();
    // 检查字符串是否为空
    if (bankCard === "") {
      return false;
    }
    // 预编译正则表达式以提高性能
    const bankCardRegex = /^[0-9]{16,19}$/;
    if (bankCardRegex.test(bankCard)) {
      return true;
    }
    return false;
  }
  /**
   * 校验是否为银行卡类型
   * @param {*} bankCardType
   * @returns boolean
   */
  validBankCardType(bankCardType) {
    // 增加对空值和非字符串的检查
    if (
      bankCardType === null ||
      bankCardType === undefined ||
      typeof bankCardType !== "string"
    ) {
      return false;
    }
    // 去除首尾空格
    bankCardType = bankCardType.trim();
    // 检查字符串是否为空
    if (bankCardType === "") {
      return false;
    }
    if (/^[0-9]{4,6}$/.test(bankCardType)) {
      return true;
    }
    return false;
  }
  /**
   * 校验是否为日期
   * @param {*} date 待验证的日期字符串
   * @param {string} [format="YYYY-MM-DD"] 可选的日期格式，默认为 "YYYY-MM-DD"
   * @returns {boolean} 如果是有效日期返回 true，否则返回 false
   */
  validDate(date, format = "YYYY-MM-DD") {
    // 增加对空值和非字符串的检查
    if (date === null || date === undefined || typeof date !== "string") {
      return false;
    }
    // 去除首尾空格
    date = date.trim();
    // 检查字符串是否为空
    if (date === "") {
      return false;
    }

    // 定义支持的日期格式及其对应的正则表达式
    const formats = {
      "YYYY-MM-DD": /^\d{4}-\d{2}-\d{2}$/,
      "MM/DD/YYYY": /^\d{2}\/\d{2}\/\d{4}$/,
      "DD/MM/YYYY": /^\d{2}\/\d{2}\/\d{4}$/,
      // 可以按需添加更多格式
    };

    // 检查传入的格式是否支持
    if (!formats[format]) {
      console.warn(`Unsupported date format: ${format}`);
      return false;
    }

    try {
      // 使用预编译的正则表达式进行日期格式验证
      return formats[format].test(date);
    } catch (error) {
      console.error(`Error validating date: ${error.message}`);
      return false;
    }
  }
  // 示例用法
  // console.log(validDate("2023-03-15")); // true
  // console.log(validDate("03/15/2023", "MM/DD/YYYY")); // true
  // console.log(validDate("15/03/2023", "DD/MM/YYYY")); // true
  // console.log(validDate("2023-15-03")); // false, 错误的日期格式
  // console.log(validDate("2023-02-30")); // false, 无效的日期 (非闰年的2月30日)
  // console.log(validDate("2023-02-29", "YYYY-MM-DD")); // true, 闰年的2月29日
  // console.log(validDate("2023-02-29", "MM/DD/YYYY")); // true
  // console.log(validDate("2023-02-29", "DD/MM/YYYY")); // true
  // console.log(validDate("2023-02-29", "YYYY-MM-DD HH:mm:ss")); // false, 不支持的日期格式

  /**
   * 验证是否为空
   * @param {*} data
   * @returns
   */
  isEmpty(data) {
    if (data === null || data === undefined) {
      return true;
    }
    if (typeof data === "string" && data.trim() === "") {
      return true;
    }
    if (Array.isArray(data) && data.length === 0) {
      return true;
    }
    if (typeof data === "object" && Object.keys(data).length === 0) {
      return true;
    }
    return false;
  }
  /**
   * 验证是否为数字
   * @param {*} data
   * @returns
   */
  isNumber(data) {
    if (typeof data === "number") {
      return true;
    }
    if (typeof data === "string") {
      if (/^[0-9]+$/.test(data)) {
        return true;
      }
    }
    return false;
  }
  /**
   * 验证是否为字符
   * @param {*} data
   * @returns
   */
  isString(data) {
    if (typeof data === "string") {
      return true;
    }
    return false;
  }
  /**
   * 验证是否为布尔值
   * @param {*} data
   * @returns
   */
  isBoolean(data) {
    if (typeof data === "boolean") {
      return true;
    }
    return false;
  }
  /**
   * 验证是否为数组
   * @param {*} data
   * @returns
   */
  isArray(data) {
    if (Array.isArray(data)) {
      return true;
    }
    return false;
  }
  /**
   * 验证是否为对象
   * @param {*} Object
   * @returns
   */
  isObject(Object) {
    if (typeof Object === "object") {
      return true;
    }
    return false;
  }
}

export default new ValidateUtils();
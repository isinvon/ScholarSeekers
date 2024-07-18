import Mock from "mockjs";

const Random = Mock.Random;

/**
 *
 * @description MockRandomUtil随机工具类
 * @author sinvon
 * @date 2024-07-11 16:48:04 下午
 * @version 1.0.0
 * @see https://github.com/nuysoft/Mock
 * @see https://github.com/nuysoft/Mock/blob/master/README-zh-CN.md
 * @see https://github.com/nuysoft/Mock/blob/master/examples/examples.js
 */
class MockRandomUtil {
  constructor() {}
  
  /**
   * 指定范围随机年龄
   * @param {*} min
   * @param {*} max
   * @returns
   */
  getRandomAge(min, max) {
    return Random.integer(min, max);
  }

  /**
   * 生成指定长度随机字符串(符号、数字、字母混合), 如: ;'cGqhwU
   * @returns String
   */
  getRandomStringByLen(len) {
    return Random.string(len);
  }

  /**
   * 生成指定范围随机字符串(符号、数字、字母混合), 如: okI02([95
   * @returns String
   */
  getRandomStringByRange(min, max) {
    return Random.string(min, max);
  }

  /**
   * 生成特定长度的顺序数组, 如: getArray(1,5): [1,2,3,4,5]
   * @returns String
   */
  getArray(min, max) {
    return Random.range(min, max + 1);
  }

  /**
   * 生成随机英文段落, 如: Gljdnkcy iov lsmrasj vcgnywx hkokssfa qamu mtbqenyo nlhayui xcnjzhk nfhsdkzfay frylw pmahwh cpisqjnacm. Azwyn jtk grucl uxfmma xugvgyek mmzqzpfm tanufcd ihlsibtvq mdh bfpc kclubgnc yloppe zwzhvubjn sjfedb qhwf efdypift kndqdfoe. Tlspvq sft sqoo cqcubcj dtylyiuyft mqsc xygfbw hjwflyggr yqn kvvojy xlccofefj fdyhiqi yuiydiss vvzczyf.
   * @returns String
   */
  getRandomEnglishParagraph() {
    return Random.paragraph();
  }

  /**
   * 生成随机中文段落, 如: 率世类原般许程都称口立上样军五所际。收无代论反满天研半工收按验下红情。还七发片正安信强引外门千道石拉拉电。做类习温七重间复深可强商中太最当。看已列光口最史件和王红产以真没现。
   * @returns String
   */
  getRandomChineseParagraph() {
    return Random.cparagraph();
  }

  /**
   * 生成指定范围随机英文句子, 如: Qxlih tdqh gtpkhliov nqxcrgt tvdhjg jtvr.
   * @param {*} min
   * @param {*} max
   * @returns
   */
  getRandomEnglishSentenceByRange(min, max) {
    return Random.sentence(min, max);
  }
  /**
   * 生成指定长度的随机英文句子, 如: The Quick Brown Fox Jumps Over The Lazy Dog.
   * @param {*} len
   * @returns String
   */
  getRandomEnglishSentenceByLen(len) {
    return Random.sentence(len);
  }

  /**
   * 生成指定范围随机中文句子, 如: 热规半给天许之三口机。
   * @param {*} min
   * @param {*} max
   * @returns
   */
  getRandomChineseSentenceByRange(min, max) {
    return Random.csentence(min, max);
  }
  /**
   * 生成指定长度随机中文句子, 如: 观花反适论
   * @param {*} len
   * @returns String
   */
  getRandomChineseSentenceByLen(len) {
    return Random.csentence(len);
  }

  /**
   * 生成指定长度的随机中文词, 如: 观花反适论
   * @param {*} len
   * @returns String
   */
  getRandomChineseWordByLen(len) {
    return Random.cword(len);
  }
  /**
   * 生成指定范围的随机中文词, 如: 周林外少日总青加路会
   * @param {*} min
   * @param {*} max
   * @returns
   */
  getRandomChineseWordByRange(min, max) {
    return Random.cword(min, max);
  }

  /**
   * 生成指定范围的随机英文字符串, 如: owstdsaddasda
   * @param {*} min
   * @param {*} max
   * @returns
   */
  getRandomWordByRange(min, max) {
    return Random.word(min, max);
  }

  /**
   * 生成指定长度随机英文字符串, 如: owst
   * @param {*} len
   * @returns
   */
  getRandomWordByLen(len) {
    return Random.word(len);
  }

  /**
   * 生成随机中文标题, 如: 观花反适论
   * @returns String
   */
  getRandomChineseTitle() {
    return Random.ctitle();
  }

  /**
   * 生成随机的英文标题, 如: The Quick Brown Fox Jumps Over The Lazy Dog
   * @returns String
   */
  getRandomEnglishTitle() {
    return Random.title();
  }

  /**
   * 生日日期, 如: 1998-07-23 18:09:00
   * @returns String
   */
  getRandomBirthday1() {
    return Random.datetime("yyyy-MM-dd HH:mm:ss");
  }

  /**
   * 生日日期, 如: Mon Aug 01 2011 04:21:17 GMT+0800 (中国标准时间)
   * @returns String
   */
  getRandomBirthday2() {
    return new Date(Random.datetime("yyyy-MM-dd HH:mm:ss"));
  }

  /**
   * 生成随机的中国地名, 如: 贵州省-三门峡市-通河县
   * @returns String
   */
  getRandomAddress() {
    return `${Random.province()}-${Random.city()}-${Random.county()}`;
  }
  /**
   * 生成随机GUID 如: 4eAD105F-77F2-BccD-dB1d-3C70bFC71fE8
   * @returns String
   */
  getRandomGuid() {
    return Random.guid();
  }

  /**
   * 随机身份证号 如: 130000198405266751
   * @returns String
   */
  getRandomId() {
    return Random.id();
  }

  /**
   * 生成随机银行卡号
   * @returns String
   */
  getRandomBankCard() {
    const bankCard = Random.string("1234567890", 16);
    return bankCard;
  }

  /**
   * 根据0-9数字生成随机手机号 (中国大陆)
   * @returns String
   */
  getRandomMobile() {
    const firstThreeDigits = [
      "130",
      "131",
      "132",
      "133",
      "134",
      "135",
      "136",
      "137",
      "138",
      "139",
      "145",
      "146",
      "147",
      "148",
      "149",
      "150",
      "151",
      "152",
      "153",
      "155",
      "156",
      "157",
      "158",
      "159",
      "165",
      "166",
      "170",
      "171",
      "172",
      "173",
      "175",
      "176",
      "177",
      "178",
      "180",
      "181",
      "182",
      "183",
      "184",
      "185",
      "186",
      "187",
      "188",
      "189",
      "190",
      "191",
      "198",
      "199",
    ];
    const randomIndex = Math.floor(Math.random() * firstThreeDigits.length);
    const prefix = firstThreeDigits[randomIndex];
    const suffix = Math.floor(
      1000000000 + Math.random() * 9000000000
    ).toString();
    return prefix + suffix;
  }

  /**
   * 随机生成中文名 如: 张三丰
   * @returns String
   */
  getRandomChineseName() {
    return Random.cname();
  }

  /**
   * 随机生成一个随机数（一般非常大）, 如 -5694023774852812、2937804575272820
   * @returns integer
   */
  getRandomNumber() {
    return Random.integer();
  }
  /**
   * 随机生成一个随机数
   * @param {*} min
   * @param {*} max
   * @returns integer
   */
  getRandomInteger(min, max) {
    return Random.integer(min, max);
  }
  /**
   * 返回一个随机float型的浮点数, 如 3.24929, 2.55282
   * @param {*} min 最小数
   * @param {*} max 最大数
   * @param {*} dmin 小数点最小位数
   * @param {*} dmax 小数点最大位数
   * @returns float
   */
  getRandomFloat(min, max, dmin, dmax) {
    return Random.float(min, max, dmin, dmax);
  }
  /**
   *
   * @param {*} len 长度
   * @returns String 随机生成一个数字型字符串
   */
  getRandomString(len) {
    return Random.string("1234567890", len);
  }
  /**
   *
   * @returns boolean 随机生成一个布尔值,如false、true
   */
  getRandomBoolean() {
    return Random.boolean();
  }
  /**
   *
   * @returns yyyy-MM-dd
   */
  getRandomDate() {
    return Random.date("yyyy-MM-dd");
  }
  /**
   *
   * @returns HH:mm:ss
   */
  getRandomTime() {
    return Random.time("HH:mm:ss");
  }
  /**
   *
   * @returns yyyy-MM-dd HH:mm:ss
   */
  getRandomDateTime() {
    return Random.datetime("yyyy-MM-dd HH:mm:ss");
  }

  /**
   *
   * @returns String 随机生成一个英文姓名, 如:  Richard Gonzalez
   */
  getRandomEnglishName() {
    return Random.name();
  }
  /**
   *
   * @returns String 随机生成一个邮箱
   */
  getRandomEmail() {
    return Random.email();
  }

  /**
   * 随机生成一个QQ邮箱, 如: g.tmoi@qq.com
   * @returns String
   */
  getRandomQQEmail() {
    return Random.email("qq.com");
  }

  /**
   *
   * @returns 返回一个随机的地区信息, 例如 华中、西北、西南、华东
   */
  getRandomRegion() {
    return Random.region();
  }
}

export default new MockRandomUtil();

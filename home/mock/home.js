// 获取轮播图列表
import Mock from "mockjs";

const apiPrefix = process.env.VUE_APP_BASE_API;
// 获取轮播图列表
Mock.mock(apiPrefix + '/home/getCarouselList', 'get', () => {
    let data = []
    // 最多5条数据, 太多的话会有很多滚动点
    for (let i = 0; i < 5; i++) {
        data.push({
            id: Mock.Random.guid(),
            title: Mock.Random.ctitle(),
            img: Mock.Random.image('200x200', Mock.Random.color(), Mock.Random.cword(5, 10),),
            url: `https://www.baidu.com/s?wd=${i + 1}`
        })
    }
    return {
        code: 200,
        msg: 'success',
        data: data
    }
})
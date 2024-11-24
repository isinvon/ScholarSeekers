import Mock from 'mockjs';
const apiPrefix = process.env.VUE_APP_BASE_API;

// 获取新闻列表 接口+分页
Mock.mock(apiPrefix + '/news/getNewsList', 'get', (options) => {
    const { pageNum, pageSize } = options.body || {};
    let data = [];
    // 循环40次
    for (let i = 0; i < 40; i++) {
        data.push({
            id: Mock.Random.guid(),
            title: Mock.Random.ctitle(15),
            // link: Mock.Random.url(),
            link: `https://www.baidu.com/s?wd=${i + 1}`,
            // img: `https://picsum.photos/200/200?random=${i + 4}`,
            img: Mock.Random.image('200x200',Mock.Random.color(),Mock.Random.cword(5, 10), ),
        });
    }
    // 假设每页显示10条记录，如果pageNum和pageSize存在，则根据它们来切分数据
    if (pageNum && pageSize) {
        const start = (pageNum - 1) * pageSize;
        const end = pageNum * pageSize;
        data = data.slice(start, end);
    }

    return {
        code: 200,
        msg: 'success',
        data: data,
    };
});

// 通过id获取新闻
Mock.mock(apiPrefix + /\/news\/getNewsById\?id=\d+/, 'get', (options) => {
    const newsId = options.url.match(/id=(\d+)/)[1];
    const data = [
        {id: 1, title: 'News 1', link: 'https://www.baidu.com', img: 'https://picsum.photos/200/200'},
        {id: 2, title: 'News 2', link: 'https://www.bing.com', img: 'https://picsum.photos/200/200'},
        {id: 3, title: 'News 3', link: 'https://www.google.com', img: 'https://picsum.photos/200/200'},
        {id: 4, title: 'News 4', link: 'https://www.google.com', img: 'https://picsum.photos/200/200'},
        {id: 5, title: 'News 5', link: 'https://www.google.com', img: 'https://picsum.photos/200/200'},
    ];
    const newsItem = data.find(item => item.id === parseInt(newsId));
    return {
        code: 200,
        msg: 'success',
        data: newsItem || {},
    };
});
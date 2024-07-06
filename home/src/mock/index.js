import Mock from 'mockjs';
// 模拟getNewsList接口_不加分页
// Mock.mock('/news/getNewsList','get',()=>{
//     return {
//         code: 200,
//         data: [
//             {id: 1, title: 'News 1', link: 'https://www.baidu.com', img: 'https://picsum.photos/200/200'},
//             {id: 2, title: 'News 2', link: 'https://www.bing.com', img: 'https://picsum.photos/200/200'},
//             {id: 3, title: 'News 3', link: 'https://www.google.com', img: 'https://picsum.photos/200/200'},
//         ]
//     }
// })

// 模拟getNewsList接口+分页
Mock.mock('/news/getNewsList', 'get', (options) => {
    const { pageNum, pageSize } = options.body || {};
    let data = [
        {id: 1, title: 'News 1', link: 'https://www.baidu.com', img: 'https://picsum.photos/200/200'},
        {id: 2, title: 'News 2', link: 'https://www.bing.com', img: 'https://picsum.photos/200/200'},
        {id: 3, title: 'News 3', link: 'https://www.bing.com', img: 'https://picsum.photos/200/200'},
    ];

    // 假设每页显示10条记录，如果pageNum和pageSize存在，则根据它们来切分数据
    if (pageNum && pageSize) {
        const start = (pageNum - 1) * pageSize;
        const end = pageNum * pageSize;
        data = data.slice(start, end);
    }

    return {
        code: 200,
        data: data,
    };
});

// 模拟getNewsById接口
Mock.mock(/\/news\/getNewsById\?id=\d+/, 'get', (options) => {
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
        data: newsItem || {},
    };
});
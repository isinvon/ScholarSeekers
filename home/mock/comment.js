import Mock from 'mockjs';
import MockRandomUtils from "./utils/MockRandomUtils";

// 男人女人枚举
const Gender = {
    MALE: 'women',
    FEMALE: 'men'
};

// 随机男人女人
const randomGender = () => {
    return Mock.Random.pick([Gender.MALE, Gender.FEMALE]);
};

// 随机数字可选1-99
const randomNumber = () => {
    return Mock.Random.integer(2, 90);
};

// 获取头像的url:
// 男人: https://randomuser.me/api/portraits/men/1.jpg   数字可以0-99
// 女人: https://randomuser.me/api/portraits/women/1.jpg   数字可以0-99

// 男女随机头像
const randomManAndWomenAvatarUrl = () => {
    return `https://randomuser.me/api/portraits/${randomGender()}/${randomNumber()}.jpg`;
};
// 仅男人头像
const randomManAvatarUrl = () => {
    return `https://randomuser.me/api/portraits/men/${randomNumber()}.jpg`;
};
// 仅女人头像
const randomWomenAvatarUrl = () => {
    return `https://randomuser.me/api/portraits/women/${randomNumber()}.jpg`;
};

// 随机图片
const randomImageUrl = () => {
    return `https://picsum.photos/id/${Mock.Random.integer(1, 1000)}/500/300`;
};

const commentArray = []

for (let i = 0; i < 40; i++) {
    commentArray.push({
        id: i + 1,
        author: {
            name: MockRandomUtils.getRandomChineseName(),
            avatar: randomManAndWomenAvatarUrl(),
            titles: [MockRandomUtils.getRandomChineseTitle()]
        },
        content: MockRandomUtils.getRandomChineseParagraph(),
        images: [
            {url: randomImageUrl()},
            {url: randomImageUrl()}
        ],
        likes: MockRandomUtils.getRandomInteger(0, 100),
        dislikes: MockRandomUtils.getRandomInteger(0, 10),
        // 回复者
        replies: [
            {
                id: 1,
                author: {
                    name: MockRandomUtils.getRandomChineseName(),
                    avatar: randomManAndWomenAvatarUrl(),
                    titles: [MockRandomUtils.getRandomChineseTitle()]
                },
                content: MockRandomUtils.getRandomChineseParagraph(),
                images: [
                    {url: randomImageUrl()},
                    {url: randomImageUrl()}
                ],
                likes: MockRandomUtils.getRandomInteger(0, 100),
                dislikes: MockRandomUtils.getRandomInteger(0, 10)
            },
            {
                id: 2,
                author: {
                    name: MockRandomUtils.getRandomChineseName(),
                    avatar: randomManAndWomenAvatarUrl(),
                    titles: [MockRandomUtils.getRandomChineseTitle()]
                },
                content: MockRandomUtils.getRandomChineseParagraph(),
                images: [
                    {url: randomImageUrl()},
                    {url: randomImageUrl()}
                ],
                likes: MockRandomUtils.getRandomInteger(0, 100),
                dislikes: MockRandomUtils.getRandomInteger(0, 10)
            }
        ]
    })
}

//commentArray的数据结构json骨架为
// {
//   "id": "@id",
//   "author": {
//     "name": "@cname",
//     "avatar": "@image",
//     "titles": [
//       "@cname",
//       "@cname",
//       "@cname"
//     ]
//   },
//   "content": "@cparagraph",
//   "images": [
//     {
//       "url": "@image"
//     },
//     {
//       "url": "@image"
//     }
//   ],
//   "likes": "@integer(0, 100)",
//   "dislikes": "@integer(0, 10)",
//   "replies": [
//     {
//       "id": "@id",
//       "author": {
//         "name": "@cname",
//         "avatar": "@image",
//         "titles": [
//           "@cname",
//           "@cname",
//           "@cname"
//         ]
//       },
//       "content": "@cparagraph",
//       "images": [
//         {
//           "url": "@image"
//         }
//       ]
//     }
//   ]
// }

// 将commentArray命名为commentList导出
export const commentData = commentArray;
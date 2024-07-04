// 顶部导航栏
import {
    House as HouseIcon,
    Compass as GuideIcon,
    Message as MessageBoxIcon,
    Bell as BellIcon,
    // Pointer as PointerIcon,
    // Link as AboutIcon,
} from '@element-plus/icons-vue';

export const headerMenu = [
    {id: 1, title: '首页', icon: HouseIcon, path: '/home'},
    {id: 2, title: '寻物启事', icon: GuideIcon, path: '/lostAndFound'},
    {id: 3, title: '我的发布', icon: MessageBoxIcon, path: '/myPublish'},
    {id: 4, title: '通知公告', icon: BellIcon, path: '/publicNotice'},
    // 以下两个页面单独设计路由
    // {id: 5, title: '关于我们', icon: PointerIcon, path: '/About'},
    // {id: 6, title: '登录', icon: AboutIcon, path: '/login'},
];

// 侧边栏
import {
    House as HomeIcon,
    Files as FilesIcon,
    Search as SearchIcon,
    Check as CheckIcon,
    User as UserIcon,
    Message as MessageIcon,
    CircleCheck as RecordIcon
} from '@element-plus/icons-vue';

export const sideMenu = [
    {id: 1, title: '首页', icon: HomeIcon, path: '/home'},
    {id: 2, title: '最新失物', icon: SearchIcon, path: '/latestLost'},
    {id: 3, title: '分类预览', icon: FilesIcon, path: '/categoryPreview'},
    {id: 4, title: '已认领', icon: CheckIcon, path: '/alreadyClaimed'},
    {id: 5, title: '所有发布记录', icon: RecordIcon, path: '/allReleaseRecord'},
    {id: 6, title: '个人中心', icon: UserIcon, path: '/personalCenter'},
    {id: 7, title: '帮助与反馈', icon: MessageIcon, path: '/helpAndFeedback'},
];


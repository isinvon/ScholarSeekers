import Index from "@/views";

import {alreadyClaimedRoute} from "./alreadyClaimedRoute";
import {categoryPreviewRoute} from "./categoryPreviewRoute";
import {helpAndFeedbackRoute} from "./helpAndFeedbackRoute";
import {homeRoute} from "./homeRoute";
import {latestLostRoute} from "./latestLostRoute";
import {personalCenterRoute} from "./personalCenterRoute";
import {lostAndFoundRecordRoute} from "./allReleaseRecordRoute";
import {articleRoute} from "@/router/routeModel/article";

import {aboutRoute} from "./aboutRoute";
import {loginRoute} from "./loginRoute";
import {notificationAnnouncementRoute} from "@/router/routeModel/notificationAnnouncement";
import {myPublishRoute} from "@/router/routeModel/myPublish";
import {lostItemPublishRoute} from "@/router/routeModel/lostItemPublishRoute";
import {lostAndFoundRoute} from "@/router/routeModel/lostAndFound";
import {errorRoute} from "./errorRoute";


export const routeModel = [
    {
        // 嵌入的页面
        path: "/",
        name: "index",
        component: Index,
        // 重定向到首页/home
        redirect: "/home",
        children: [
            // sideMenu栏
            ...homeRoute,
            ...helpAndFeedbackRoute,
            ...personalCenterRoute,
            ...latestLostRoute,
            ...alreadyClaimedRoute,
            ...categoryPreviewRoute,
            ...lostAndFoundRecordRoute,
            // headerMenu栏
            ...notificationAnnouncementRoute,
            ...myPublishRoute,
            ...lostAndFoundRoute,
            ...lostItemPublishRoute,
            // 其他路由
            ...articleRoute,
        ],
    },
    // 外部页面
    ...loginRoute,
    ...aboutRoute,
    ...errorRoute
];
import Index from "@/views";

import {alreadyClaimedRoute} from "./alreadyClaimedRoute";
import {categoryPreviewRoute} from "./categoryPreviewRoute";
import {helpAndFeedbackRoute} from "./helpAndFeedbackRoute";
import {homeRoute} from "./homeRoute";
import {latestLostRoute} from "./latestLostRoute";
import {personalCenterRoute} from "./personalCenterRoute";

import {aboutRoute} from "./aboutRoute";
import {loginRoute} from "./loginRoute";
import {errorRoute} from "./errorRoute";


export const routeModel = [
    {
        path: "/",
        name: "index",
        component: Index,
        // 重定向到首页/home
        redirect: "/home",
        children: [
            ...homeRoute,
            ...helpAndFeedbackRoute,
            ...personalCenterRoute,
            ...latestLostRoute,
            ...alreadyClaimedRoute,
            ...categoryPreviewRoute,

        ],
    },
    ...loginRoute,
    ...aboutRoute,
    ...errorRoute
];
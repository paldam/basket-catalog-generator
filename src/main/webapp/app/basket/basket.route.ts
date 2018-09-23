import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import {BasketComponent} from './basket.component';

// const ADMIN_ROUTES = [auditsRoute, configurationRoute, docsRoute, healthRoute, logsRoute, ...userMgmtRoute, metricsRoute];

export const BASKET_ROUTE: Routes = [
    {
        path: 'basket',
        component: BasketComponent,
        data: {
            authorities: ['ROLE_ADMIN']
        },
        canActivate: [UserRouteAccessService],

    }
];

import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import {BasketComponent} from './baskets-picker/basket.component';
import {BasketCustomizeComponent} from './basket-customize/basket-customize.component';

// const ADMIN_ROUTES = [auditsRoute, configurationRoute, docsRoute, healthRoute, logsRoute, ...userMgmtRoute, metricsRoute];

export const BASKET_ROUTE: Routes = [
    {
        path: 'basket',
        component: BasketComponent,
        data: {
            authorities: ['ROLE_ADMIN']
        },
        canActivate: [UserRouteAccessService],

    },
    {
        path: 'basketcustomize',
        component: BasketCustomizeComponent,
        data: {
            authorities: ['ROLE_ADMIN']
        },
        canActivate: [UserRouteAccessService],

    },

];

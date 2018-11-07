import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { BasketPickerComponent } from './baskets-picker/basket-picker.component';
import { BasketCustomizeComponent } from './basket-customize/basket-customize.component';
import { CatalogGeneratorCoreComponent } from './catalog-generator-core/catalog-generator-core.component';
import { BasketResolve } from './basket-reolve';

// const ADMIN_ROUTES = [auditsRoute, configurationRoute, docsRoute, healthRoute, logsRoute, ...userMgmtRoute, metricsRoute];

export const BASKET_ROUTE: Routes = [
    {
        path: 'generator',
        component: CatalogGeneratorCoreComponent,

        data: {
            authorities: ['ROLE_USER']
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'basketcustomize',
        component: BasketCustomizeComponent,
        data: {
            authorities: ['ROLE_USER']
        },
        canActivate: [UserRouteAccessService]
    }
];

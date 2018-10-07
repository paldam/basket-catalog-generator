import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { CatalogListComponent } from './catalog-list/catalog-list.component';

// const ADMIN_ROUTES = [auditsRoute, configurationRoute, docsRoute, healthRoute, logsRoute, ...userMgmtRoute, metricsRoute];

export const GENERATED_CATALOG_ROUTE: Routes = [
    {
        path: 'cataloglist',
        component: CatalogListComponent,
        data: {
            authorities: ['ROLE_ADMIN']
        },
        canActivate: [UserRouteAccessService]
    }
];

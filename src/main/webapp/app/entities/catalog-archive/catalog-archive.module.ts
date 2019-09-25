import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DialogModule, ProgressSpinnerModule } from 'primeng/primeng';

import { BasketExtSharedModule } from 'app/shared';
import {
    CatalogArchiveComponent,
    CatalogArchiveDetailComponent,
    CatalogArchiveUpdateComponent,
    CatalogArchiveDeletePopupComponent,
    CatalogArchiveDeleteDialogComponent,
    catalogArchiveRoute,
    catalogArchivePopupRoute
} from './';

const ENTITY_STATES = [...catalogArchiveRoute, ...catalogArchivePopupRoute];

@NgModule({
    imports: [BasketExtSharedModule, ProgressSpinnerModule, DialogModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatalogArchiveComponent,
        CatalogArchiveDetailComponent,
        CatalogArchiveUpdateComponent,
        CatalogArchiveDeleteDialogComponent,
        CatalogArchiveDeletePopupComponent
    ],
    entryComponents: [
        CatalogArchiveComponent,
        CatalogArchiveUpdateComponent,
        CatalogArchiveDeleteDialogComponent,
        CatalogArchiveDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BasketExtCatalogArchiveModule {}

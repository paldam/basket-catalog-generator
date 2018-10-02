import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import {
    BasketComponent,
    BasketDetailComponent,
    BasketUpdateComponent,
    BasketDeletePopupComponent,
    BasketDeleteDialogComponent,
    basketRoute,
    basketPopupRoute
} from './';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { BasketExtSharedModule } from '../../shared/shared.module';
import { HttpClientModule } from '@angular/common/http';

const ENTITY_STATES = [...basketRoute, ...basketPopupRoute];

@NgModule({
    imports: [BasketExtSharedModule, HttpClientModule, BrowserModule, FormsModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [BasketComponent, BasketDetailComponent, BasketUpdateComponent, BasketDeleteDialogComponent, BasketDeletePopupComponent],
    entryComponents: [BasketComponent, BasketUpdateComponent, BasketDeleteDialogComponent, BasketDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BasketCatalogBasketModule {}

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BasketExtSharedModule } from '../shared/';
import {BasketComponent} from './baskets-picker/basket.component';
import {BASKET_ROUTE} from './basket.route';
import {BasketService} from "./basket.service";
import {DataTableModule} from 'primeng/primeng';
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { SelectedBasketsComponent } from './selected-baskets/selected-baskets.component';
import { BasketCustomizeComponent } from './basket-customize/basket-customize.component';
import {TableModule} from "primeng/table";

@NgModule({
    imports: [BasketExtSharedModule, RouterModule.forChild(BASKET_ROUTE),DataTableModule,BrowserModule,
        BrowserAnimationsModule,TableModule],
    declarations: [
        BasketComponent,
        SelectedBasketsComponent,
        BasketCustomizeComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]   ,
    providers: [BasketService],
})
export class BasketModule {}

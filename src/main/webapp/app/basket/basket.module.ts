import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BasketExtSharedModule } from 'app/shared';
import {BasketComponent} from './basket.component';
import {BASKET_ROUTE} from './basket.route';

@NgModule({
    imports: [BasketExtSharedModule, RouterModule.forChild(BASKET_ROUTE)],
    declarations: [
        BasketComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BasketModule {}

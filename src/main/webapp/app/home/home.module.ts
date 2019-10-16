import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BasketExtSharedModule } from 'app/shared';
import { HomeComponent, RegisterComponent2 } from './';
import { RoutingState } from 'app/routing-stage';

@NgModule({
    imports: [BasketExtSharedModule],
    providers: [RoutingState],
    declarations: [HomeComponent, RegisterComponent2],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BasketExtHomeModule {}

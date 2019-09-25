import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BasketExtCatalogArchiveModule } from './catalog-archive/catalog-archive.module';
import { BasketExtProductModule } from './product/product.module';
import { BasketCatalogBasketModule } from './basket/basket.module';
import { DialogModule, ProgressSpinnerModule } from 'primeng/primeng';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        BasketExtCatalogArchiveModule,
        BasketExtProductModule,
        BasketCatalogBasketModule,
        ProgressSpinnerModule,
        DialogModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BasketExtEntityModule {}

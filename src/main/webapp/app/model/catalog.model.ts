
import {BasketExt} from './ext-basket.model';
import {CatalogDetails} from './catalog-details.model';
export class Catalog{

    constructor(
        public baskets?: BasketExt[],
        public catalogDetails?: CatalogDetails,
    ) {
    }
}

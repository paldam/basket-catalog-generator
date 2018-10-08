import { IProduct } from 'app/shared/model//product.model';
import { ICatalogArchive } from 'app/shared/model//catalog-archive.model';
import { Product } from './product.model';

export interface IBasket {
    id?: number;
    basketName?: string;
    basketPrice?: number;
    orginBasketId?: number;
    products?: IProduct[];
    catalogArchives?: ICatalogArchive[];
}

export class Basket implements IBasket {
    constructor(
        public basketName?: string,
        public basketTotalPrice?: number,
        public orginBasketId?: number,
        public products?: Product[],
        public catalogArchives?: ICatalogArchive[],
        public id?: number
    ) {}
}

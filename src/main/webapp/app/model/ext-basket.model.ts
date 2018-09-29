import {BasketItemsExt} from './ext-basket-items.model';
export class BasketExt{

    constructor(
        public basketName?: string,
        public BasketOrginId?: number,
        public basketPrice?: number,
        public basketItems?: BasketItemsExt[]
    ) {
    }
}

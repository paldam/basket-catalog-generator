import {BasketType} from './basket-type.model';
import {BasketItems} from './basket-items.model';

export class Basket{

    constructor(
        public basketId?: number,
        public basketName?: string,
        public basketType?: BasketType,
        public basketItems?: BasketItems[],
        public basketTotalPrice?:number,
        public season?: string,
    ) {
    }
}

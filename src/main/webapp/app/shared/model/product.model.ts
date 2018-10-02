import { IBasket } from 'app/shared/model//basket.model';

export interface IProduct {
    id?: number;
    productName?: string;
    productCapacity?: string;
}

export class Product implements IProduct {
    constructor(public productName?: string, public productCapacity?: string, public id?: number) {}
}

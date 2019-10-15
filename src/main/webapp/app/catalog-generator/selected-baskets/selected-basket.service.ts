import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class SelectedBasketService {
    public tabIndex: number = 0;

    public selectedBasket: any[] = [];
    public selectedBasketIds: any[] = [];
    public numberOfBasket = 0;

    constructor() {}

    addBasket(basket: any) {
        basket.ownPrice = basket.basketTotalPrice / 100;
        this.selectedBasket.push(basket);
        this.numberOfBasket = this.selectedBasket.length;
        this.selectedBasketIds.push(basket.basketId);
    }

    removeBasket(basketId: number) {
        this.selectedBasket = this.selectedBasket.filter(basket => basket.basketId != basketId);
    }

    openNext() {
        this.tabIndex = this.tabIndex + 1;
    }
}

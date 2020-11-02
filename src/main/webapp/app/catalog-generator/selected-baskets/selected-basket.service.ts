import { Injectable } from '@angular/core';
import { Basket } from 'app/shared/model/basket.model';

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

        console.log(
            this.selectedBasket.forEach((value: Basket) => {
                console.log(value.basketName);
            })
        );
    }

    removeBasket(basketId: number) {
        this.selectedBasket = this.selectedBasket.filter(basket => basket.basketId != basketId);
    }

    openNext() {
        this.tabIndex = this.tabIndex + 1;
    }
}

import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class SelectedBasketService {

    public selectedBasket: any[] = [];
    public numberOfBasket = 0;

    constructor() {
    }

    addBasket(basket: any) {
              this.selectedBasket.push(basket);
              this.numberOfBasket = this.selectedBasket.length;
    }
}

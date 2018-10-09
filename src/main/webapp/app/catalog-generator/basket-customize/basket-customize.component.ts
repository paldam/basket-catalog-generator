import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { SelectedBasketService } from '../selected-baskets/selected-basket.service';
import { Basket } from '../../shared/model/basket.model';

@Component({
    selector: 'jhi-basket-customize',
    templateUrl: './basket-customize.component.html',
    styleUrls: ['./basket-customize.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class BasketCustomizeComponent implements OnInit {
    public basketToCustomize: any[] = [];
    public selectedImgeToPreview: number;
    selectCars: Basket[];

    constructor(public selectedBasketService: SelectedBasketService) {}

    ngOnInit() {}

    selectImageToPreview(basketId: any) {
        this.selectedImgeToPreview = basketId;
    }

    UpOnList(basketId: number) {
        if (this.selectedBasketService.selectedBasket.length > 1) {
            console.log('Wpdło 1');
            let vars = this.selectedBasketService.selectedBasket.findIndex(basket => basket.basketId == basketId);
            console.log('Wpdło 2 : ' + vars);
            if (vars != 0) {
                console.log('ERRRRRRRRRRRRRRr ');
                let tmpElement = this.selectedBasketService.selectedBasket[vars - 1];
                this.selectedBasketService.selectedBasket[vars - 1] = this.selectedBasketService.selectedBasket[vars];
                this.selectedBasketService.selectedBasket[vars] = tmpElement;
            }
        }
    }

    DownOnList(basketId: number) {
        if (this.selectedBasketService.selectedBasket.length > 1) {
            console.log('Wpdło 1');
            let vars = this.selectedBasketService.selectedBasket.findIndex(basket => basket.basketId == basketId);
            console.log('Wpdło 2 : ' + vars);
            if (vars != this.selectedBasketService.selectedBasket.length - 1) {
                console.log('ERRRRRRRRRRRRRRr');
                let tmpElement = this.selectedBasketService.selectedBasket[vars + 1];
                this.selectedBasketService.selectedBasket[vars + 1] = this.selectedBasketService.selectedBasket[vars];
                this.selectedBasketService.selectedBasket[vars] = tmpElement;
            }
        }
    }
}

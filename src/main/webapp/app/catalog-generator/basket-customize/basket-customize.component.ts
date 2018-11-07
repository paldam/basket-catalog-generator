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
    public bland: string;
    public row: any;
    public col: any;

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

    onTableEditInit(event: any) {
        console.log('WWWWWWWWWW');
        console.log(event.data);
        event.data.ownPrice = 0;
    }

    onTableEditComplete(event: any) {
        //    let regexp = new RegExp('^(?=.*?\\d)\\d*[.]?\\d{1,2}$');
        //    let  test = regexp.test(event.data.ownPrice);
        // alert("To pole musi zawierać format waluty z kropką np : 0.59 , 2.5 , 0.99 , 19.99"); // will display true
        //
        // console.log(regexp.test(event.data.ownPrice));
    }

    valid() {}
}

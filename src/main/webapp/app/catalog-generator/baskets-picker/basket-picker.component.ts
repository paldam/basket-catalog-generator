import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { CatalogGeneratorService } from '../catalog-generator.service';
import { SelectedBasketService } from '../selected-baskets/selected-basket.service';
import { MenuItem } from 'primeng/api';
import { Basket } from '../../shared/model/basket.model';
import { Observable } from 'rxjs/Rx';

@Component({
    selector: 'jhi-basket',
    templateUrl: './basket-picker.component.html',
    styleUrls: ['./basket-picker.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class BasketPickerComponent implements OnInit {
    public baskets: any[] = [];
    public basketfiltered: any[] = [];

    public selectedImgeToPreview: number;
    public items: MenuItem[];
    public rangeValues: number[] = [0, 0];
    public rangeConst: number[] = [0, 0];
    public selectedBasketOnContextMenu: Basket = new Basket();
    display: boolean = false;

    filtersLoaded: Promise<boolean>;

    constructor(private catalogGeneratorService: CatalogGeneratorService, private selectedBasketService: SelectedBasketService) {
        this.catalogGeneratorService
            .getBasket()
            .toPromise()
            .then(res => {
                this.baskets = res.body;
                this.basketfiltered = res.body;

                // for (var item of this.baskets) {
                //     item.basketTotalPrice = item.basketTotalPrice/100
                // }
                //
                // for (var item of this.basketfiltered) {
                //     item.basketTotalPrice = item.basketTotalPrice/100
                // }

                console.log(this.baskets);

                this.rangeConst[0] = this.rangeValues[0] = Math.min.apply(
                    Math,
                    this.basketfiltered.map(function(o) {
                        return o.basketTotalPrice;
                    })
                );
                this.rangeConst[1] = this.rangeValues[1] = Math.max.apply(
                    Math,
                    this.basketfiltered.map(function(o) {
                        return o.basketTotalPrice;
                    })
                );

                this.filtersLoaded = Promise.resolve(true);
                console.log(this.selectedBasketService.selectedBasket);
            });
    }

    ngOnInit() {
        this.items = [
            {
                label: 'Dodaj kosz',
                icon: 'fa fa-plus',
                command: event => this.addBasket(this.selectedBasketOnContextMenu)
            }
        ];
        setTimeout(() => {
            console.log('DDDDDD' + JSON.stringify(this.baskets));
        }, 3000);
    }

    addBasket(basket: any) {
        this.selectedBasketService.addBasket(basket);
    }

    selectImageToPreview(basketId: any) {
        this.selectedImgeToPreview = basketId;
    }

    contextMenuSelected(event) {
        this.selectedBasketOnContextMenu = event.data;
    }

    sortBasketDESC() {
        this.basketfiltered.sort((a, b): number => {
            return b.basketTotalPrice - a.basketTotalPrice;
        });
    }

    sortBasketASC() {
        this.basketfiltered.sort((a, b): number => {
            return a.basketTotalPrice - b.basketTotalPrice;
        });
    }

    filterByPriceRange(a: number, b: number) {
        console.log('Zakres do sortowania od ' + a + ' do ' + b);

        this.basketfiltered = this.baskets.filter((basket: Basket) => {
            return basket.basketTotalPrice >= a && basket.basketTotalPrice <= b;
        });
    }

    getButtonClass(basketId: number) {
        let vars = this.selectedBasketService.selectedBasketIds.indexOf(basketId);

        if (vars != -1) {
            let cssClasses = {
                'added-basket-card': true
            };
            return cssClasses;
        } else {
            return null;
        }
    }

    idExistsOnSelectedBasket(basketId: number): boolean {
        return this.selectedBasketService.selectedBasket.some(vendor => vendor['basketId'] == basketId);
    }

    removeFromSelectedBasketList(basketId: number) {
        this.selectedBasketService.removeBasket(basketId);
    }

    showDialog() {
        this.display = true;
    }

    closeDialog() {
        this.display = false;
    }
}

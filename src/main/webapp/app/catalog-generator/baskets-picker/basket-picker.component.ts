import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { CatalogGeneratorService } from '../catalog-generator.service';
import { SelectedBasketService } from '../selected-baskets/selected-basket.service';
import { MenuItem } from 'primeng/api';
import { Basket } from '../../shared/model/basket.model';

@Component({
    selector: 'jhi-basket',
    templateUrl: './basket-picker.component.html',
    styleUrls: ['./basket-picker.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class BasketPickerComponent implements OnInit {
    public baskets: any[] = [];
    public selectedImgeToPreview: number;
    public items: MenuItem[];
    public people: any[] = [];
    public selectedBasketOnContextMenu: Basket = new Basket();

    constructor(private catalogGeneratorService: CatalogGeneratorService, private selectedBasketService: SelectedBasketService) {
        catalogGeneratorService.getBasket().subscribe(data => {
            this.baskets = data.body;

            console.log(JSON.stringify(this.baskets));
        });
    }

    ngOnInit() {
        this.items = [{ label: 'Dodaj kosz', icon: 'fa fa-plus', command: event => this.addBasket(this.selectedBasketOnContextMenu) }];

        this.people = [
            {
                name: 'Douglas  Pace'
            },
            {
                name: 'Mcleod  Mueller'
            },
            {
                name: 'Day  Meyers'
            },
            {
                name: 'Aguirre  Ellis'
            },
            {
                name: 'Cook  Tyson'
            },
            {
                name: 'Mcleod  Mueller'
            },
            {
                name: 'Day  Meyers'
            },
            {
                name: 'Aguirre  Ellis'
            },
            {
                name: 'Cook  Tyson'
            }
        ];
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
}

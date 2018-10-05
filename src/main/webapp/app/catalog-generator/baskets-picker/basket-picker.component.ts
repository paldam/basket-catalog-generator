import { Component, OnInit } from '@angular/core';
import { CatalogGeneratorService } from '../catalog-generator.service';
import { SelectedBasketService } from '../selected-baskets/selected-basket.service';

@Component({
    selector: 'jhi-basket',
    templateUrl: './basket-picker.component.html',
    styleUrls: ['./basket-picker.component.css']
})
export class BasketPickerComponent implements OnInit {
    public baskets: any[] = [];
    public selectedImgeToPreview: number;

    constructor(private catalogGeneratorService: CatalogGeneratorService, private selectedBasketService: SelectedBasketService) {
        catalogGeneratorService.getBasket().subscribe(data => {
            this.baskets = data.body;

            console.log(JSON.stringify(this.baskets));
        });
    }

    ngOnInit() {}

    addBasket(basket: any) {
        this.selectedBasketService.addBasket(basket);
    }

    selectImageToPreview(basketId: any) {
        this.selectedImgeToPreview = basketId;
    }
}

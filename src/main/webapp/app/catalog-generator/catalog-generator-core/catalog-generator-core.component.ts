import { Component, OnInit } from '@angular/core';
import { SelectedBasketService } from '../selected-baskets/selected-basket.service';

@Component({
    selector: 'jhi-catalog-generator-core',
    templateUrl: './catalog-generator-core.component.html',
    styleUrls: ['./catalog-generator-core.component.css']
})
export class CatalogGeneratorCoreComponent implements OnInit {
    constructor(private selectedBasketService: SelectedBasketService) {}

    ngOnInit() {}

    isTabDisabled(): boolean {
        if (this.selectedBasketService.numberOfBasket > 0) {
            return false;
        } else {
            return true;
        }
    }
}

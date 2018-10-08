import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { SelectedBasketService } from '../selected-baskets/selected-basket.service';

@Component({
    selector: 'jhi-catalog-generator-core',
    templateUrl: './catalog-generator-core.component.html',
    styleUrls: ['./catalog-generator-core.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class CatalogGeneratorCoreComponent implements OnInit {
    constructor(private selectedBasketService: SelectedBasketService) {}

    ngOnInit() {}

    isTabDisabled(): boolean {
        if (this.selectedBasketService.selectedBasket.length > 0) {
            return false;
        } else {
            return true;
        }
    }
}

import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { SelectedBasketService } from '../selected-baskets/selected-basket.service';

@Component({
    selector: 'jhi-catalog-generator-core',
    templateUrl: './catalog-generator-core.component.html',
    styleUrls: ['./catalog-generator-core.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class CatalogGeneratorCoreComponent implements OnInit {
    index: number = 0;
    constructor(public selectedBasketService: SelectedBasketService) {}

    ngOnInit() {}

    isTabDisabled(): boolean {
        if (this.selectedBasketService.selectedBasket.length > 0) {
            return false;
        } else {
            return true;
        }
    }

    handleChange(e) {
        this.index = e.index;
    }

    openNext() {
        this.index = this.index === 2 ? 0 : this.index + 1;
    }

    openPrev() {
        this.index = this.index === 0 ? 2 : this.index - 1;
    }

    openFirst() {
        this.index = 0;
    }
}

import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { SelectedBasketService } from '../selected-baskets/selected-basket.service';

@Component({
    selector: 'jhi-basket-customize',
    templateUrl: './basket-customize.component.html',
    styleUrls: ['./basket-customize.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class BasketCustomizeComponent implements OnInit {
    public basketToCustomize: any[] = [];
    public selectedImgeToPreview: number;

    constructor(public selectedBasketService: SelectedBasketService) {}

    ngOnInit() {}
    selectImageToPreview(basketId: any) {
        this.selectedImgeToPreview = basketId;
    }
}

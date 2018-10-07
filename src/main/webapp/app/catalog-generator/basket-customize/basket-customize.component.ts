import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { SelectedBasketService } from '../selected-baskets/selected-basket.service';
import { MenuItem } from 'primeng/api';
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
    public items: MenuItem[];
    selectCars: Basket[];

    constructor(public selectedBasketService: SelectedBasketService) {}

    ngOnInit() {}
    selectImageToPreview(basketId: any) {
        this.selectedImgeToPreview = basketId;

        this.items = [{ label: 'Dodaj kosz', icon: 'fa fa-plus', command: event => null }];
    }
}

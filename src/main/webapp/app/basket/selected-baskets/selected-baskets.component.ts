import { Component, OnInit } from '@angular/core';
import {SelectedBasketService} from './selected-basket.service';

@Component({
  selector: 'jhi-selected-baskets',
  templateUrl: './selected-baskets.component.html',
  styleUrls: ['./selected-baskets.component.css']
})
export class SelectedBasketsComponent implements OnInit {

    constructor(private selectedBasketService: SelectedBasketService) { }

    ngOnInit() {
    }

    get numberOfBasket() {
        return this.selectedBasketService.numberOfBasket;
    }

}

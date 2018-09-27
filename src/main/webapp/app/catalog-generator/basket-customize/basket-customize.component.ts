import { Component, OnInit } from '@angular/core';
import {SelectedBasketService} from '../selected-baskets/selected-basket.service';

@Component({
  selector: 'jhi-basket-customize',
  templateUrl: './basket-customize.component.html',
  styleUrls: ['./basket-customize.component.css']
})
export class BasketCustomizeComponent implements OnInit {

    public basketToCustomize: any[] = [];

  constructor(private  selectedBasketService: SelectedBasketService) {
      this.basketToCustomize = selectedBasketService.selectedBasket;

  }

  ngOnInit() {
  }

}

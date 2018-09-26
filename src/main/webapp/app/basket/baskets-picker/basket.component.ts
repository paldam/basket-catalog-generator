import { Component, OnInit } from '@angular/core';
import {BasketService} from '../basket.service';
import {SelectedBasketService} from '../selected-baskets/selected-basket.service';

@Component({
  selector: 'jhi-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {

    public baskets: any[] = [];

  constructor(private basketService: BasketService, private  selectedBasketService: SelectedBasketService) {
                basketService.getBasket().subscribe(data => {
                    this.baskets = data.body;
                    console.log("KKKKKKKKKKKKKKKKKKKKKKKKK " + data.body)
                });
  }

  ngOnInit() {
  }

  addBasket(basket: any)  {
        this.selectedBasketService.addBasket(basket);
  }

}

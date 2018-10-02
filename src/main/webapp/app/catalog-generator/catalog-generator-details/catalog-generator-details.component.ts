import { Component, OnInit } from '@angular/core';
import { ViewEncapsulation } from '@angular/core';
import { SelectedBasketService } from '../selected-baskets/selected-basket.service';
import { NgForm } from '@angular/forms';
import { CatalogGeneratorService } from '../catalog-generator.service';
import { CatalogArchive, Theme } from '../../shared/model/catalog-archive.model';
import { Basket } from '../../shared/model/basket.model';
import { Product } from '../../shared/model/product.model';

@Component({
    selector: 'jhi-catalog-generator-details',
    templateUrl: './catalog-generator-details.component.html',
    styleUrls: ['./catalog-generator-details.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class CatalogGeneratorDetailsComponent implements OnInit {
    public catalogArchive: CatalogArchive = new CatalogArchive();
    public baskets: Basket[] = [];
    public formSubmitted: boolean = false;
    public loading: string = '...';

    constructor(private selectedBasketService: SelectedBasketService, private catalogGeneratorService: CatalogGeneratorService) {}

    ngOnInit() {
        this.catalogArchive.catalogAdditionalDesc = 'Oferta przygotwana dla firmy EnterStroe';
        this.catalogArchive.catalogName = 'Kosze świąteczne 2018';
        this.catalogArchive.catalogTheme = Theme.NIEBIESKI;
        this.catalogArchive.customerAssistantEmail = 'dami@onet.eu';
        this.catalogArchive.customerAssistantName = 'Kamil Nowak';

        this.catalogArchive.customerAssistantTel = '508703333';
        this.catalogArchive.forWho = 'EnterStore Damian Paluch';
    }

    submitForm(form: NgForm) {
        this.formSubmitted = true;

        if (form.valid) {
            this.selectedBasketService.selectedBasket.forEach(basket => {
                let productToAdd: Product[] = [];

                basket.basketItems.forEach(basketI => {
                    productToAdd.push(new Product(basketI.product.productName, basketI.product.capacity));
                });

                console.log('Jakie produkty !!!! ' + JSON.stringify(productToAdd[0].productName));

                this.baskets.push(new Basket(basket.basketName, basket.basketTotalPrice, basket.basketId, productToAdd));
                productToAdd = [];
            });

            this.catalogArchive.baskets = this.baskets;

            this.genCatalog(this.catalogArchive);
        }

        this.formSubmitted = false;
    }

    genCatalog(catalog: CatalogArchive) {
        this.catalogGeneratorService.sendCataloqDataToGeneratePdF(catalog).subscribe(res => {
            var fileURL = URL.createObjectURL(res);
            window.open(fileURL);

            this.loading = 'załadowano';
        });
    }
}

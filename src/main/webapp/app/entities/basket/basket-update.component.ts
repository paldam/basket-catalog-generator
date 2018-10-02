import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBasket } from 'app/shared/model/basket.model';
import { BasketService } from './basket.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product';
import { ICatalogArchive } from 'app/shared/model/catalog-archive.model';
import { CatalogArchiveService } from 'app/entities/catalog-archive';

@Component({
    selector: 'jhi-basket-update',
    templateUrl: './basket-update.component.html'
})
export class BasketUpdateComponent implements OnInit {
    private _basket: IBasket;
    isSaving: boolean;

    products: IProduct[];

    catalogarchives: ICatalogArchive[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private basketService: BasketService,
        private productService: ProductService,
        private catalogArchiveService: CatalogArchiveService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ basket }) => {
            this.basket = basket;
        });
        this.productService.query().subscribe(
            (res: HttpResponse<IProduct[]>) => {
                this.products = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.catalogArchiveService.query().subscribe(
            (res: HttpResponse<ICatalogArchive[]>) => {
                this.catalogarchives = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.basket.id !== undefined) {
            this.subscribeToSaveResponse(this.basketService.update(this.basket));
        } else {
            this.subscribeToSaveResponse(this.basketService.create(this.basket));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBasket>>) {
        result.subscribe((res: HttpResponse<IBasket>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProductById(index: number, item: IProduct) {
        return item.id;
    }

    trackCatalogArchiveById(index: number, item: ICatalogArchive) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get basket() {
        return this._basket;
    }

    set basket(basket: IBasket) {
        this._basket = basket;
    }
}

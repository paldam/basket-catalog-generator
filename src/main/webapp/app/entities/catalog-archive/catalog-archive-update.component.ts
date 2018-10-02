import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ICatalogArchive } from 'app/shared/model/catalog-archive.model';
import { CatalogArchiveService } from './catalog-archive.service';
import { IBasket } from 'app/shared/model/basket.model';
import { BasketService } from 'app/entities/basket';

@Component({
    selector: 'jhi-catalog-archive-update',
    templateUrl: './catalog-archive-update.component.html'
})
export class CatalogArchiveUpdateComponent implements OnInit {
    private _catalogArchive: ICatalogArchive;
    isSaving: boolean;

    baskets: IBasket[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private catalogArchiveService: CatalogArchiveService,
        private basketService: BasketService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catalogArchive }) => {
            this.catalogArchive = catalogArchive;
        });
        this.basketService.query().subscribe(
            (res: HttpResponse<IBasket[]>) => {
                this.baskets = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.catalogArchive.id !== undefined) {
            this.subscribeToSaveResponse(this.catalogArchiveService.update(this.catalogArchive));
        } else {
            this.subscribeToSaveResponse(this.catalogArchiveService.create(this.catalogArchive));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatalogArchive>>) {
        result.subscribe((res: HttpResponse<ICatalogArchive>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBasketById(index: number, item: IBasket) {
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
    get catalogArchive() {
        return this._catalogArchive;
    }

    set catalogArchive(catalogArchive: ICatalogArchive) {
        this._catalogArchive = catalogArchive;
    }
}

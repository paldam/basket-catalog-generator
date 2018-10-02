import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ICatalogArchive } from 'app/shared/model/catalog-archive.model';
import { Principal } from 'app/core';
import { CatalogArchiveService } from './catalog-archive.service';

@Component({
    selector: 'jhi-catalog-archive',
    templateUrl: './catalog-archive.component.html'
})
export class CatalogArchiveComponent implements OnInit, OnDestroy {
    catalogArchives: ICatalogArchive[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private catalogArchiveService: CatalogArchiveService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.catalogArchiveService.query().subscribe(
            (res: HttpResponse<ICatalogArchive[]>) => {
                this.catalogArchives = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCatalogArchives();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICatalogArchive) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInCatalogArchives() {
        this.eventSubscriber = this.eventManager.subscribe('catalogArchiveListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

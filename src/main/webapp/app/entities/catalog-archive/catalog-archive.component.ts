import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { CatalogArchive, ICatalogArchive } from 'app/shared/model/catalog-archive.model';
import { Principal } from 'app/core';
import { CatalogArchiveService } from './catalog-archive.service';
import { CatalogGeneratorService } from '../../catalog-generator/catalog-generator.service';
import { saveAs } from 'file-saver/FileSaver';

@Component({
    selector: 'jhi-catalog-archive',
    templateUrl: './catalog-archive.component.html',
    styleUrls: ['./catalog-archive.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class CatalogArchiveComponent implements OnInit, OnDestroy {
    catalogArchives: ICatalogArchive[];
    currentAccount: any;
    eventSubscriber: Subscription;
    public showPdfGenStatusModal: boolean = false;
    public generatedPdf: any;
    fileFilterLoaded: Promise<boolean>;

    constructor(
        private catalogArchiveService: CatalogArchiveService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private catalogGeneratorService: CatalogGeneratorService
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
        console.log('dfsfdsf' + this.catalogArchives);
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

    genCatalog(id: number) {
        this.showPdfGenStatusModal = true;

        this.catalogGeneratorService.reGenerteCatalog(id).subscribe(res => {
            setTimeout(() => {
                this.fileFilterLoaded = Promise.resolve(true);
            }, 1800);
            this.generatedPdf = res;
        });
    }

    openPdfInBrowser() {
        //window.open(this.generatedUrl);
        let d = new Date();
        let pdfName = 'katalog_' + d.toLocaleDateString() + ' ' + d.toLocaleTimeString() + '.pdf';

        saveAs(this.generatedPdf, pdfName);
        this.showPdfGenStatusModal = false;
        this.generatedPdf = [];

        // setTimeout(() => {
        //     this.router.navigateByUrl('/catalog-archive');
        // }, 1000);
    }
}

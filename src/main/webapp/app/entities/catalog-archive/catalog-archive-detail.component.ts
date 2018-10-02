import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ICatalogArchive } from 'app/shared/model/catalog-archive.model';

@Component({
    selector: 'jhi-catalog-archive-detail',
    templateUrl: './catalog-archive-detail.component.html'
})
export class CatalogArchiveDetailComponent implements OnInit {
    catalogArchive: ICatalogArchive;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catalogArchive }) => {
            this.catalogArchive = catalogArchive;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}

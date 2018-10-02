import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatalogArchive } from 'app/shared/model/catalog-archive.model';
import { CatalogArchiveService } from './catalog-archive.service';

@Component({
    selector: 'jhi-catalog-archive-delete-dialog',
    templateUrl: './catalog-archive-delete-dialog.component.html'
})
export class CatalogArchiveDeleteDialogComponent {
    catalogArchive: ICatalogArchive;

    constructor(
        private catalogArchiveService: CatalogArchiveService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catalogArchiveService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catalogArchiveListModification',
                content: 'Deleted an catalogArchive'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-catalog-archive-delete-popup',
    template: ''
})
export class CatalogArchiveDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catalogArchive }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatalogArchiveDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.catalogArchive = catalogArchive;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CatalogArchive } from 'app/shared/model/catalog-archive.model';
import { CatalogArchiveService } from './catalog-archive.service';
import { CatalogArchiveComponent } from './catalog-archive.component';
import { CatalogArchiveDetailComponent } from './catalog-archive-detail.component';
import { CatalogArchiveUpdateComponent } from './catalog-archive-update.component';
import { CatalogArchiveDeletePopupComponent } from './catalog-archive-delete-dialog.component';
import { ICatalogArchive } from 'app/shared/model/catalog-archive.model';

@Injectable({ providedIn: 'root' })
export class CatalogArchiveResolve implements Resolve<ICatalogArchive> {
    constructor(private service: CatalogArchiveService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((catalogArchive: HttpResponse<CatalogArchive>) => catalogArchive.body));
        }
        return of(new CatalogArchive());
    }
}

export const catalogArchiveRoute: Routes = [
    {
        path: 'catalog-archive',
        component: CatalogArchiveComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogArchives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-archive/:id/view',
        component: CatalogArchiveDetailComponent,
        resolve: {
            catalogArchive: CatalogArchiveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogArchives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-archive/new',
        component: CatalogArchiveUpdateComponent,
        resolve: {
            catalogArchive: CatalogArchiveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogArchives'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-archive/:id/edit',
        component: CatalogArchiveUpdateComponent,
        resolve: {
            catalogArchive: CatalogArchiveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogArchives'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catalogArchivePopupRoute: Routes = [
    {
        path: 'catalog-archive/:id/delete',
        component: CatalogArchiveDeletePopupComponent,
        resolve: {
            catalogArchive: CatalogArchiveResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogArchives'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

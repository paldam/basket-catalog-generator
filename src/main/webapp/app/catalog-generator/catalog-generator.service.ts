import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { LocalStorageService, SessionStorageService } from 'ngx-webstorage';
import { SERVER_API_URL } from '../app.constants';
import { ResponseContentType } from '@angular/http';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { CatalogArchive } from '../shared/model/catalog-archive.model';

@Injectable()
export class CatalogGeneratorService {
    private resourceUrl = SERVER_API_URL + '/extbaskets';

    constructor(
        private http: HttpClient,
        private http2: Http,
        private localStorage: LocalStorageService,
        private sessionStorage: SessionStorageService
    ) {}

    getBasket(): Observable<HttpResponse<any>> {
        const token = this.localStorage.retrieve('authenticationToken') || this.sessionStorage.retrieve('authenticationToken');

        return this.http.get(this.resourceUrl, {
            headers: { ['Authorization']: 'Bearer ' + token },
            observe: 'response'
        });
    }

    sendCataloqDataToGeneratePdF(catalog: CatalogArchive): any {
        return this.http2.post(SERVER_API_URL + 'api/generatecatalog', catalog, { responseType: ResponseContentType.Blob }).map(res => {
            return new Blob([res.blob()], { type: 'application/pdf' });
        });
    }
}

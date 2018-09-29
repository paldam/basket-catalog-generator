import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {LocalStorageService, SessionStorageService} from 'ngx-webstorage';
import {Basket} from '../model/basket.model';
import {Catalog} from '../model/catalog.model';
import {SERVER_API_URL} from '../app.constants';

@Injectable()
export class CatalogGeneratorService {

    constructor(private http: HttpClient, private localStorage: LocalStorageService, private sessionStorage: SessionStorageService) {
    }

    getBasket(): Observable<HttpResponse<any>> {

        const token = this.localStorage.retrieve('authenticationToken') || this.sessionStorage.retrieve('authenticationToken');

        return this.http.get('http://localhost:8060/extbaskets', {
            headers: {['Authorization']: 'Bearer ' + token},
            observe: 'response'
        });

    }


    sendCataloqDataToGeneratePdF(catalog: Catalog): Observable<any> {

        return this.http.post(SERVER_API_URL + 'api/generatecatalog', catalog);
    }

}

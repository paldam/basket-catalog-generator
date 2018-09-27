import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {LocalStorageService, SessionStorageService} from 'ngx-webstorage';

@Injectable()
export class CatalogGeneratorService {

    constructor(private http: HttpClient, private localStorage: LocalStorageService, private sessionStorage: SessionStorageService) {
    }
       getBasket( ): Observable<HttpResponse<any>> {

        const token = this.localStorage.retrieve('authenticationToken') || this.sessionStorage.retrieve('authenticationToken');

        return this.http.get('http://localhost:8060/extbaskets', {headers: {['Authorization']: 'Bearer ' + token }, observe: 'response' });

    }

}

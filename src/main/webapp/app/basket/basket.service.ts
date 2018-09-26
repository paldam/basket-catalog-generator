import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {SERVER_API_URL} from '../app.constants';
import {LocalStorageService, SessionStorageService} from "ngx-webstorage";

@Injectable()
export class BasketService {

    constructor(private http: HttpClient,private localStorage: LocalStorageService, private sessionStorage: SessionStorageService) {
    }
       getBasket( ): Observable<HttpResponse<any>> {
           
           const token = this.localStorage.retrieve('authenticationToken') || this.sessionStorage.retrieve('authenticationToken');

           let headers = new HttpHeaders ;

           if (token) {
               headers = headers.append('Authorization', 'Bearer ' + token);
           }


        return this.http.get('http://localhost:8060/extbaskets', {headers: headers, observe: 'response' });

    }

}

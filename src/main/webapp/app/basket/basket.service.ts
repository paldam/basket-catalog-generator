import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {SERVER_BASKET_API_URL} from '../app.constants';

@Injectable()
export class BasketService {

    constructor(private http: HttpClient) {
    }
       getBasket( ): Observable<HttpResponse<any>> {
        return this.http.get(SERVER_BASKET_API_URL + '/extbaskets/', { observe: 'response' });
    }

}

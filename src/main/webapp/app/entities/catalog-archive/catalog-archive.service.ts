import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatalogArchive } from 'app/shared/model/catalog-archive.model';

type EntityResponseType = HttpResponse<ICatalogArchive>;
type EntityArrayResponseType = HttpResponse<ICatalogArchive[]>;

@Injectable({ providedIn: 'root' })
export class CatalogArchiveService {
    private resourceUrl = SERVER_API_URL + 'api/catalog-archives';

    constructor(private http: HttpClient) {}

    create(catalogArchive: ICatalogArchive): Observable<EntityResponseType> {
        return this.http.post<ICatalogArchive>(this.resourceUrl, catalogArchive, { observe: 'response' });
    }

    update(catalogArchive: ICatalogArchive): Observable<EntityResponseType> {
        return this.http.put<ICatalogArchive>(this.resourceUrl, catalogArchive, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICatalogArchive>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICatalogArchive[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

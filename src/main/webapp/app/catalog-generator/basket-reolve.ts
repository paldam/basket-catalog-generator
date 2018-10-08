import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from '@angular/router';
import { CatalogGeneratorService } from './catalog-generator.service';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class BasketResolve implements Resolve<any> {
    constructor(private catalogGeneratorService: CatalogGeneratorService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
        return this.catalogGeneratorService.getBasket();
    }
}

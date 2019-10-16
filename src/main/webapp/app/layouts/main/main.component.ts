import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRouteSnapshot, NavigationEnd, NavigationStart, NavigationCancel, NavigationError } from '@angular/router';

import { Title } from '@angular/platform-browser';
import { RoutingState } from 'app/routing-stage';

@Component({
    selector: 'jhi-main',
    templateUrl: './main.component.html'
})
export class JhiMainComponent implements OnInit {
    constructor(private titleService: Title, private router: Router, routingState: RoutingState) {
        routingState.loadRouting();
    }

    private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
        let title: string = routeSnapshot.data && routeSnapshot.data['pageTitle'] ? routeSnapshot.data['pageTitle'] : 'Generator katalogów';
        if (routeSnapshot.firstChild) {
            title = this.getPageTitle(routeSnapshot.firstChild) || title;
        }
        return title;
    }

    ngOnInit() {
        this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                this.titleService.setTitle(this.getPageTitle(this.router.routerState.snapshot.root));
            }
        });

        // if(this.router.url === '/'){
        //      this.router.navigateByUrl('home')
        //  }

        //this.router.navigateByUrl('home')
    }
}

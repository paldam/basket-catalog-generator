import { Component, ElementRef, OnInit, Renderer } from '@angular/core';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LoginModalService, Principal, Account, LoginService, StateStorageService } from 'app/core';
import { Router, RouterState } from '@angular/router';
import { RoutingState } from 'app/routing-stage';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    authenticationError: boolean;
    password: string;
    rememberMe: boolean;
    username: string;
    credentials: any;

    constructor(
        private principal: Principal,
        private routingState: RoutingState,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private loginService: LoginService,
        private stateStorageService: StateStorageService,
        private elementRef: ElementRef,
        private renderer: Renderer,
        private router: Router
    ) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
        setTimeout(() => {
            console.log('efwefewwe');
            console.log(this.routingState.getHistory());
        }, 1000);
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', message => {
            this.principal.identity().then(account => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.loginService
            .login({
                username: this.username,
                password: this.password,
                rememberMe: this.rememberMe
            })
            .then(() => {
                this.authenticationError = false;
                if (this.router.url === '/register' || /^\/activate\//.test(this.router.url) || /^\/reset\//.test(this.router.url)) {
                    this.router.navigateByUrl('catalog-archive');
                }

                if (this.router.url === '/home' || this.router.url === '/') {
                    setTimeout(() => {
                        this.router.navigateByUrl('/generator');
                    }, 400);
                }

                this.eventManager.broadcast({
                    name: 'authenticationSuccess',
                    content: 'Sending Authentication Success'
                });

                // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                // since login is succesful, go to stored previousState and clear previousState
                const redirect = this.stateStorageService.getUrl();
                if (redirect) {
                    this.stateStorageService.storeUrl(null);
                    this.router.navigate([redirect]);
                }
            })
            .catch(() => {
                this.authenticationError = true;
            });
    }

    register() {
        this.router.navigate(['/register']);
    }
}

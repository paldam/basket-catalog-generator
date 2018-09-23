import { Route } from '@angular/router';

import { RegisterComponent } from './register.component';
import { UserRouteAccessService } from 'app/core';

export const registerRoute: Route = {
    path: 'register',
    component: RegisterComponent,
    data: {
        authorities: ['ROLE_SUPERADMIN'],
        pageTitle: 'Registration'
    },
    canActivate: [UserRouteAccessService],
};

import { NgModule } from '@angular/core';

import { BasketExtSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [BasketExtSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [BasketExtSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class BasketExtSharedCommonModule {}

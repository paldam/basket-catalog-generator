import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BasketExtSharedModule } from '../shared/';
import { ContextMenuModule, DataTableModule, FieldsetModule, FileUploadModule, OverlayPanelModule } from 'primeng/primeng';
import { TabViewModule } from 'primeng/tabview';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TableModule } from 'primeng/table';
import { StepsModule } from 'primeng/steps';
import { HttpModule } from '@angular/http';
import { PanelModule } from 'primeng/panel';
import { MenuItem } from 'primeng/api';
import { CardModule } from 'primeng/card';
import { GENERATED_CATALOG_ROUTE } from './generated-catalog.route';
import { CatalogListComponent } from './catalog-list/catalog-list.component';

@NgModule({
    imports: [
        BasketExtSharedModule,
        RouterModule.forChild(GENERATED_CATALOG_ROUTE),
        HttpModule,
        TabViewModule,
        PanelModule,
        FileUploadModule,
        OverlayPanelModule,
        FieldsetModule,
        DataTableModule,
        StepsModule,
        BrowserModule,
        BrowserAnimationsModule,
        TableModule,
        ContextMenuModule,
        CardModule
    ],
    declarations: [CatalogListComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    providers: []
})
export class GeneratedCatalogModule {}

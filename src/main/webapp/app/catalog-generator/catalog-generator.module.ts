import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BasketExtSharedModule } from '../shared/';
import { BasketPickerComponent } from './baskets-picker/basket-picker.component';
import { BASKET_ROUTE } from './catalog-generator-.route';
import { CatalogGeneratorService } from './catalog-generator.service';
import { DataTableModule, FieldsetModule, FileUploadModule, OverlayPanelModule } from 'primeng/primeng';
import { TabViewModule } from 'primeng/tabview';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SelectedBasketsComponent } from './selected-baskets/selected-baskets.component';
import { BasketCustomizeComponent } from './basket-customize/basket-customize.component';
import { TableModule } from 'primeng/table';
import { CatalogGeneratorCoreComponent } from './catalog-generator-core/catalog-generator-core.component';
import { StepsModule } from 'primeng/steps';
import { CatalogGeneratorDetailsComponent } from './catalog-generator-details/catalog-generator-details.component';
import { HttpModule } from '@angular/http';
import { PanelModule } from 'primeng/panel';

@NgModule({
    imports: [
        BasketExtSharedModule,
        RouterModule.forChild(BASKET_ROUTE),
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
        TableModule
    ],
    declarations: [
        BasketPickerComponent,
        SelectedBasketsComponent,
        BasketCustomizeComponent,
        CatalogGeneratorCoreComponent,
        CatalogGeneratorDetailsComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    providers: [CatalogGeneratorService]
})
export class CatalogGeneratorModule {}

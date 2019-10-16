import { RouterModule, Routes } from '@angular/router';
import { HomeComponent, RegisterComponent2 } from 'app/home';
import { NgModule } from '@angular/core';
import { CatalogGeneratorCoreComponent } from 'app/catalog-generator/catalog-generator-core/catalog-generator-core.component';

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'home', component: HomeComponent },
    { path: 'register', component: RegisterComponent2 },
    { path: 'generator', component: CatalogGeneratorCoreComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SampleRoutingModule {}

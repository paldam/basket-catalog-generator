import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ViewEncapsulation } from '@angular/core';
import { SelectedBasketService } from '../selected-baskets/selected-basket.service';
import { NgForm } from '@angular/forms';
import { CatalogGeneratorService } from '../catalog-generator.service';
import { CatalogArchive, Theme } from '../../shared/model/catalog-archive.model';
import { Basket } from '../../shared/model/basket.model';
import { Product } from '../../shared/model/product.model';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { Router } from '@angular/router';
import { saveAs } from 'file-saver/FileSaver';

@Component({
    selector: 'jhi-catalog-generator-details',
    templateUrl: './catalog-generator-details.component.html',
    styleUrls: ['./catalog-generator-details.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class CatalogGeneratorDetailsComponent implements OnInit {
    public catalogArchive: CatalogArchive = new CatalogArchive();
    public baskets: Basket[] = [];
    public formSubmitted: boolean = false;
    public loading: string = '...';
    public showPdfGenStatusModal: boolean = false;
    public showThemePickerVisible: boolean = false;
    fileFilterLoaded: Promise<boolean>;
    public themesImages: any[] = [];
    public themee: string;

    //public generatedUrl: any;
    public generatedPdf: any;
    percentDone: number;
    uploadSuccess: boolean;
    size: any;
    width: number;
    height: number;
    fileType: string;

    @ViewChild('coverFilesInput') imgType: ElementRef;
    @ViewChild('ww') ww: ElementRef;

    constructor(
        private selectedBasketService: SelectedBasketService,
        private catalogGeneratorService: CatalogGeneratorService,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private router: Router
    ) {}

    ngOnInit() {
        // this.catalogArchive.catalogAdditionalDesc = 'Oferta przygotwana dla firmy EnterStroe';
        // this.catalogArchive.catalogName = 'Kosze świąteczne 2018';
        // //this.catalogArchive.catalogTheme = Theme.NIEBIESKI;
        // this.catalogArchive.customerAssistantEmail = 'dami@onet.eu';
        // this.catalogArchive.customerAssistantName = 'Kamil Nowak';
        //
        // this.catalogArchive.customerAssistantTel = '508703333';
        // this.catalogArchive.forWho = 'EnterStore Damian Paluch';

        this.themesImages.push({ name: 'Motyw_1' });
        this.themesImages.push({ name: 'Motyw_2' });
        this.themesImages.push({ name: 'Motyw_3' });
        this.themesImages.push({ name: 'Motyw_4' });
        this.themesImages.push({ name: 'Motyw_5' });
        this.themesImages.push({ name: 'Motyw_6' });
        this.themesImages.push({ name: 'Motyw_7' });
        this.themesImages.push({ name: 'Motyw_8' });
        this.themesImages.push({ name: 'Motyw_9' });
        this.themesImages.push({ name: 'Motyw_10' });
    }

    submitForm(form: NgForm) {
        this.formSubmitted = true;

        if (form.valid && this.chceckImageValid()) {
            this.selectedBasketService.selectedBasket.forEach(basket => {
                let productToAdd: Product[] = [];

                basket.basketItems.forEach(basketI => {
                    productToAdd.push(new Product(basketI.product.productName, basketI.product.capacity));
                });

                this.baskets.push(new Basket(basket.basketName, basket.ownPrice * 100, basket.basketId, productToAdd));
                productToAdd = [];
            });

            console.log('ss' + JSON.stringify(this.catalogArchive));

            this.catalogArchive.baskets = this.baskets;

            this.genCatalog(this.catalogArchive);
            this.selectedBasketService.selectedBasket = [];

            // setTimeout(() => {
            //     this.router.navigateByUrl('/catalog-archive');
            // }, 1000);
        }

        this.formSubmitted = false;
    }

    genCatalog(catalog: CatalogArchive) {
        this.showPdfGenStatusModal = true;
        this.catalogGeneratorService.sendCataloqDataToGeneratePdF(catalog).subscribe(res => {
            setTimeout(() => {
                this.fileFilterLoaded = Promise.resolve(true);
            }, 1800);

            //this.generatedUrl = URL.createObjectURL(res);
            this.generatedPdf = res;
        });
    }

    openPdfInBrowser() {
        //window.open(this.generatedUrl);
        let d = new Date();
        let pdfName = 'katalog_' + d.toLocaleDateString() + ' ' + d.toLocaleTimeString() + '.pdf';

        saveAs(this.generatedPdf, pdfName);
        this.showPdfGenStatusModal = false;
        this.generatedPdf = [];

        setTimeout(() => {
            this.router.navigateByUrl('/catalog-archive');
        }, 1000);
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    onChange(evt: any) {
        this.percentDone = 100;
        this.uploadSuccess = true;
        let image: any = evt.target.files[0];
        this.size = image.size / 1024 / 1000;
        this.fileType = image.type;
        let fr = new FileReader();
        fr.onload = () => {
            // when file has loaded
            var img = new Image();

            img.onload = () => {
                this.width = img.width;
                this.height = img.height;
            };
            console.log('xxxx' + this.width + this.height);
            img.src = fr.result; // This is the data URL
        };

        fr.readAsDataURL(image);

        this.imgType.nativeElement.value = '';
    }

    chceckImageValid(): boolean {
        console.log('Typ danych to ' + this.fileType);
        if (this.fileType != 'image/jpeg') {
            return false;
        }
        if (this.width != 400) {
            return false;
        }
        if (this.height != 300) {
            return false;
        }
        if (this.size > 11) {
            return false;
        } else {
            return true;
        }
    }

    showThemePicker() {
        this.showThemePickerVisible = true;
    }
}

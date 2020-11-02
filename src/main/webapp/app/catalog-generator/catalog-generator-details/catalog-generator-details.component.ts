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
    public image: any;
    themesPrevImg: any[];
    //public generatedUrl: any;
    public generatedPdf: any;
    percentDone: number;
    uploadSuccess: boolean;
    size: any;
    width: number;
    height: number;
    fileType: string;

    public themeIndex: number = 0;

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

        this.themesImages.push({ name: 'Motyw_1', img: 'http://www.kosze.waw.pl/images/basketext/theme-prev/1.jpg' });
        this.themesImages.push({ name: 'Motyw_2', img: 'http://www.kosze.waw.pl/images/basketext/theme-prev/2.jpg' });
        this.themesImages.push({ name: 'Motyw_3', img: 'http://www.kosze.waw.pl/images/basketext/theme-prev/3.jpg' });
        this.themesImages.push({ name: 'Motyw_4', img: 'http://www.kosze.waw.pl/images/basketext/theme-prev/4.jpg' });
        this.themesImages.push({ name: 'Motyw_5', img: 'http://www.kosze.waw.pl/images/basketext/theme-prev/5.jpg' });
        this.themesImages.push({ name: 'Motyw_6', img: 'http://www.kosze.waw.pl/images/basketext/theme-prev/6.jpg' });
        this.themesImages.push({ name: 'Motyw_7', img: 'http://www.kosze.waw.pl/images/basketext/theme-prev/7.jpg' });
        this.themesImages.push({ name: 'Motyw_8', img: 'http://www.kosze.waw.pl/images/basketext/theme-prev/8.jpg' });
        this.themesImages.push({ name: 'Motyw_9', img: 'http://www.kosze.waw.pl/images/basketext/theme-prev/9.jpg' });
        this.themesImages.push({ name: 'Motyw_10', img: 'http://www.kosze.waw.pl/images/basketext/theme-prev/10.jpg' });
    }

    submitForm(form: NgForm) {
        this.formSubmitted = true;

        if (form.valid && this.chceckImageValid()) {
            this.selectedBasketService.selectedBasket.forEach(basket => {
                let productToAdd: Product[] = [];
                basket.basketItems.forEach(basketI => {
                    if (basketI.product.productSubType.productType.activeForPdfExport) {
                        productToAdd.push(new Product(basketI.product.productCatalogName, basketI.product.capacity));
                    } else {
                    }
                });

                this.baskets.push(new Basket(basket.basketName, basket.ownPrice * 100, basket.basketId, productToAdd));
                productToAdd = [];
            });

            this.catalogArchive.catalogTheme = this.themesImages[this.themeIndex].name;

            this.catalogArchive.baskets = this.baskets;

            this.genCatalog(this.catalogArchive);
            this.selectedBasketService.selectedBasket = [];
        }
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
        console.log('111 ');
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    onChange(evt: any) {
        console.log('222222 ');
        this.percentDone = 100;
        this.uploadSuccess = true;
        let image: any = evt.target.files[0];
        this.image = evt.target.files[0];
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
    }

    chceckImageValid(): boolean {
        if (this.image == null) {
            return true;
        }

        if (this.fileType != 'image/jpeg' && this.fileType != 'image/png') {
            return false;
        }

        if (this.size > 2) {
            return false;
        } else {
            return true;
        }
    }

    showThemePicker() {
        this.showThemePickerVisible = true;
    }

    closeThemePicker() {
        this.showThemePickerVisible = false;
    }

    nextTheme() {
        this.themeIndex++;

        if (this.themesImages.length == this.themeIndex) {
            this.themeIndex = 0;
        }
    }

    prevTheme() {
        if (this.themeIndex == 0) {
            this.themeIndex = this.themesImages.length - 1;
        } else {
            this.themeIndex--;
        }
    }
}

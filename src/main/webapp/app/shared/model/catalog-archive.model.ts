import { IBasket } from 'app/shared/model//basket.model';

export const enum Theme {
    NIEBIESKI = 'NIEBIESKI',
    CZERWONY = 'CZERWONY'
}

export interface ICatalogArchive {
    id?: number;
    catalogName?: string;
    forWho?: string;
    customerAssistantName?: string;
    customerAssistantEmail?: string;
    customerAssistantTel?: string;
    catalogAdditionalDesc?: string;
    catalogTheme?: Theme;
    logoContentType?: string;
    logo?: any;
    baskets?: IBasket[];
}

export class CatalogArchive implements ICatalogArchive {
    constructor(
        public id?: number,
        public catalogName?: string,
        public forWho?: string,
        public customerAssistantName?: string,
        public customerAssistantEmail?: string,
        public customerAssistantTel?: string,
        public catalogAdditionalDesc?: string,
        public catalogTheme?: Theme,
        public logoContentType?: string,
        public logo?: any,
        public baskets?: IBasket[]
    ) {}
}

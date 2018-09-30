export class CatalogDetails{

    constructor(
        public catalogName?: string,
        public catalogFor?: string,
        public customerAssistantName?: string,
        public customerAssistantEmail?: string,
        public customerAssistantPhone?: string,
        public catalogLastPageDesc?: string,
        public catalogTheme?: string
    ) {
    }
}

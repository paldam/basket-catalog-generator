package com.damian.domain;

import java.util.ArrayList;

/**
 * Created by Damian on 27.09.2018.
 */
public class Catalog {

    private ArrayList<Basket> baskets;
    private CatalogDetails catalogDetails;

    public Catalog() {
    }

    public Catalog(ArrayList<Basket> baskets, CatalogDetails catalogDetails) {
        this.baskets = baskets;
        this.catalogDetails = catalogDetails;
    }

    public ArrayList<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(ArrayList<Basket> baskets) {
        this.baskets = baskets;
    }

    public CatalogDetails getCatalogDetails() {
        return catalogDetails;
    }

    public void setCatalogDetails(CatalogDetails catalogDetails) {
        this.catalogDetails = catalogDetails;
    }
}

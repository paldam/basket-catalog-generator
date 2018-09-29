package com.damian.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Damian on 27.09.2018.
 */
public class Basket {

    private String BasketName;
    private BigDecimal BasketPrice;
    private ArrayList<BasketItems> BasketItems;

    public Basket() {
    }

    public Basket(String basketName, BigDecimal basketPrice, ArrayList<com.damian.domain.BasketItems> basketItems) {
        BasketName = basketName;
        BasketPrice = basketPrice;
        BasketItems = basketItems;
    }

    public String getBasketName() {
        return BasketName;
    }

    public void setBasketName(String basketName) {
        BasketName = basketName;
    }

    public BigDecimal getBasketPrice() {
        return BasketPrice;
    }

    public void setBasketPrice(BigDecimal basketPrice) {
        BasketPrice = basketPrice;
    }

    public ArrayList<com.damian.domain.BasketItems> getBasketItems() {
        return BasketItems;
    }

    public void setBasketItems(ArrayList<com.damian.domain.BasketItems> basketItems) {
        BasketItems = basketItems;
    }

    @Override
    public String toString() {
        return "Basket{" +
            "BasketName='" + BasketName + '\'' +
            ", BasketPrice=" + BasketPrice +
            ", BasketItems=" + BasketItems +
            '}';
    }
}

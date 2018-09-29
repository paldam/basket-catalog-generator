package com.damian.domain;

/**
 * Created by Damian on 27.09.2018.
 */
public class BasketItems {
    private String productName;
    private String productCapacity;
    private Integer quantity;

    public BasketItems() {
    }

    public BasketItems(String productName, String productCapacity, Integer quantity) {
        this.productName = productName;
        this.productCapacity = productCapacity;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(String productCapacity) {
        this.productCapacity = productCapacity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

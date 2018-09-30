package com.damian.domain;

import javax.persistence.*;

/**
 * Created by Damian on 27.09.2018.
 */
public class BasketItems {
    private Integer basketItemsId;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_items_id", nullable = false)
    public Integer getBasketItemsId() {
        return basketItemsId;
    }

    public void setBasketItemsId(Integer basketItemsId) {
        this.basketItemsId = basketItemsId;
    }
    @Basic
    @Column(name = "product_name", nullable = false)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "capacity", nullable = false)
    public String getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(String productCapacity) {
        this.productCapacity = productCapacity;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

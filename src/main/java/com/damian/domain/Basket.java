package com.damian.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "baskets")
public class Basket {

    private Integer BasketId;
    private String BasketName;
    private BigDecimal BasketPrice;
    //

    public Basket() {
    }

    public Basket(String basketName, BigDecimal basketPrice) {
        BasketName = basketName;
        BasketPrice = basketPrice;
        //BasketItems = basketItems;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id", nullable = false)
    public Integer getBasketId() {
        return BasketId;
    }

    public void setBasketId(Integer basketId) {
        BasketId = basketId;
    }

    @Basic
    @Column(name = "basket_name", nullable = false, length = 300)
    public String getBasketName() {
        return BasketName;
    }

    public void setBasketName(String basketName) {
        BasketName = basketName;
    }

    @Basic
    @Column(name = "basket_price", length = 40)
    public BigDecimal getBasketPrice() {
        return BasketPrice;
    }

    public void setBasketPrice(BigDecimal basketPrice) {
        BasketPrice = basketPrice;
    }


//    @OneToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
//    @JoinColumn(name="basket_id", referencedColumnName="basket_id")
//    public List<BasketItems> getBasketItems() {
//        return BasketItems;
//    }
//
//    public void setBasketItems(List<BasketItems> basketItems) {
//        BasketItems = basketItems;
//    }

    @Override
    public String toString() {
        return "Basket{" +
            "BasketName='" + BasketName + '\'' +
            ", BasketPrice=" + BasketPrice +
            ", BasketItems="  +
            '}';
    }
}

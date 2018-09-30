package com.damian.domain;

import javax.persistence.*;

@Entity
@Table(name = "catalog_baskets")
public class CatalogBaskets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_baskets_id", nullable = false)
    private Integer catalogBasketsId;


    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;



    public Integer getCatalogBasketsId() {
        return catalogBasketsId;
    }

    public void setCatalogBasketsId(Integer catalogBasketsId) {
        this.catalogBasketsId = catalogBasketsId;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}

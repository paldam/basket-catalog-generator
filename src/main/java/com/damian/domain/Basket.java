package com.damian.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "basket")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Basket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "basket_name", nullable = false)
    private String basketName;

    @NotNull
    @Column(name = "basket_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal basketTotalPrice;

    @NotNull
    @Column(name = "orgin_basket_id", nullable = false)
    private Integer orginBasketId;

    @ManyToMany (cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "basket_products",
               joinColumns = @JoinColumn(name = "baskets_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "products_id", referencedColumnName = "id"))
    private Set<Product> products = new HashSet<>();

    @ManyToMany(mappedBy = "baskets")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CatalogArchive> catalogArchives = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBasketName() {
        return basketName;
    }

    public Basket basketName(String basketName) {
        this.basketName = basketName;
        return this;
    }

    public void setBasketName(String basketName) {
        this.basketName = basketName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getBasketTotalPrice() {
        return basketTotalPrice;
    }

    public void setBasketTotalPrice(BigDecimal basketTotalPrice) {
        this.basketTotalPrice = basketTotalPrice;
    }


    public Integer getOrginBasketId() {
        return orginBasketId;
    }

    public Basket orginBasketId(Integer orginBasketId) {
        this.orginBasketId = orginBasketId;
        return this;
    }

    public void setOrginBasketId(Integer orginBasketId) {
        this.orginBasketId = orginBasketId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Basket products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public Basket addProducts(Product product) {
        this.products.add(product);
        product.getBaskets().add(this);
        return this;
    }

    public Basket removeProducts(Product product) {
        this.products.remove(product);
        product.getBaskets().remove(this);
        return this;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<CatalogArchive> getCatalogArchives() {
        return catalogArchives;
    }

    public Basket catalogArchives(Set<CatalogArchive> catalogArchives) {
        this.catalogArchives = catalogArchives;
        return this;
    }

    public Basket addCatalogArchive(CatalogArchive catalogArchive) {
        this.catalogArchives.add(catalogArchive);
        catalogArchive.getBaskets().add(this);
        return this;
    }

    public Basket removeCatalogArchive(CatalogArchive catalogArchive) {
        this.catalogArchives.remove(catalogArchive);
        catalogArchive.getBaskets().remove(this);
        return this;
    }

    public void setCatalogArchives(Set<CatalogArchive> catalogArchives) {
        this.catalogArchives = catalogArchives;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Basket basket = (Basket) o;
        if (basket.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), basket.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Basket{" +
            "id=" + id +
            ", basketName='" + basketName + '\'' +
            ", basketTotalPrice=" + basketTotalPrice +
            ", orginBasketId=" + orginBasketId +
            ", products=" + products +
            ", catalogArchives=" + catalogArchives +
            '}';
    }
}

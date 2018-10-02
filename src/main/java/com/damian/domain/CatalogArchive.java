package com.damian.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.damian.domain.enumeration.Theme;

/**
 * A CatalogArchive.
 */
@Entity
@Table(name = "catalog_archive")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CatalogArchive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "catalog_name", nullable = false)
    private String catalogName;

    @Column(name = "for_who")
    private String forWho;

    @Column(name = "customer_assistant_name")
    private String customerAssistantName;

    @Column(name = "customer_assistant_email")
    private String customerAssistantEmail;

    @Column(name = "customer_assistant_tel")
    private String customerAssistantTel;

    @Column(name = "catalog_additional_desc")
    private String catalogAdditionalDesc;

    @Enumerated(EnumType.STRING)
    @Column(name = "catalog_theme")
    private Theme catalogTheme;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "logo_content_type")
    private String logoContentType;

    @ManyToMany(cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "catalog_archive_baskets",
               joinColumns = @JoinColumn(name = "catalog_archives_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "baskets_id", referencedColumnName = "id"))
    private Set<Basket> baskets = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public CatalogArchive catalogName(String catalogName) {
        this.catalogName = catalogName;
        return this;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getForWho() {
        return forWho;
    }

    public CatalogArchive forWho(String forWho) {
        this.forWho = forWho;
        return this;
    }

    public void setForWho(String forWho) {
        this.forWho = forWho;
    }

    public String getCustomerAssistantName() {
        return customerAssistantName;
    }

    public CatalogArchive customerAssistantName(String customerAssistantName) {
        this.customerAssistantName = customerAssistantName;
        return this;
    }

    public void setCustomerAssistantName(String customerAssistantName) {
        this.customerAssistantName = customerAssistantName;
    }

    public String getCustomerAssistantEmail() {
        return customerAssistantEmail;
    }

    public CatalogArchive customerAssistantEmail(String customerAssistantEmail) {
        this.customerAssistantEmail = customerAssistantEmail;
        return this;
    }

    public void setCustomerAssistantEmail(String customerAssistantEmail) {
        this.customerAssistantEmail = customerAssistantEmail;
    }

    public String getCustomerAssistantTel() {
        return customerAssistantTel;
    }

    public CatalogArchive customerAssistantTel(String customerAssistantTel) {
        this.customerAssistantTel = customerAssistantTel;
        return this;
    }

    public void setCustomerAssistantTel(String customerAssistantTel) {
        this.customerAssistantTel = customerAssistantTel;
    }

    public String getCatalogAdditionalDesc() {
        return catalogAdditionalDesc;
    }

    public CatalogArchive catalogAdditionalDesc(String catalogAdditionalDesc) {
        this.catalogAdditionalDesc = catalogAdditionalDesc;
        return this;
    }

    public void setCatalogAdditionalDesc(String catalogAdditionalDesc) {
        this.catalogAdditionalDesc = catalogAdditionalDesc;
    }

    public Theme getCatalogTheme() {
        return catalogTheme;
    }

    public CatalogArchive catalogTheme(Theme catalogTheme) {
        this.catalogTheme = catalogTheme;
        return this;
    }

    public void setCatalogTheme(Theme catalogTheme) {
        this.catalogTheme = catalogTheme;
    }

    public byte[] getLogo() {
        return logo;
    }

    public CatalogArchive logo(byte[] logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public CatalogArchive logoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
        return this;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public Set<Basket> getBaskets() {
        return baskets;
    }

    public CatalogArchive baskets(Set<Basket> baskets) {
        this.baskets = baskets;
        return this;
    }

    public CatalogArchive addBaskets(Basket basket) {
        this.baskets.add(basket);
        basket.getCatalogArchives().add(this);
        return this;
    }

    public CatalogArchive removeBaskets(Basket basket) {
        this.baskets.remove(basket);
        basket.getCatalogArchives().remove(this);
        return this;
    }

    public void setBaskets(Set<Basket> baskets) {
        this.baskets = baskets;
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
        CatalogArchive catalogArchive = (CatalogArchive) o;
        if (catalogArchive.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), catalogArchive.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CatalogArchive{" +
            "id=" + getId() +
            ", catalogName='" + getCatalogName() + "'" +
            ", forWho='" + getForWho() + "'" +
            ", customerAssistantName='" + getCustomerAssistantName() + "'" +
            ", customerAssistantEmail='" + getCustomerAssistantEmail() + "'" +
            ", customerAssistantTel='" + getCustomerAssistantTel() + "'" +
            ", catalogAdditionalDesc='" + getCatalogAdditionalDesc() + "'" +
            ", catalogTheme='" + getCatalogTheme() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logoContentType='" + getLogoContentType() + "'" +
            "}";
    }
}

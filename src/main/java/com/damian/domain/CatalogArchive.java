package com.damian.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.*;

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
    @Basic

    @Column(name = "date_of_generate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateOfGenerate;

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

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "catalog_archive_baskets",
        joinColumns = @JoinColumn(name = "catalog_archives_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "baskets_id", referencedColumnName = "id"))
    private List<Basket> baskets = new ArrayList<>();

    @ManyToOne()
    private User user;

    public CatalogArchive() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfGenerate() {
        return dateOfGenerate;
    }

    public void setDateOfGenerate(Date dateOfGenerate) {
        this.dateOfGenerate = dateOfGenerate;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getForWho() {
        return forWho;
    }

    public void setForWho(String forWho) {
        this.forWho = forWho;
    }

    public String getCustomerAssistantName() {
        return customerAssistantName;
    }

    public void setCustomerAssistantName(String customerAssistantName) {
        this.customerAssistantName = customerAssistantName;
    }

    public String getCustomerAssistantEmail() {
        return customerAssistantEmail;
    }

    public void setCustomerAssistantEmail(String customerAssistantEmail) {
        this.customerAssistantEmail = customerAssistantEmail;
    }

    public String getCustomerAssistantTel() {
        return customerAssistantTel;
    }

    public void setCustomerAssistantTel(String customerAssistantTel) {
        this.customerAssistantTel = customerAssistantTel;
    }

    public String getCatalogAdditionalDesc() {
        return catalogAdditionalDesc;
    }

    public void setCatalogAdditionalDesc(String catalogAdditionalDesc) {
        this.catalogAdditionalDesc = catalogAdditionalDesc;
    }

    public Theme getCatalogTheme() {
        return catalogTheme;
    }

    public void setCatalogTheme(Theme catalogTheme) {
        this.catalogTheme = catalogTheme;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public List<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<Basket> baskets) {
        this.baskets = baskets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CatalogArchive that = (CatalogArchive) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getDateOfGenerate() != null ? !getDateOfGenerate().equals(that.getDateOfGenerate()) : that.getDateOfGenerate() != null)
            return false;
        if (getCatalogName() != null ? !getCatalogName().equals(that.getCatalogName()) : that.getCatalogName() != null)
            return false;
        if (getForWho() != null ? !getForWho().equals(that.getForWho()) : that.getForWho() != null) return false;
        if (getCustomerAssistantName() != null ? !getCustomerAssistantName().equals(that.getCustomerAssistantName()) : that.getCustomerAssistantName() != null)
            return false;
        if (getCustomerAssistantEmail() != null ? !getCustomerAssistantEmail().equals(that.getCustomerAssistantEmail()) : that.getCustomerAssistantEmail() != null)
            return false;
        if (getCustomerAssistantTel() != null ? !getCustomerAssistantTel().equals(that.getCustomerAssistantTel()) : that.getCustomerAssistantTel() != null)
            return false;
        if (getCatalogAdditionalDesc() != null ? !getCatalogAdditionalDesc().equals(that.getCatalogAdditionalDesc()) : that.getCatalogAdditionalDesc() != null)
            return false;
        if (getCatalogTheme() != that.getCatalogTheme()) return false;
        if (!Arrays.equals(getLogo(), that.getLogo())) return false;
        if (getLogoContentType() != null ? !getLogoContentType().equals(that.getLogoContentType()) : that.getLogoContentType() != null)
            return false;
        if (getBaskets() != null ? !getBaskets().equals(that.getBaskets()) : that.getBaskets() != null) return false;
        return getUser() != null ? getUser().equals(that.getUser()) : that.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDateOfGenerate() != null ? getDateOfGenerate().hashCode() : 0);
        result = 31 * result + (getCatalogName() != null ? getCatalogName().hashCode() : 0);
        result = 31 * result + (getForWho() != null ? getForWho().hashCode() : 0);
        result = 31 * result + (getCustomerAssistantName() != null ? getCustomerAssistantName().hashCode() : 0);
        result = 31 * result + (getCustomerAssistantEmail() != null ? getCustomerAssistantEmail().hashCode() : 0);
        result = 31 * result + (getCustomerAssistantTel() != null ? getCustomerAssistantTel().hashCode() : 0);
        result = 31 * result + (getCatalogAdditionalDesc() != null ? getCatalogAdditionalDesc().hashCode() : 0);
        result = 31 * result + (getCatalogTheme() != null ? getCatalogTheme().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getLogo());
        result = 31 * result + (getLogoContentType() != null ? getLogoContentType().hashCode() : 0);
        result = 31 * result + (getBaskets() != null ? getBaskets().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CatalogArchive{" +
            "id=" + id +
            ", dateOfGenerate=" + dateOfGenerate +
            ", catalogName='" + catalogName + '\'' +
            ", forWho='" + forWho + '\'' +
            ", customerAssistantName='" + customerAssistantName + '\'' +
            ", customerAssistantEmail='" + customerAssistantEmail + '\'' +
            ", customerAssistantTel='" + customerAssistantTel + '\'' +
            ", catalogAdditionalDesc='" + catalogAdditionalDesc + '\'' +
            ", catalogTheme=" + catalogTheme +
            ", logo=" + Arrays.toString(logo) +
            ", logoContentType='" + logoContentType + '\'' +
            ", baskets=" + baskets +
            ", user=" + user +
            '}';
    }
}

package com.damian.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "catalogs_archive")
public class CatalogArchive {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_archive_id")
    private Long catalogArchiveId;

    @Basic
    @Column(name = "catalog_name", length = 300)
    private String catalogName;

    @Basic
    @Column(name = "for_who", length = 100)
    private String forWho;

    @Basic
    @Column(name = "customer_assistant_name", length = 100)
    private String customerAssistantName;

    @Basic
    @Column(name = "customer_assistant_email", length = 100)
    private String customerAssistantEmail;

    @Basic
    @Column(name = "customer_assistant_tel", length = 100)
    private String customerAssistantTel;

    @Basic
    @Column(name = "catalog_additional_desc", length = 100)
    private String catalogAdditionalDesc;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "catalog_archive_id", referencedColumnName = "catalog_archive_id")
    public List<CatalogBaskets> catalogBaskets;

    @JsonIgnore
    @Basic
    @Column(name = "logo", columnDefinition = "LONGBLOB")
    private byte[] data;


    public Long getCatalogArchiveId() {
        return catalogArchiveId;
    }

    public void setCatalogArchiveId(Long catalogArchiveId) {
        this.catalogArchiveId = catalogArchiveId;
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

    public List<CatalogBaskets> getCatalogBaskets() {
        return catalogBaskets;
    }

    public void setCatalogBaskets(List<CatalogBaskets> catalogBaskets) {
        this.catalogBaskets = catalogBaskets;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

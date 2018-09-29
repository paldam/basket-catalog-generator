package com.damian.domain;


public class CatalogDetails {

    private String catalogName;
    private String catalogFor;
    private String customerAssistantName;
    private String customerAssistantEmail;
    private String customerAssistantPhone;
    private String catalogLastPageDesc;

    public CatalogDetails() {
    }

    public CatalogDetails(String catalogName, String catalogFor, String customerAssistantName, String customerAssistantEmail, String customerAssistantPhone, String catalogLastPageDesc) {
        this.catalogName = catalogName;
        this.catalogFor = catalogFor;
        this.customerAssistantName = customerAssistantName;
        this.customerAssistantEmail = customerAssistantEmail;
        this.customerAssistantPhone = customerAssistantPhone;
        this.catalogLastPageDesc = catalogLastPageDesc;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogFor() {
        return catalogFor;
    }

    public void setCatalogFor(String catalogFor) {
        this.catalogFor = catalogFor;
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

    public String getCustomerAssistantPhone() {
        return customerAssistantPhone;
    }

    public void setCustomerAssistantPhone(String customerAssistantPhone) {
        this.customerAssistantPhone = customerAssistantPhone;
    }

    public String getCatalogLastPageDesc() {
        return catalogLastPageDesc;
    }

    public void setCatalogLastPageDesc(String catalogLastPageDesc) {
        this.catalogLastPageDesc = catalogLastPageDesc;
    }
}

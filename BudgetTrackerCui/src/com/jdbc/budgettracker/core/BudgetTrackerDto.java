package com.jdbc.budgettracker.core;

import java.util.Date;

public class BudgetTrackerDto {
    private int id;
    private Date date;
    private String storeName;
    private String productName;
    private String productType;
    private int price;

    public BudgetTrackerDto() {
    }

    public BudgetTrackerDto(int id, Date date, String storeName, String productName, String productType, int price) {
        super();
        this.id = id;
        this.date = date;
        this.storeName = storeName;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date insertDate) {
        this.date = insertDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
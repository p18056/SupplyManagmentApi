package com.Projects.ProducrManagmentAPI.models;

import java.sql.Date;

import jakarta.persistence.Embeddable;

@Embeddable 
public class InventoryItemId {
    
    private int productId;
    private Date expireDate;

    public InventoryItemId(int productId, Date expireDate) {
        this.productId = productId;
        this.expireDate = expireDate;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public Date getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}

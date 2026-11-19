package com.Projects.ProducrManagmentAPI.DtoObjects;

import java.sql.Date;

public class QuantityPerExpireDate {

    public QuantityPerExpireDate(double quantity, Date expirDate)
    {
        this.quantity = quantity;
        this.expireDate = expirDate;
    }

    private double quantity;
    private Date expireDate;

    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public Date getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    
}

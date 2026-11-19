package com.Projects.ProducrManagmentAPI.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "orders")
public class Order {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    @OneToOne 
    @JoinColumn(name = "recipie_id")
    private Recipie recipie;
    private Date date;


    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public Recipie getRecipie() {
        return recipie;
    }
    public void setRecipie(Recipie recipie) {
        this.recipie = recipie;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
}

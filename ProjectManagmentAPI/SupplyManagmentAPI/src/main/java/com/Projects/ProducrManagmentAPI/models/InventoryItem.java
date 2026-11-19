package com.Projects.ProducrManagmentAPI.models;

import java.sql.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity 
@Table(name = "inventory")
public class InventoryItem {

    @EmbeddedId
    private InventoryItemId inventoryItemId ;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    private double quantity;

        public InventoryItemId getInventoryId() {
        return inventoryItemId;
    }
    public void setInventoryItemId(InventoryItemId inventoryId) {
        this.inventoryItemId = inventoryId;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
}

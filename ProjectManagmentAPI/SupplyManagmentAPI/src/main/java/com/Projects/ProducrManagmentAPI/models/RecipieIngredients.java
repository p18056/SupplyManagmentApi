package com.Projects.ProducrManagmentAPI.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "recipie_ingredients")
public class RecipieIngredients {

    @EmbeddedId 
    private RecipieIngredientsId recipieIngredientsId;
    @ManyToOne 
    @MapsId("productId")
    @JoinColumn (name = "product_id")
    private Product product;
    @ManyToOne 
    @MapsId("recipieId")
    @JoinColumn(name = "recipie_id")
    private Recipie recipie;
    private double quantity;


    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        if(quantity >0) this.quantity = quantity;
        else this.quantity =0;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Recipie getRecipie() {
        return recipie;
    }
    public void setRecipies(Recipie recipie) {
        this.recipie = recipie;
    }
}

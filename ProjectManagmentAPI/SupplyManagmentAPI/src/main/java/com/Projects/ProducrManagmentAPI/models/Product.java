package com.Projects.ProducrManagmentAPI.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "products")
public class Product {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<InventoryItem> inventoryItems;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<RecipieIngredients> recipieIngredients;

public int getId(){
    return  id;
}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(List<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
  
    public List<RecipieIngredients> getRecipieIngredients() {
        return recipieIngredients;
    }
    public void setRecipieIngredients(List<RecipieIngredients> recipieIngredients) {
        this.recipieIngredients = recipieIngredients;
    }

}

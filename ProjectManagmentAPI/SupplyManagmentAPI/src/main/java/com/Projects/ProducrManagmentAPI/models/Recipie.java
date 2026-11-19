package com.Projects.ProducrManagmentAPI.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table (name = "recipies")
public class Recipie {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String descriprion;
    private double price;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<RecipieIngredients> recipieIngredients;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescriprion() {
        return descriprion;
    }
    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
    
    public List<RecipieIngredients> getRecipieIngredients() {
        return recipieIngredients;
    }
    public void setRecipieIngredients(List<RecipieIngredients> recipieIngredients) {
        this.recipieIngredients = recipieIngredients;
    }
    
}

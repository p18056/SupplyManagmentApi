package com.Projects.ProducrManagmentAPI.models;

import jakarta.persistence.Embeddable;

@Embeddable 
public class RecipieIngredientsId {

    private int recipieId;
    private int productId;

    
    public int getRecipieId() {
        return recipieId;
    }
    public void setRecipieId(int recipieId) {
        this.recipieId = recipieId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
}

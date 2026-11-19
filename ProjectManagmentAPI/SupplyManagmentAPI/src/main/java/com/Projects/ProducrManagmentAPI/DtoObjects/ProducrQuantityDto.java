package com.Projects.ProducrManagmentAPI.DtoObjects;

import java.util.ArrayList;
import java.util.List;

import com.Projects.ProducrManagmentAPI.models.InventoryItem;
import com.Projects.ProducrManagmentAPI.models.Product;

public class ProducrQuantityDto {

    public ProducrQuantityDto(Product product)
    {
        productDto =  new ProductDto(product);
        quantityPerExpireDate = new ArrayList<QuantityPerExpireDate>(); 
        for(InventoryItem item: product.getInventoryItems())
        {
            quantityPerExpireDate.add(new QuantityPerExpireDate(item.getQuantity(), item.getInventoryId().getExpireDate()));
        }

    }

    private ProductDto productDto;
    private List<QuantityPerExpireDate> quantityPerExpireDate;

    public ProductDto getProductDto() {
        return productDto;
    }
    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }
    public List<QuantityPerExpireDate> getQuantityPerExpireDate() {
        return quantityPerExpireDate;
    }
    public void setQuantityPerExpireDate(List<QuantityPerExpireDate> quantityPerExpireDate) {
        this.quantityPerExpireDate = quantityPerExpireDate;
    }
    
}

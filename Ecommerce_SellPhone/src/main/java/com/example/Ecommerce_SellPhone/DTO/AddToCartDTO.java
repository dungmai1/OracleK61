package com.example.Ecommerce_SellPhone.DTO;

import jakarta.validation.constraints.NotNull;

public class AddToCartDTO {
    private Integer id;
    private @NotNull Integer productID;
    private @NotNull Integer quantity;

    public AddToCartDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

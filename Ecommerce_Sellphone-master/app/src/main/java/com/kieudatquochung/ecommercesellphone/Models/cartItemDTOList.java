package com.kieudatquochung.ecommercesellphone.Models;

import java.util.List;

public class cartItemDTOList {
    private int id,quantity;
    private Product product;

    public cartItemDTOList() {
    }

    public cartItemDTOList(int id, int quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

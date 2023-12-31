package com.kieudatquochung.ecommercesellphone.Models;

public class PopularProductResponse {
    private int id;
    private Product product;

    public PopularProductResponse(int id, Product product) {
        this.id = id;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

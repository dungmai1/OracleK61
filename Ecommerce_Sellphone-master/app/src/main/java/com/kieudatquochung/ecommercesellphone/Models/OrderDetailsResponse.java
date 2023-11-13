package com.kieudatquochung.ecommercesellphone.Models;

public class OrderDetailsResponse {
    private int id;
    private OrderResponse order;
    private Product product;
    private int product_total_money;
    private int quantity;

    public OrderDetailsResponse(int id, OrderResponse order, Product product, int product_total_money, int quantity) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.product_total_money = product_total_money;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderResponse getOrder() {
        return order;
    }

    public void setOrder(OrderResponse order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProduct_total_money() {
        return product_total_money;
    }

    public void setProduct_total_money(int product_total_money) {
        this.product_total_money = product_total_money;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

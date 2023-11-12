package com.example.Ecommerce_SellPhone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Order_Details")
public class Order_Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer Product_total_money;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private int Quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order_Details() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct_total_money() {
        return Product_total_money;
    }

    public void setProduct_total_money(Integer product_total_money) {
        Product_total_money = product_total_money;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}

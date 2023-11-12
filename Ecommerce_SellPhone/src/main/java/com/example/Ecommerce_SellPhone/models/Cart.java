package com.example.Ecommerce_SellPhone.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Integer id;
    @Column(name = "created_date")
    private Date createDate;
    private int quantity;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Cart() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

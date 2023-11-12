package com.example.Ecommerce_SellPhone.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(targetEntity = Customer.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "created_date")
    private Date createDate;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Wishlist(Integer id, Customer customer, Date createDate, Product product) {
        this.id = id;
        this.customer = customer;
        this.createDate = createDate;
        this.product = product;
    }

    public Wishlist() {

    }

    public Integer getId() {
        return id;
    }

    public Wishlist(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
        this.createDate = new Date();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

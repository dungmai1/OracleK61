package com.example.Ecommerce_SellPhone.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tokens")
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    private Date createdDate;
    @OneToOne(targetEntity = Customer.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "customer_id")
    private Customer customer;

    public AuthenticationToken() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AuthenticationToken(Customer customer) {
        this.customer = customer;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }
}

package com.example.Ecommerce_SellPhone.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date Order_date;
    private Integer Order_total;
    private String Payment_Method;
    private String Address;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrder_date() {
        return Order_date;
    }

    public void setOrder_date(Date order_date) {
        Order_date = order_date;
    }

    public Integer getOrder_total() {
        return Order_total;
    }

    public void setOrder_total(Integer order_total) {
        Order_total = order_total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPayment_Method() {
        return Payment_Method;
    }

    public void setPayment_Method(String payment_Method) {
        Payment_Method = payment_Method;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

}

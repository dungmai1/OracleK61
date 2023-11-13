package com.kieudatquochung.ecommercesellphone.Models;

import java.util.Date;

public class OrderResponse {
    private int id;
    private Date order_date;
    private int order_total;
    private Customer customer;
    private String payment_Method;
    private String address;

    public OrderResponse(int id, Date order_date, int order_total, Customer customer, String payment_Method, String address) {
        this.id = id;
        this.order_date = order_date;
        this.order_total = order_total;
        this.customer = customer;
        this.payment_Method = payment_Method;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public int getOrder_total() {
        return order_total;
    }

    public void setOrder_total(int order_total) {
        this.order_total = order_total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPayment_Method() {
        return payment_Method;
    }

    public void setPayment_Method(String payment_Method) {
        this.payment_Method = payment_Method;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

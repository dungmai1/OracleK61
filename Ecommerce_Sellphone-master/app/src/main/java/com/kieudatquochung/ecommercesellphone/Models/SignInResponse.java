package com.kieudatquochung.ecommercesellphone.Models;

import java.io.Serializable;

public class SignInResponse implements Serializable {
    private String status;
    private String token;
    private Customer customer;

    public SignInResponse(String status, String token, Customer customer) {
        this.status = status;
        this.token = token;
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

package com.example.Ecommerce_SellPhone.models;

import jakarta.persistence.Entity;

public class Login {
    private int Customer_phone;
    private String Customer_password;

    public Login(int customer_phone, String customer_password) {
        Customer_phone = customer_phone;
        Customer_password = customer_password;
    }

    public int getCustomer_phone() {
        return Customer_phone;
    }

    public void setCustomer_phone(int customer_phone) {
        Customer_phone = customer_phone;
    }

    public String getCustomer_password() {
        return Customer_password;
    }

    public void setCustomer_password(String customer_password) {
        Customer_password = customer_password;
    }
}

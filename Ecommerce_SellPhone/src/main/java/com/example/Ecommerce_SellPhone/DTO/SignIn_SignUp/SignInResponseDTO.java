package com.example.Ecommerce_SellPhone.DTO.SignIn_SignUp;

import com.example.Ecommerce_SellPhone.models.Customer;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class SignInResponseDTO {
    private String status;
    private String token;
    private Customer customer;

    public SignInResponseDTO(String status, String token, Customer customer) {
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

package com.kieudatquochung.ecommercesellphone.Models;

public class SignInRequest {
    private int phone;
    private String password;

    public SignInRequest(int phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

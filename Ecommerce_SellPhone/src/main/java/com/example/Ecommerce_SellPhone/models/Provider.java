package com.example.Ecommerce_SellPhone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Provider")
public class Provider {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String Provider_name;
    private String Provider_address;
    private String Provider_phone;

    private String Provider_email;
    private String Provider_website;

    public Provider() {
    }


    public String getProvider_name() {
        return Provider_name;
    }

    public void setProvider_name(String provider_name) {
        Provider_name = provider_name;
    }

    public String getProvider_address() {
        return Provider_address;
    }

    public void setProvider_address(String provider_address) {
        Provider_address = provider_address;
    }

    public String getProvider_phone() {
        return Provider_phone;
    }

    public void setProvider_phone(String provider_phone) {
        Provider_phone = provider_phone;
    }

    public String getProvider_email() {
        return Provider_email;
    }

    public void setProvider_email(String provider_email) {
        Provider_email = provider_email;
    }

    public String getProvider_website() {
        return Provider_website;
    }

    public void setProvider_website(String provider_website) {
        Provider_website = provider_website;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

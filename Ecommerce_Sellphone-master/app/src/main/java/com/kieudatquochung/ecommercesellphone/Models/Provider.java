package com.kieudatquochung.ecommercesellphone.Models;

public class Provider {
    private int provider_ID;
    private String provider_name;
    private String provider_address;
    private String provider_phone;

    private String provider_email;
    private String provider_website;

    public Provider(int provider_ID, String provider_name, String provider_address, String provider_phone, String provider_email, String provider_website) {
        this.provider_ID = provider_ID;
        this.provider_name = provider_name;
        this.provider_address = provider_address;
        this.provider_phone = provider_phone;
        this.provider_email = provider_email;
        this.provider_website = provider_website;
    }

    public int getProvider_ID() {
        return provider_ID;
    }

    public void setProvider_ID(int provider_ID) {
        this.provider_ID = provider_ID;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getProvider_address() {
        return provider_address;
    }

    public void setProvider_address(String provider_address) {
        this.provider_address = provider_address;
    }

    public String getProvider_phone() {
        return provider_phone;
    }

    public void setProvider_phone(String provider_phone) {
        this.provider_phone = provider_phone;
    }

    public String getProvider_email() {
        return provider_email;
    }

    public void setProvider_email(String provider_email) {
        this.provider_email = provider_email;
    }

    public String getProvider_website() {
        return provider_website;
    }

    public void setProvider_website(String provider_website) {
        this.provider_website = provider_website;
    }
}

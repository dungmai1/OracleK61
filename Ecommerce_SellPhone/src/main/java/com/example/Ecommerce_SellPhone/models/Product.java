package com.example.Ecommerce_SellPhone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;

    private String Product_name;
    private int Price;
    private String Product_describe;
    private String Pictures;
    private String ScreenSize;
    private String OS;
    private String Memory_Storage_Capacity;
    private String Front_Camera;
    private String Back_Camera;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public Product() {

    }

    public Product(Integer id, String product_name, int price, String product_describe, String pictures, String screenSize, String OS, String memory_Storage_Capacity, String front_Camera, String back_Camera, Category category, Provider provider) {
        this.id = id;
        Product_name = product_name;
        Price = price;
        Product_describe = product_describe;
        Pictures = pictures;
        ScreenSize = screenSize;
        this.OS = OS;
        Memory_Storage_Capacity = memory_Storage_Capacity;
        Front_Camera = front_Camera;
        Back_Camera = back_Camera;
        this.category = category;
        this.provider = provider;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getProduct_describe() {
        return Product_describe;
    }

    public void setProduct_describe(String product_describe) {
        Product_describe = product_describe;
    }

    public String getPictures() {
        return Pictures;
    }

    public void setPictures(String pictures) {
        Pictures = pictures;
    }

    public String getScreenSize() {
        return ScreenSize;
    }

    public void setScreenSize(String screenSize) {
        ScreenSize = screenSize;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getMemory_Storage_Capacity() {
        return Memory_Storage_Capacity;
    }

    public void setMemory_Storage_Capacity(String memory_Storage_Capacity) {
        Memory_Storage_Capacity = memory_Storage_Capacity;
    }

    public String getFront_Camera() {
        return Front_Camera;
    }

    public void setFront_Camera(String front_Camera) {
        Front_Camera = front_Camera;
    }

    public String getBack_Camera() {
        return Back_Camera;
    }

    public void setBack_Camera(String back_Camera) {
        Back_Camera = back_Camera;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}

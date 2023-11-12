package com.example.Ecommerce_SellPhone.DTO;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {
    private Integer id;
    private String Product_name;
    private int Price;
    private  String Product_describe;
    private  String Pictures;
    private String ScreenSize;
    private  String OS;
    private  String Memory_Storage_Capacity;
    private String Front_Camera;
    private  String Back_Camera;
    private  Integer Category_ID;
    private  Integer Provider_ID;

    public ProductDTO(){

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

    public Integer getCategory_ID() {
        return Category_ID;
    }

    public void setCategory_ID(Integer category_ID) {
        Category_ID = category_ID;
    }

    public Integer getProvider_ID() {
        return Provider_ID;
    }

    public void setProvider_ID(Integer provider_ID) {
        Provider_ID = provider_ID;
    }
}

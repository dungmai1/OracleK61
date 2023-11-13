package com.kieudatquochung.ecommercesellphone.Models;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String product_name;
    private int price;
    private String product_describe;
    private String pictures;
    private String screenSize;
    private String os;
    private String memory_Storage_Capacity;
    private String front_Camera;
    private String back_Camera;

    public Product(int id, String product_name, int price, String product_describe, String pictures, String screenSize, String os, String memory_Storage_Capacity, String front_Camera, String back_Camera) {
        this.id = id;
        this.product_name = product_name;
        this.price = price;
        this.product_describe = product_describe;
        this.pictures = pictures;
        this.screenSize = screenSize;
        this.os = os;
        this.memory_Storage_Capacity = memory_Storage_Capacity;
        this.front_Camera = front_Camera;
        this.back_Camera = back_Camera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProduct_describe() {
        return product_describe;
    }

    public void setProduct_describe(String product_describe) {
        this.product_describe = product_describe;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getMemory_Storage_Capacity() {
        return memory_Storage_Capacity;
    }

    public void setMemory_Storage_Capacity(String memory_Storage_Capacity) {
        this.memory_Storage_Capacity = memory_Storage_Capacity;
    }

    public String getFront_Camera() {
        return front_Camera;
    }

    public void setFront_Camera(String front_Camera) {
        this.front_Camera = front_Camera;
    }

    public String getBack_Camera() {
        return back_Camera;
    }

    public void setBack_Camera(String back_Camera) {
        this.back_Camera = back_Camera;
    }
}

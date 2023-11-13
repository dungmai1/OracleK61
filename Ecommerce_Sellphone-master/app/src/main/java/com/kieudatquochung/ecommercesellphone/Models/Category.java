package com.kieudatquochung.ecommercesellphone.Models;

public class Category {
    private int category_ID;
    private String category_name;

    public Category(int category_ID, String category_name) {
        this.category_ID = category_ID;
        this.category_name = category_name;
    }

    public int getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(int category_ID) {
        this.category_ID = category_ID;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}

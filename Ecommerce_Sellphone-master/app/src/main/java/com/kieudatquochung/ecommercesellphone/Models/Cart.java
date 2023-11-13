package com.kieudatquochung.ecommercesellphone.Models;

import java.util.List;

public class Cart {
    private List<cartItemDTOList> cartItemDTOList;
    private int totalCost;

    public Cart(List<cartItemDTOList> cartItemDTOList, int totalCost ) {
        this.cartItemDTOList = cartItemDTOList;
        this.totalCost = totalCost;
    }

    public List<cartItemDTOList> getCartItemDTOListList() {
        return cartItemDTOList;
    }

    public void setCartItemDTOListList(List<cartItemDTOList> cartItemDTOListList) {
        this.cartItemDTOList = cartItemDTOListList;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}

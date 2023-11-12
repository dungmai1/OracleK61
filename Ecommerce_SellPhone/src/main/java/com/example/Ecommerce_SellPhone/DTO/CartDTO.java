package com.example.Ecommerce_SellPhone.DTO;

import java.util.List;

public class CartDTO {
    private List<CartItemDTO> cartItemDTOList;
    private int totalCost;

    public CartDTO() {
    }

    public List<CartItemDTO> getCartItemDTOList() {
        return cartItemDTOList;
    }

    public void setCartItemDTOList(List<CartItemDTO> cartItemDTOList) {
        this.cartItemDTOList = cartItemDTOList;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}

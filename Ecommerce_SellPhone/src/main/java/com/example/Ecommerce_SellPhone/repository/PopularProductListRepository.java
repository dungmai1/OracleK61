package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.models.PopularProductList;
import com.example.Ecommerce_SellPhone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopularProductListRepository extends JpaRepository<PopularProductList, Integer> {
    PopularProductList findByProduct(Product product);
}

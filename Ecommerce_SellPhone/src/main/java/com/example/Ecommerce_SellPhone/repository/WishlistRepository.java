package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {
    List<Wishlist> findAllByCustomerOrderByCreateDateDesc(Customer customer);
    Wishlist findByCustomerAndProduct(Customer customer, Product product);
}

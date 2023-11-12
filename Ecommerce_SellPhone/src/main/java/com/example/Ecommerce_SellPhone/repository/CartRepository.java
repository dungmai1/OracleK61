package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.models.Cart;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findAllByCustomerOrderByCreateDateDesc(Customer customer);
    Cart findByCustomerAndProduct(Customer customer, Product product);
    @Modifying
    @Query("update Cart c set c.quantity = :quantity where c.customer.id = :customer and c.product.id = :product")
    void updateCart(@Param("quantity") int quantity, @Param("customer") int customer, @Param("product") int product);
}

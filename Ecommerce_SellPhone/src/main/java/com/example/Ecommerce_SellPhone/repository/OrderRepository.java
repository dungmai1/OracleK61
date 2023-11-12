package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.models.Cart;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Order;
import com.example.Ecommerce_SellPhone.models.Order_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> getOrdersByCustomer(Customer customer);
}

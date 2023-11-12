package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Order;
import com.example.Ecommerce_SellPhone.models.Order_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_DetailsRepository extends JpaRepository<Order_Details,Integer> {
    List<Order_Details> findByOrderCustomerAndOrder_Id(Customer customer, int order_id);
    List<Order_Details> findByOrderId(int order_id);
}

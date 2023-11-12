package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.models.AuthenticationToken;
import com.example.Ecommerce_SellPhone.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByCustomer(Customer customer);
    AuthenticationToken findByToken(String token);
}

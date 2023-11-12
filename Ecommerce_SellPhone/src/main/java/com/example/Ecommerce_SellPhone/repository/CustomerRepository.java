package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByPhone(int phone);
    @Modifying
    @Query("update Customer c set c.email = :email,c.name =:name where c.id = :id")
    void updateCustomer(@Param("id") int id, @Param("email") String email, @Param("name") String name);
    @Procedure(procedureName = "ADDCUSTOMER")
    void callAddCustomerProcedure(
            @Param("p_phone") int p_phone,
            @Param("p_name") String p_name,
            @Param("p_email") String p_email,
            @Param("p_password") String p_password
    );
    @Query("select c from Customer c where c.id = :id")
    Customer getSingleCustomer(@Param("id")int id);
}

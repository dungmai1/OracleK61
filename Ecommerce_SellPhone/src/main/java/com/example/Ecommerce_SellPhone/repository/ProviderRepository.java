package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.DTO.ProviderDTO;
import com.example.Ecommerce_SellPhone.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Integer> {
    @Procedure(procedureName = "update_provider")
    void updateProvider(
            @Param("p_id") int id,
            @Param("p_name") String name,
            @Param("p_address") String address,
            @Param("p_phone") String phone,
            @Param("p_email") String email,
            @Param("p_website") String website
    );
}

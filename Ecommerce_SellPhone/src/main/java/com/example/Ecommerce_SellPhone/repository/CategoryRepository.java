package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Procedure(procedureName = "ADDCATEGORY")
    void callAddCategoryProcedure(
            @Param("p_Category_name") String p_Category_name
    );
    @Procedure(procedureName = "UPDATECATEGORY")
    void callUpdateCategoryProcedure(
            @Param("p_id") int p_id,
            @Param("p_Category_name") String p_Category_name
    );
}

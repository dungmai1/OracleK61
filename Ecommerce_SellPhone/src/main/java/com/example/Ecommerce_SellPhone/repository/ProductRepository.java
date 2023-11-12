package com.example.Ecommerce_SellPhone.repository;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategoryId(Integer Category_ID);
    @Procedure(procedureName = "ADDPRODUCT")
    void callAddProductProcedure(
            @Param("p_BackCamera") String p_BackCamera,
            @Param("p_FrontCamera") String p_FrontCamera,
            @Param("p_MemoryStorageCapacity") String p_MemoryStorageCapacity,
            @Param("p_OS") String p_OS,
            @Param("p_Pictures") String p_Pictures,
            @Param("p_Price") Integer p_Price,
            @Param("p_ProductDescribe") String p_ProductDescribe,
            @Param("p_ProductName") String p_ProductName,
            @Param("p_ScreenSize") String p_ScreenSize,
            @Param("p_CategoryID") Integer p_CategoryID,
            @Param("p_ProviderID") Integer p_ProviderID
    );
    @Query("SELECT p FROM Product p WHERE LOWER(p.Product_name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<Product> searchProduct(String name);
    @Procedure(procedureName = "UpdateProduct")
    void updateProduct(
            @Param("p_id") Integer id,
            @Param("p_product_name") String productName,
            @Param("p_price") int price,
            @Param("p_product_describe") String productDescription,
            @Param("p_pictures") String pictures,
            @Param("p_screen_size") String screenSize,
            @Param("p_os") String os,
            @Param("p_memory_storage_capacity") String memoryStorageCapacity,
            @Param("p_front_camera") String frontCamera,
            @Param("p_back_camera") String backCamera,
            @Param("p_category_id") Integer categoryId,
            @Param("p_provider_id") Integer providerId
    );
}

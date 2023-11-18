package com.example.Ecommerce_SellPhone.service.Product;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.models.Provider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService  {

    public void createProduct(ProductDTO productDTO);
    public List<ProductDTO> getAllProduct();
    public ProductDTO getProductDTO(Product product);
    public List<ProductDTO> getAllProductByCategory(int Category_ID);

    Product findById(int productID);
    public List<ProductDTO> searchProduct(String product_name);

    void updateProduct(ProductDTO productDTO);
    ProductDTO FindByID(int productID);
}

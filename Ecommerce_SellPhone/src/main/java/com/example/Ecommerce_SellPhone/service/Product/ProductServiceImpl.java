package com.example.Ecommerce_SellPhone.service.Product;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.Exception.ProductNotExistsException;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.models.Provider;
import com.example.Ecommerce_SellPhone.repository.CategoryRepository;
import com.example.Ecommerce_SellPhone.repository.ProductRepository;
import com.example.Ecommerce_SellPhone.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public void createProduct(ProductDTO productDTO) {
        productRepository.callAddProductProcedure(
                productDTO.getBack_Camera(),
                productDTO.getFront_Camera(),
                productDTO.getMemory_Storage_Capacity(),
                productDTO.getOS(),
                productDTO.getPictures(),
                productDTO.getPrice(),
                productDTO.getProduct_describe(),
                productDTO.getProduct_name(),
                productDTO.getScreenSize(),
                productDTO.getCategory_ID(),
                productDTO.getProvider_ID()
        );
    }
    public ProductDTO getProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_describe(product.getProduct_describe());
        productDTO.setProduct_name(product.getProduct_name());
        productDTO.setPictures(product.getPictures());
        productDTO.setPrice(product.getPrice());
        productDTO.setBack_Camera(product.getBack_Camera());
        productDTO.setFront_Camera(product.getFront_Camera());
        productDTO.setOS(product.getOS());
        productDTO.setScreenSize(product.getScreenSize());
        productDTO.setMemory_Storage_Capacity(product.getMemory_Storage_Capacity());
        productDTO.setCategory_ID(product.getCategory().getId());
        productDTO.setProvider_ID(product.getProvider().getId());
        productDTO.setId(product.getId());
        return productDTO;
    }
    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product:  products){
            productDTOList.add(getProductDTO(product));
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> getAllProductByCategory(int Category_ID) {
        List<Product> products = productRepository.findAllByCategoryId(Category_ID);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product:  products){
            productDTOList.add(getProductDTO(product));
        }
        return productDTOList;
    }
    @Override
    public Product findById(int productID) throws ProductNotExistsException{
        Optional<Product> optionalProduct = productRepository.findById(productID);
        if(optionalProduct.isEmpty()){
            throw new ProductNotExistsException("product id is valid " + productID);
        }
        return optionalProduct.get();
    }

    @Override
    public List<ProductDTO> searchProduct(String product_name) {
        List<Product> products = productRepository.searchProduct(product_name);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product:  products){
            productDTOList.add(getProductDTO(product));
        }
        return productDTOList;
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        productRepository.updateProduct(
                productDTO.getId(),
                productDTO.getProduct_name(),
                productDTO.getPrice(),
                productDTO.getProduct_describe(),
                productDTO.getPictures(),
                productDTO.getScreenSize(),
                productDTO.getOS(),
                productDTO.getMemory_Storage_Capacity(),
                productDTO.getFront_Camera(),
                productDTO.getBack_Camera(),
                productDTO.getCategory_ID(),
                productDTO.getProvider_ID()
                );
    }

    @Override
    public ProductDTO FindByID(int productID) {
        Optional<Product> optionalProduct = productRepository.findById(productID);
        if(optionalProduct.isEmpty()){
            throw new ProductNotExistsException("product id is valid " + productID);
        }
        ProductDTO productDTO = getProductDTO(optionalProduct.get());
        return productDTO;
    }
}

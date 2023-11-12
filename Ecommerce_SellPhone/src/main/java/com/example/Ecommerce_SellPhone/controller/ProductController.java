package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.models.Provider;
import com.example.Ecommerce_SellPhone.repository.CategoryRepository;
import com.example.Ecommerce_SellPhone.repository.ProductRepository;
import com.example.Ecommerce_SellPhone.repository.ProviderRepository;
import com.example.Ecommerce_SellPhone.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO){
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategory_ID());
        Optional<Provider> optionalProvider = providerRepository.findById(productDTO.getProvider_ID());
        if(!optionalCategory.isPresent() && !optionalProvider.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"category or provider does not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(productDTO);
        return new ResponseEntity<>(new ApiResponse(true,"product has been added"),HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        List<ProductDTO> productDTOList = productService.getAllProduct();
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }
    @GetMapping("/{Category_ID}")
    public ResponseEntity<List<ProductDTO>> getAllProductsByCategoryId(@PathVariable Integer Category_ID) {
        List<ProductDTO> productDTOList = productService.getAllProductByCategory(Category_ID);
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProduct(@RequestParam("product_name") String product_name){
        List<ProductDTO> productDTOList = productService.searchProduct(product_name);
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductDTO productDTO){
        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
        if(!optionalProduct.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"product does not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDTO);
        return new ResponseEntity<>(new ApiResponse(true,"product has been added"),HttpStatus.CREATED);
    }
    @GetMapping("/getSingleProduct")
    public ProductDTO getSingleProduct(@RequestParam("product_id") int product_id){
        Product product = productService.findById(product_id);
        ProductDTO productDTO = productService.getProductDTO(product);
        return productDTO;
    }
}

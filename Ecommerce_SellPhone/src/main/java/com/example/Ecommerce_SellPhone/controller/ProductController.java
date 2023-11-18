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
@CrossOrigin("http://localhost:8081/")
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
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO) {
        // Check if category_ID is not null and exists in the database
        if (productDTO.getCategory_ID() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategory_ID());
            if (!optionalCategory.isPresent()) {
                return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.BAD_REQUEST);
            }
        }

        // Check if provider_ID is not null and exists in the database
        if (productDTO.getProvider_ID() != null) {
            Optional<Provider> optionalProvider = providerRepository.findById(productDTO.getProvider_ID());
            if (!optionalProvider.isPresent()) {
                return new ResponseEntity<>(new ApiResponse(false, "Provider does not exist"), HttpStatus.BAD_REQUEST);
            }
        }

        // If both category and provider are valid, proceed to create the product
        productService.createProduct(productDTO);

        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        List<ProductDTO> productDTOList = productService.getAllProduct();
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }
    @GetMapping("/{Category_ID}")
    public ResponseEntity<List<ProductDTO>> getAllProductsByCategoryId(@PathVariable("Category_ID") int Category_ID) {
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
    @GetMapping("/singleProduct/{product_id}")
    public ProductDTO getSingleProduct(@PathVariable("product_id") int product_id){
        ProductDTO productDTO = productService.FindByID(product_id);
        return productDTO;
    }

}

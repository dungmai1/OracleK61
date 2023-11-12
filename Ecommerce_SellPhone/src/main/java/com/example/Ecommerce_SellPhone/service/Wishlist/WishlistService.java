package com.example.Ecommerce_SellPhone.service.Wishlist;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.Exception.ProductNotExistsException;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.models.Wishlist;
import com.example.Ecommerce_SellPhone.repository.WishlistRepository;
import com.example.Ecommerce_SellPhone.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {
    @Autowired
    WishlistRepository wishlistRepository;
    @Autowired
    ProductService productService;
    public void createWishlist(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }
    public List<ProductDTO> getWishlistForCustomer(Customer customer) {
        final List<Wishlist> wishlists = wishlistRepository.findAllByCustomerOrderByCreateDateDesc(customer);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Wishlist wishlist : wishlists){
            productDTOList.add(productService.getProductDTO(wishlist.getProduct()));
        }
        return productDTOList;
    }
    public void deleteProductFromWishlist(Customer customer, int product_id){
        Product product = productService.findById(product_id);
        Wishlist wishlist = wishlistRepository.findByCustomerAndProduct(customer,product);
        wishlistRepository.delete(wishlist);
    }
    public Wishlist checkWishlist(Customer customer, int product_id){
        Product product = productService.findById(product_id);
        Wishlist wishlist = wishlistRepository.findByCustomerAndProduct(customer,product);
        return  wishlist;
    }
}

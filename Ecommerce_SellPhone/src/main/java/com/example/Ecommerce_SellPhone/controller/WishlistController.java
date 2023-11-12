package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.AuthenticationToken;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.models.Wishlist;
import com.example.Ecommerce_SellPhone.repository.WishlistRepository;
import com.example.Ecommerce_SellPhone.service.Auth.AuthService;
import com.example.Ecommerce_SellPhone.service.Wishlist.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;
    @Autowired
    AuthService authService;
    @Autowired
    WishlistRepository wishlistRepository;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token") String token){
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        Wishlist wishlist = new Wishlist(customer,product);
        Wishlist existingWishlistEntry = wishlistRepository.findByCustomerAndProduct(customer, product);
        if (existingWishlistEntry != null) {
            ApiResponse apiResponse = new ApiResponse(false, "Product already exists in the wishlist.");
            return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
        }
        wishlistService.createWishlist(wishlist);
        ApiResponse apiResponse = new ApiResponse(true, "Add wishlist success");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDTO>> getWishlist(@PathVariable("token") String token){
        authService.authenticate(token);
        Customer customer =authService.getCustomer(token);
        List<ProductDTO> wishListForCustomer = wishlistService.getWishlistForCustomer(customer);
        return new ResponseEntity<>(wishListForCustomer,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{token}")
    public ResponseEntity<ApiResponse> deleteWishlist(@PathVariable("token") String token,
                                                      @RequestParam("product_id")int product_id){
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        wishlistService.deleteProductFromWishlist(customer, product_id);
        ApiResponse apiResponse = new ApiResponse(true, "Remove product from wishlist success");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/check/{token}")
    public ResponseEntity<Boolean> checkWishlist(@PathVariable("token") String token,
                                                 @RequestParam("product_id") int product_id){
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        Wishlist existingWishlistEntry = wishlistService.checkWishlist(customer, product_id);
        if (existingWishlistEntry != null) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
}

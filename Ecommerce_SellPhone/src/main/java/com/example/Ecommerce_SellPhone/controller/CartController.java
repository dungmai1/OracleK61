package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.AddToCartDTO;
import com.example.Ecommerce_SellPhone.DTO.CartDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Cart;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Wishlist;
import com.example.Ecommerce_SellPhone.service.Auth.AuthService;
import com.example.Ecommerce_SellPhone.service.Cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private AuthService authService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDTO addToCartDTO,
                                                 @RequestParam("token") String token){
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        cartService.addToCart(addToCartDTO,customer);
        return new ResponseEntity<>(new ApiResponse(true,"Added to Cart"), HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<CartDTO> getCartItems(@RequestParam("token") String token){
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        CartDTO cartDTO = cartService.listCartItems(customer);
        return new ResponseEntity<>(cartDTO,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer cartItemId,
                                                      @RequestParam("token") String token){
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        cartService.deleteCartItem(cartItemId,customer);
        return new ResponseEntity<>(new ApiResponse(true,"Items has been removed"), HttpStatus.OK);
    }
    @GetMapping("/check/{token}")
    public ResponseEntity<Boolean> checkWishlist(@PathVariable("token") String token,
                                                 @RequestParam("product_id") int product_id){
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        Cart existingCartEntry = cartService.checkCart(customer, product_id);
        if (existingCartEntry != null) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
    @PostMapping("/update/increase/{token}")
    public ResponseEntity<ApiResponse> updateQuantityIncrease(@PathVariable("token") String token,
                                                      @RequestParam("product_id") int product_id){
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        cartService.updateQuantityIncrease(customer,product_id);
        return new ResponseEntity<>(new ApiResponse(true,"Updated Quantity success"), HttpStatus.OK);
    }
    @PostMapping("/update/decrease/{token}")
    public ResponseEntity<ApiResponse> updateQuantityDecrease(@PathVariable("token") String token,
                                                              @RequestParam("product_id") int product_id){
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        cartService.updateQuantityDecrease(customer,product_id);
        return new ResponseEntity<>(new ApiResponse(true,"Updated Quantity success"), HttpStatus.OK);
    }
}

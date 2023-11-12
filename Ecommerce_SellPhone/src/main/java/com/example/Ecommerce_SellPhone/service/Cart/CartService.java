package com.example.Ecommerce_SellPhone.service.Cart;

import com.example.Ecommerce_SellPhone.DTO.AddToCartDTO;
import com.example.Ecommerce_SellPhone.DTO.CartDTO;
import com.example.Ecommerce_SellPhone.DTO.CartItemDTO;
import com.example.Ecommerce_SellPhone.Exception.CustomException;
import com.example.Ecommerce_SellPhone.models.Cart;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.repository.CartRepository;
import com.example.Ecommerce_SellPhone.service.Product.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartRepository cartRepository;
    public void addToCart(AddToCartDTO addToCartDTO, Customer customer) {
        Product product = productService.findById(addToCartDTO.getProductID());
        Cart cart = new Cart();
        cart.setCreateDate(new Date());
        cart.setProduct(product);
        cart.setCustomer(customer);
        cart.setQuantity(addToCartDTO.getQuantity());
        cartRepository.save(cart);
    }

    public CartDTO listCartItems(Customer customer) {
        List<Cart> cartList =  cartRepository.findAllByCustomerOrderByCreateDateDesc(customer);
        List<CartItemDTO> cartItems = new ArrayList<>();
        int totalCost = 0;
        for(Cart cart : cartList){
            CartItemDTO cartItemDTO = new CartItemDTO(cart);
            totalCost +=cartItemDTO.getQuantity() * cart.getProduct().getPrice();
            cartItems.add(cartItemDTO);
        }
        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotalCost(totalCost);
        cartDTO.setCartItemDTOList(cartItems);
        return cartDTO;
    }
    public void deleteCartItem(Integer cartItemId, Customer customer) {
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
        if(optionalCart.isEmpty()){
            throw new CustomException("cart item id is invalid"+cartItemId);
        }
        Cart cart = optionalCart.get();
        if(cart.getCustomer()!=customer){
            throw new CustomException("cart item does not belong to customer"+cartItemId);
        }
        cartRepository.delete(cart);
    }
    public Cart checkCart(Customer customer, int product_id){
        Product product = productService.findById(product_id);
        Cart cart = cartRepository.findByCustomerAndProduct(customer, product);
        return cart;
    }
    @Transactional
    public void updateQuantityIncrease(Customer customer, int product_id){
        Product product = productService.findById(product_id);
        Cart cart = cartRepository.findByCustomerAndProduct(customer,product);
        int quantity = cart.getQuantity()+1;
        cartRepository.updateCart(quantity,customer.getId(),product_id);
    }
    @Transactional
    public void updateQuantityDecrease(Customer customer, int product_id){
        Product product = productService.findById(product_id);
        Cart cart = cartRepository.findByCustomerAndProduct(customer,product);
        int quantity = cart.getQuantity();
        if(quantity==1){
            throw new CustomException("Quantity cannot be 0");
        }else{
            int newQuantity = quantity-1;
            cartRepository.updateCart(newQuantity,customer.getId(),product_id);
        }
    }
}

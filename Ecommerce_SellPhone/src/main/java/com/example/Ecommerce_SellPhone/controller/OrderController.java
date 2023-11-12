package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.CartDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Order;
import com.example.Ecommerce_SellPhone.models.Order_Details;
import com.example.Ecommerce_SellPhone.service.Auth.AuthService;
import com.example.Ecommerce_SellPhone.service.Order.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AuthService authService;

    @PostMapping("/create/{token}")
    public ResponseEntity<ApiResponse> createOrder(@PathVariable("token") String token,
                                                   @RequestBody CartDTO cartDTO,
                                                   @RequestParam("payment_method") String payment_method,
                                                   @RequestParam("address") String address) {
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        orderService.createOrder(customer, cartDTO, payment_method, address);
        return new ResponseEntity<>(new ApiResponse(true, "order has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<Order>> getAllOrder(@PathVariable("token") String token) {
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        List<Order> order = orderService.getAllOrder(customer);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}

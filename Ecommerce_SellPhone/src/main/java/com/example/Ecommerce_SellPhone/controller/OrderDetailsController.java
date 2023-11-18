package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Order;
import com.example.Ecommerce_SellPhone.models.Order_Details;
import com.example.Ecommerce_SellPhone.service.Auth.AuthService;
import com.example.Ecommerce_SellPhone.service.Order.OrderService;
import com.example.Ecommerce_SellPhone.service.OrderDetails.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private AuthService authService;
    @GetMapping("/{token}")
    public ResponseEntity<List<Order_Details>> getAllOrder(@PathVariable("token") String token,
                                                   @RequestParam("order_id")int order_id) {
        authService.authenticate(token);
        Customer customer = authService.getCustomer(token);
        List<Order_Details> orderDetails = orderDetailsService.getAllOrderDetails(customer,order_id);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<Order_Details>> getAllOrderDetails(@RequestParam("order_id")int order_id) {
        List<Order_Details> orderDetails = orderDetailsService.getOrderDetail_OrderID(order_id);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
}

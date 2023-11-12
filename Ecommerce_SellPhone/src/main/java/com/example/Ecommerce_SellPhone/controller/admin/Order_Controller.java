package com.example.Ecommerce_SellPhone.controller.admin;

import com.example.Ecommerce_SellPhone.models.Order;
import com.example.Ecommerce_SellPhone.models.Order_Details;
import com.example.Ecommerce_SellPhone.service.Order.OrderService;
import com.example.Ecommerce_SellPhone.service.OrderDetails.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class Order_Controller {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;
    @GetMapping("/")
    public String getOrder(Model model){
        List<Order> orderList = orderService.getAllOrderAmin();
        List<Order_Details> orderDetails = orderDetailsService.getAllOrderDetails_Admin();
        model.addAttribute("orderList",orderList);
        model.addAttribute("orderDetails",orderDetails);
        return "/order/index";
    }
    @GetMapping("/details/{order_id}")
    public String getOrderDetails(Model model,@PathVariable("order_id")int order_id){
        List<Order_Details> orderDetails = orderDetailsService.getOrderDetail_OrderID(order_id);
        model.addAttribute("orderDetails",orderDetails);
        return "/order/details";
    }
}

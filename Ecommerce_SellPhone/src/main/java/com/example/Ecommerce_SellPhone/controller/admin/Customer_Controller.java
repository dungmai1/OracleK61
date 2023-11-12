package com.example.Ecommerce_SellPhone.controller.admin;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
@Controller
@RequestMapping("admin/customer")
public class Customer_Controller {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/")
    public String customer(Model model){
        List<Customer> customerList = customerService.getAllUser();
        customerList.sort(Comparator.comparing(Customer::getId));
        model.addAttribute("customerList",customerList);
        return "customer/index";
    }
    @GetMapping("/add")
    public String add(Model model){
        return "customer/add";
    }
}

package com.example.Ecommerce_SellPhone.service.OrderDetails;

import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Order;
import com.example.Ecommerce_SellPhone.models.Order_Details;
import com.example.Ecommerce_SellPhone.repository.Order_DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private Order_DetailsRepository orderDetailsRepository;

    public List<Order_Details> getAllOrderDetails(Customer customer, int order_id) {
        List<Order_Details> orderDetails = orderDetailsRepository.findByOrderCustomerAndOrder_Id(customer,order_id);
        return orderDetails;
    }
    public List<Order_Details> getAllOrderDetails_Admin() {
        List<Order_Details> orderDetails = orderDetailsRepository.findAll();
        return orderDetails;
    }
    public List<Order_Details> getOrderDetail_OrderID(int order_id){
        List<Order_Details> orderDetails = orderDetailsRepository.findByOrderId(order_id);
        return orderDetails;
    }
}

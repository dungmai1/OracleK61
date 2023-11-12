package com.example.Ecommerce_SellPhone.service.Order;

import com.example.Ecommerce_SellPhone.DTO.CartDTO;
import com.example.Ecommerce_SellPhone.DTO.CartItemDTO;
import com.example.Ecommerce_SellPhone.Exception.CustomException;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.models.Order;
import com.example.Ecommerce_SellPhone.models.Order_Details;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.repository.CartRepository;
import com.example.Ecommerce_SellPhone.repository.OrderRepository;
import com.example.Ecommerce_SellPhone.repository.Order_DetailsRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Order_DetailsRepository orderDetailsRepository;
    @Autowired
    private CartRepository cartRepository;
    public void createOrder(Customer customer, CartDTO cartDTO, String payment_method, String address) {
        Order order = new Order();
        order.setOrder_date(new Date());
        order.setCustomer(customer);
        order.setPayment_Method(payment_method);
        order.setAddress(address);
        order = orderRepository.save(order);

        for (CartItemDTO cartItem : cartDTO.getCartItemDTOList()) {
            Order_Details orderDetails = new Order_Details();
            orderDetails.setOrder(order);
            Product product = cartItem.getProduct();
            orderDetails.setProduct(product);

            // Set quantity in Order_Details
            orderDetails.setQuantity(cartItem.getQuantity());

            int subtotal = product.getPrice() * cartItem.getQuantity();
            orderDetails.setProduct_total_money(subtotal);
            orderDetailsRepository.save(orderDetails);

            Integer cartItemId = cartItem.getId();
            cartRepository.deleteById(cartItemId);
        }

        order.setOrder_total(cartDTO.getTotalCost());
        orderRepository.save(order);
    }

    public List<Order> getAllOrder(Customer customer) {
        List<Order> orderList = orderRepository.getOrdersByCustomer(customer);
        return orderList;
    }
    public List<Order> getAllOrderAmin(){
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }
}

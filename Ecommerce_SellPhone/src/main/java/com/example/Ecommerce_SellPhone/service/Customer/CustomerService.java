package com.example.Ecommerce_SellPhone.service.Customer;

import com.example.Ecommerce_SellPhone.DTO.SignIn_SignUp.SignInDTO;
import com.example.Ecommerce_SellPhone.DTO.SignIn_SignUp.SignInResponseDTO;
import com.example.Ecommerce_SellPhone.DTO.SignIn_SignUp.SignUpDTO;
import com.example.Ecommerce_SellPhone.DTO.SignIn_SignUp.ResponseDTO;
import com.example.Ecommerce_SellPhone.models.Customer;

import java.util.List;

public interface CustomerService {
    public ResponseDTO createCustomer(SignUpDTO signUpDTO);

    SignInResponseDTO SignIn(SignInDTO signInDTO);

    void updateCustomer(Customer customer, int customer_id, String name, String email);

    List<Customer> getAllUser();
    Customer getSingleCustomer(int customer_id);
}

package com.example.Ecommerce_SellPhone.service.Customer;

import com.example.Ecommerce_SellPhone.DTO.SignIn_SignUp.SignInDTO;
import com.example.Ecommerce_SellPhone.DTO.SignIn_SignUp.SignInResponseDTO;
import com.example.Ecommerce_SellPhone.DTO.SignIn_SignUp.SignUpDTO;
import com.example.Ecommerce_SellPhone.DTO.SignIn_SignUp.ResponseDTO;
import com.example.Ecommerce_SellPhone.Exception.AuthenticationFailException;
import com.example.Ecommerce_SellPhone.Exception.CustomException;
import com.example.Ecommerce_SellPhone.models.AuthenticationToken;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.repository.CustomerRepository;
import com.example.Ecommerce_SellPhone.service.Auth.AuthService;
import com.example.Ecommerce_SellPhone.service.Product.ProductService;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

}

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
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    ProductService productService;
    @Transactional
    @Override
    public ResponseDTO createCustomer(SignUpDTO signUpDTO) {
        if(Objects.nonNull(customerRepository.findByPhone(signUpDTO.getPhone()))){
            throw new CustomException("customer already present");
        }
        String EncryptPassword = signUpDTO.getPassword();
        try{
            EncryptPassword = hashPassword(signUpDTO.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer(signUpDTO.getPhone(), signUpDTO.getName(), signUpDTO.getEmail(),EncryptPassword);
        //save customer
        customerRepository.save(customer);

        //create token
        final AuthenticationToken authenticationToken = new AuthenticationToken(customer);
        authService.saveConfirmationToken(authenticationToken);

        ResponseDTO responseDTO = new ResponseDTO("success","response");
        return responseDTO;
    }

    @Override
    public SignInResponseDTO SignIn(SignInDTO signInDTO) {
        Customer customer = customerRepository.findByPhone(signInDTO.getPhone());
        if(Objects.isNull(customer)){
            throw new AuthenticationFailException("customer is not value");
        }
        try{
            if(!customer.getPassword().equals(hashPassword(signInDTO.getPassword()))){
                throw new AuthenticationFailException("wrong password");
            }
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        AuthenticationToken token = authService.getToken(customer);
        if(Objects.isNull(token)){
            throw new CustomException("Token is not present");
        }
        return new SignInResponseDTO("success", token.getToken(),customer);
    }
    @Transactional
    @Override
    public void updateCustomer(Customer customer,int customer_id, String email, String name) {
        customerRepository.updateCustomer(customer_id,email,name);
    }

    @Override
    public List<Customer> getAllUser() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList;
    }

    @Override
    public Customer getSingleCustomer(int customer_id) {
        Customer customer = customerRepository.getSingleCustomer(customer_id);
        return customer;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException  {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

}

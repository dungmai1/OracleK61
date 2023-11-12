package com.example.Ecommerce_SellPhone.service.Auth;

import com.example.Ecommerce_SellPhone.Exception.AuthenticationFailException;
import com.example.Ecommerce_SellPhone.models.AuthenticationToken;
import com.example.Ecommerce_SellPhone.models.Customer;

public interface AuthService {
    void saveConfirmationToken(AuthenticationToken authenticationToken);

    public AuthenticationToken getToken(Customer customer);
    public Customer getCustomer(String token);
    public void authenticate(String token);
}



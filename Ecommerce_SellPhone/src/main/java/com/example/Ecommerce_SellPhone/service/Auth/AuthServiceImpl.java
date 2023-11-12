package com.example.Ecommerce_SellPhone.service.Auth;

import com.example.Ecommerce_SellPhone.Exception.AuthenticationFailException;
import com.example.Ecommerce_SellPhone.models.AuthenticationToken;
import com.example.Ecommerce_SellPhone.models.Customer;
import com.example.Ecommerce_SellPhone.repository.CustomerRepository;
import com.example.Ecommerce_SellPhone.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private TokenRepository tokenRepository;
    @Override
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }
    public AuthenticationToken getToken(Customer customer){
        return tokenRepository.findByCustomer(customer);
    }
    public Customer getCustomer(String token){
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if(Objects.isNull(authenticationToken)){
            return null;
        }
        return authenticationToken.getCustomer();
    }
    public void authenticate(String token) throws AuthenticationFailException{
        if(Objects.isNull(token)){
            throw new AuthenticationFailException("token is not present");
        }
        if (Objects.isNull(getCustomer(token))){
            throw new AuthenticationFailException("token is not valid");
        }
    }
}

package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.ProviderDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Provider;
import com.example.Ecommerce_SellPhone.service.Provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProvider(@RequestBody ProviderDTO providerDTO) {
        providerService.createProvider(providerDTO);
        return new ResponseEntity<>(new ApiResponse(true,"Provider created"), HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/")
    public ResponseEntity<List<Provider>> getAllProvider() {
        List<Provider> provider = providerService.getAllProvider();
        return new ResponseEntity<>(provider,HttpStatus.OK);
    }
}

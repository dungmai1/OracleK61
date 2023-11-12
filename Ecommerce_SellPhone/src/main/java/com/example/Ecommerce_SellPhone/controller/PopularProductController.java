package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.models.PopularProductList;
import com.example.Ecommerce_SellPhone.service.PopularProductList.PopularProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/popular")
public class PopularProductController {
    @Autowired
    private PopularProductListService popularProductListService;
    @GetMapping("/")
    public ResponseEntity<List<PopularProductList>> getAllProduct(){
        List<PopularProductList> popularProductLists = popularProductListService.getAllPopularProduct();
        return new ResponseEntity<>(popularProductLists, HttpStatus.OK);
    }
}

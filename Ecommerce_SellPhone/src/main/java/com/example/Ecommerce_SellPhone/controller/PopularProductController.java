package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.PopularProductList;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.service.PopularProductList.PopularProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody Product product){
        if(popularProductListService.create(product)){
            return new ResponseEntity<>(new ApiResponse(true, "Create Success"), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new ApiResponse(false, "Failed"), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") int id){
        popularProductListService.delete(id);
        return new ResponseEntity<>(new ApiResponse(true, "Deleted Success"), HttpStatus.OK);
    }
}

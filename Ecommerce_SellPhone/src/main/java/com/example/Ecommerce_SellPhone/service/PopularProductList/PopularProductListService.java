package com.example.Ecommerce_SellPhone.service.PopularProductList;

import com.example.Ecommerce_SellPhone.models.PopularProductList;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.repository.PopularProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopularProductListService {
    @Autowired
    private PopularProductListRepository popularProductListRepository;

    public List<PopularProductList> getAllPopularProduct(){
        List<PopularProductList> popularProductLists = popularProductListRepository.findAll();
        return popularProductLists;
    }
    public Boolean create(Product product) {
        PopularProductList existingProduct = popularProductListRepository.findByProduct(product);
        if (existingProduct == null) {
            PopularProductList popularProduct = new PopularProductList();
            popularProduct.setProduct(product);
            popularProductListRepository.save(popularProduct);
            return true;
        } else {
            return false;
        }
    }
    public void delete(int id){
        PopularProductList existingProduct = popularProductListRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            popularProductListRepository.deleteById(id);
        } else {
        }
    }
}

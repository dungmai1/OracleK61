package com.example.Ecommerce_SellPhone.controller;

import com.example.Ecommerce_SellPhone.DTO.CategoryDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("http://localhost:8081/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDTO categoryDTO){
        try {
            categoryService.createCategory(categoryDTO);
            return new ResponseEntity<>(new ApiResponse(true, "Category has been created"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Failed to create category: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> category = categoryService.getAllCategory();
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody CategoryDTO categoryDTO){
        try {
            categoryService.updateCategory(categoryDTO);
            return new ResponseEntity<>(new ApiResponse(true, "Update category success"), HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse(false, "Failed to update category"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{category_id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("category_id") int category_id){
        Category category = categoryService.getCategoryById(category_id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{category_id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("category_id") int category_id){
        if (categoryService.delete(category_id)){
            return new ResponseEntity<>(new ApiResponse(true,"deleted success"), HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(new ApiResponse(false, "Category not found or deletion failed"), HttpStatus.NOT_FOUND);
        }
    }
}

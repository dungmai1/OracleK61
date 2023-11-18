package com.example.Ecommerce_SellPhone.service.Category;

import com.example.Ecommerce_SellPhone.DTO.CategoryDTO;
import com.example.Ecommerce_SellPhone.models.Category;

import java.util.List;

public interface CategoryService {
    public void createCategory(CategoryDTO categoryDTO);
    public List<Category> getAllCategory();
    public void updateCategory(CategoryDTO categoryDTO);
    Category findById(Integer id);
    Boolean delete(Integer id);
    public Category getCategoryById(int category_id);
}

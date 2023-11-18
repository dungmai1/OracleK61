package com.example.Ecommerce_SellPhone.service.Category;

import com.example.Ecommerce_SellPhone.DTO.CategoryDTO;
import com.example.Ecommerce_SellPhone.Exception.CustomException;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        categoryRepository.callAddCategoryProcedure(categoryDTO.getCategory_name());
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findById(categoryDTO.getId());
        if(category.isEmpty()){
            throw new CustomException("category id is valid"+ categoryDTO.getId());
        }else{
            categoryRepository.callUpdateCategoryProcedure(categoryDTO.getId(), categoryDTO.getCategory_name());
        }
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Boolean delete(Integer id) {
        try{
            categoryRepository.delete(findById(id));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category getCategoryById(int category_id) {
        Category category = categoryRepository.findById(category_id).get();
        return category;
    }
}

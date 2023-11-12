package com.example.Ecommerce_SellPhone.controller.admin;

import com.example.Ecommerce_SellPhone.DTO.CategoryDTO;
import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.service.Category.CategoryService;
import com.example.Ecommerce_SellPhone.service.Customer.CustomerService;
import com.example.Ecommerce_SellPhone.service.Product.ProductService;
import com.example.Ecommerce_SellPhone.service.Provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("admin/category")
public class Category_Controller {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/")
    public String category(Model model){
        List<Category> categoryList = categoryService.getAllCategory();
        categoryList.sort(Comparator.comparing(Category::getId));
        model.addAttribute("categoryList",categoryList);
        return "category/index";
    }
    @GetMapping("/add")
    public String addCategory(Model model){
        CategoryDTO category = new CategoryDTO();
        model.addAttribute("category", category);
        return "category/add";
    }
    @PostMapping("/add")
    public String save(@ModelAttribute("category") CategoryDTO categoryDTO, RedirectAttributes redirectAttributes) {
        categoryService.createCategory(categoryDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Category added successfully!");
        return "redirect:/admin/category/";
    }
    @GetMapping("/update/{categoryId}")
    public String updateCategory(@PathVariable Integer categoryId, Model model) {
        Category category = categoryService.findById(categoryId);
        model.addAttribute("category",category);
        return "/category/update";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("category") CategoryDTO categoryDTO, RedirectAttributes redirectAttributes) {
        categoryService.updateCategory(categoryDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully!");
        return "redirect:/admin/category/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        if(categoryService.delete(id)){
            redirectAttributes.addFlashAttribute("successMessage", "Deleted category successfully!");
            return "redirect:/admin/category/";
        }else{
            return "redirect:/admin/category/";
        }
    }
}

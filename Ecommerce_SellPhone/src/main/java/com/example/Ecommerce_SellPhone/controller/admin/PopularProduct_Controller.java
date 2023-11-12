package com.example.Ecommerce_SellPhone.controller.admin;

import com.example.Ecommerce_SellPhone.DTO.CategoryDTO;
import com.example.Ecommerce_SellPhone.models.PopularProductList;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.service.PopularProductList.PopularProductListService;
import com.example.Ecommerce_SellPhone.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/popular")
public class PopularProduct_Controller {
    @Autowired
    private PopularProductListService popularProductListService;
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String showPopularProductList(Model model) {
        List<PopularProductList> popularProductLists = popularProductListService.getAllPopularProduct();
        model.addAttribute("popularProducts", popularProductLists);
        return "/popular/index";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
        popularProductListService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Delete successfully!");
        return "redirect:/admin/popular/";
    }
}

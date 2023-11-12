package com.example.Ecommerce_SellPhone.controller.admin;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.DTO.ProviderDTO;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.models.PopularProductList;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.models.Provider;
import com.example.Ecommerce_SellPhone.service.Category.CategoryService;
import com.example.Ecommerce_SellPhone.service.Customer.CustomerService;
import com.example.Ecommerce_SellPhone.service.PopularProductList.PopularProductListService;
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
@RequestMapping("admin/product")
public class Product_Controller {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private PopularProductListService popularProductListService;
    @GetMapping("/")
    public String product(Model model){
        PopularProductList popularProductList = new PopularProductList();
        List<ProductDTO> productList = productService.getAllProduct();
        productList.sort(Comparator.comparing(ProductDTO::getId));
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("productList",productList);
        model.addAttribute("popularProductList",popularProductList);
        return "product/index";
    }
    @PostMapping("/popular/{product_id}")
    public String addToPopularList(@PathVariable("product_id") int productId, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(productId);
        if (popularProductListService.create(product)) {
            redirectAttributes.addFlashAttribute("successMessage", "OutStandingProduct added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "The product is already available in featured products");
        }
        return "redirect:/admin/product/";
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        ProductDTO productDTO = new ProductDTO();
        List<Category> categoryList = categoryService.getAllCategory();
        List<Provider> providerList = providerService.getAllProvider();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("providerList", providerList);
        model.addAttribute("productDTO", productDTO);
        return "product/add";
    }
    @PostMapping("/add")
    public String save(@ModelAttribute("productDTO") ProductDTO productDTO, RedirectAttributes redirectAttributes) {
        productService.createProduct(productDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
        return "redirect:/admin/product/";
    }
    @GetMapping("/category")
    public String getProductsByCategory(@RequestParam(name = "categoryId", required = false) Integer categoryId, Model model) {
        List<ProductDTO> productList = productService.getAllProductByCategory(categoryId);
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("productList", productList);
        return "product/index";
    }
    @GetMapping("/update/{product_id}")
    public String update(@PathVariable Integer product_id, Model model){
        ProductDTO productDTO = productService.FindByID(product_id);
        List<Category> categoryList = categoryService.getAllCategory();
        List<Provider> providerList = providerService.getAllProvider();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("providerList", providerList);
        model.addAttribute("product", productDTO);
        return "product/update";
    }
    @PostMapping("/update")
    public String edit(@ModelAttribute("product") ProductDTO productDTO, RedirectAttributes redirectAttributes) {
        productService.updateProduct(productDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");
        return "redirect:/admin/product/";
    }
}

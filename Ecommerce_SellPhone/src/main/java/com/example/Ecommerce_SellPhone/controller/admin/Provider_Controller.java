package com.example.Ecommerce_SellPhone.controller.admin;

import com.example.Ecommerce_SellPhone.DTO.CategoryDTO;
import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.DTO.ProviderDTO;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.models.Provider;
import com.example.Ecommerce_SellPhone.service.Category.CategoryService;
import com.example.Ecommerce_SellPhone.service.Customer.CustomerService;
import com.example.Ecommerce_SellPhone.service.Product.ProductService;
import com.example.Ecommerce_SellPhone.service.Provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("admin/provider")
public class Provider_Controller {
    @Autowired
    private ProviderService providerService;
    @GetMapping("/")
    public String provider(Model model){
        List<Provider> providerList = providerService.getAllProvider();
        providerList.sort(Comparator.comparing(Provider::getId));
        model.addAttribute("providerList", providerList);
        return "provider/index";
    }
    @GetMapping("/add")
    public String addProvider(Model model){
        ProviderDTO providerDTO = new ProviderDTO();
        model.addAttribute("providerDTO", providerDTO);
        return "provider/add";
    }
    @PostMapping("/add")
    public String save(@ModelAttribute("providerDTO") ProviderDTO providerDTO) {
        providerService.createProvider(providerDTO);
        return "redirect:/admin/provider/";
    }
    @GetMapping("/update/{provider_id}")
    public String update(@PathVariable Integer provider_id, Model model){
        Provider provider = providerService.findById(provider_id);
        model.addAttribute("provider", provider);
        return "provider/update";
    }
    @PostMapping("/update")
    public String edit(@ModelAttribute("provider") Provider provider) {
        providerService.updateProvider(provider);
        return "redirect:/admin/provider/";
    }
}

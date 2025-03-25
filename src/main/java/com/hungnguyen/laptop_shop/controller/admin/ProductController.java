package com.hungnguyen.laptop_shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hungnguyen.laptop_shop.domain.Product;

@Controller
public class ProductController {

    @GetMapping("/admin/product")
    public String getProduct(){
        return "admin/product/index";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model){
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }
}

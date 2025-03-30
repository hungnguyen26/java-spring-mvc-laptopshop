package com.hungnguyen.laptop_shop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hungnguyen.laptop_shop.domain.Product;
import com.hungnguyen.laptop_shop.service.ProductService;


@Controller
public class HomePageController {

    private final ProductService productService;

    public HomePageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getHomePage(Model model){
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }
}

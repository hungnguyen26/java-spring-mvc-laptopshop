package com.hungnguyen.laptop_shop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hungnguyen.laptop_shop.domain.Product;
import com.hungnguyen.laptop_shop.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ProductClientController {
    
    private final ProductService productService;

    public ProductClientController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProductDetail(Model model,@PathVariable Long id) {
        Product product = this.productService.getProductById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("id", id);

        return "client/product/detail";
    }
    

    
    
}

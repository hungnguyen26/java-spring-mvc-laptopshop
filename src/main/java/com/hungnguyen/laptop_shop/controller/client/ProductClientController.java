package com.hungnguyen.laptop_shop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hungnguyen.laptop_shop.domain.Product;
import com.hungnguyen.laptop_shop.service.ProductService;


@Controller
public class ProductClientController {
    
    

    @GetMapping("/product/{id}")
    public String getProductDetail(Model model,@PathVariable Long id) {
        return "client/product/detail";
    }
    
}

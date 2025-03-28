package com.hungnguyen.laptop_shop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.hungnguyen.laptop_shop.domain.Product;
import com.hungnguyen.laptop_shop.service.ProductService;
import com.hungnguyen.laptop_shop.service.UploadService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ProductController {

    private final UploadService uploadService;
    private final ProductService productService;

    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model){
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products", products);
        return "admin/product/index";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model){
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String postMethodName(Model model, 
            @ModelAttribute("newProduct") @Valid Product pr,
            BindingResult newProductBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {

        // validdate
        if(newProductBindingResult.hasErrors()){
            return "/admin/product/create";
        }

        String image = this.uploadService.handleSaveUploadFile(file, "product");
        pr.setImage(image);

        this.productService.createProduct(pr);
        return "redirect:/admin/product";
    }
 
    @GetMapping("/admin/product/{id}")
    public String getMethodName(@PathVariable long id, Model model) {
        Product pr = this.productService.getProductById(id).get();
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "admin/product/detail";
    }
    
}

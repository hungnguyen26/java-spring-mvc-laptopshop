package com.hungnguyen.laptop_shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hungnguyen.laptop_shop.domain.Product;
import com.hungnguyen.laptop_shop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product pr){
        return this.productRepository.save(pr);
    }

    public List<Product> getAllProduct(){
        return this.productRepository.findAll();
    }
    
}

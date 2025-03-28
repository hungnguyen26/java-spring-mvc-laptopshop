package com.hungnguyen.laptop_shop.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Product> getProductById(long id){
        return this.productRepository.findById(id);
    }

    public void deleteProductById(long id){
        this.productRepository.deleteById(id);
    }
    
}

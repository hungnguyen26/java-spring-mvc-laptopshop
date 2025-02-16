package com.hungnguyen.laptop_shop;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloController {
    
    @GetMapping("/")
    public String index() {
        return "Hello hunggggaaaa";
    }
    
    @GetMapping("/user")
    public String userPage() {
        return "Only user";
    }
    
    @GetMapping("/admin")
    public String adminPage() {
        return "Only admin";
    }
    
}

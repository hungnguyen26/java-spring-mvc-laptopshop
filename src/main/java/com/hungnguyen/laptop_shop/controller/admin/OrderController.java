package com.hungnguyen.laptop_shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/admin/order")
    public String getDashboard(){
        return "admin/order/index";
    }
}

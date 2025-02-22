package com.hungnguyen.laptop_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hungnguyen.laptop_shop.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = this.userService.hanldeHello();
        model.addAttribute("bin", test);
        return "hello" ;
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        return "admin/user/create" ;
    }
}

// @RestController
// public class UserController {

// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("/")
// public String getHomePage(){
// return this.userService.hanldeHello();
// }
// }
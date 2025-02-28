package com.hungnguyen.laptop_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnguyen.laptop_shop.domain.User;
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
        model.addAttribute("newUser", new User());

        return "admin/user/create" ;
    }

    @RequestMapping(value = "/admin/user/create", method =  RequestMethod.POST)
    @ResponseBody
    public String createUserPage(Model model, @ModelAttribute("newUser") User bin) {
        System.out.println("run here" + bin);
        return "hello" ;
    }
}


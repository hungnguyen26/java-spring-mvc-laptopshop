package com.hungnguyen.laptop_shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungnguyen.laptop_shop.domain.User;
import com.hungnguyen.laptop_shop.repository.UserRepository;
import com.hungnguyen.laptop_shop.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("admin@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("bin", "test");
        model.addAttribute("bin", "from controller");
        return "hello" ;
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = userService.getAllUsers();
        // System.out.println(">>>check user"+ users);
        model.addAttribute("users", users); // truyền dữ liệu qua view
        return "admin/user/table-user" ;
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user); 
        System.out.println(user);
        return "admin/user/user-detail" ;
    }

    @RequestMapping("/admin/user/create")  // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create" ;
    }

    @RequestMapping(value = "/admin/user/create", method =  RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User bin) {
        this.userService.handleSaveUser(bin);
        return "redirect:/admin/user" ;
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUserUpdatePage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser); 
        return "admin/user/user-update" ;
    }

    @PostMapping("/admin/user/update")
    public String postUserUpdate(Model model,@ModelAttribute("newUser") User bin) {
        User currentUser = this.userService.getUserById(bin.getId());
        if(currentUser != null){
            currentUser.setAddress(bin.getAddress());
            currentUser.setFullName(bin.getFullName());
            currentUser.setPhone(bin.getPhone());

            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user" ;
    }
} 


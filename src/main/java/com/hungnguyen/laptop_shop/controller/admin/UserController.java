package com.hungnguyen.laptop_shop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hungnguyen.laptop_shop.domain.User;
import com.hungnguyen.laptop_shop.service.UploadService;
import com.hungnguyen.laptop_shop.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UploadService uploadService, UserService userService, PasswordEncoder passwordEncoder) {
        this.uploadService = uploadService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("admin@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("bin", "test");
        model.addAttribute("bin", "from controller");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = userService.getAllUsers();
        // System.out.println(">>>check user"+ users);
        model.addAttribute("users", users); // truyền dữ liệu qua view
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        // System.out.println(user);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/create") // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String createUserPage(Model model, 
            @ModelAttribute("newUser") @Valid User bin,
            BindingResult newUserBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file
            ) {

        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (">>>>>>>>>" + error.getField() + " - " + error.getDefaultMessage());
        }

        // validdate
        if(newUserBindingResult.hasErrors()){
            return "/admin/user/create";
        }

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(bin.getPassword());

        bin.setAvatar(avatar);
        bin.setPassword(hashPassword);

        bin.setRole(this.userService.getRoleByName(bin.getRole().getName()));

        this.userService.handleSaveUser(bin);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUserUpdatePage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUserUpdate(Model model, @ModelAttribute("newUser") User bin) {
        User currentUser = this.userService.getUserById(bin.getId());
        if (currentUser != null) {
            currentUser.setAddress(bin.getAddress());
            currentUser.setFullName(bin.getFullName());
            currentUser.setPhone(bin.getPhone());

            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getUserDeletePage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postUserDeletePage(Model model, @ModelAttribute("newUser") User bin) {
        this.userService.deleteAUser(bin.getId());
        return "redirect:/admin/user";

    }
}

package com.hungnguyen.laptop_shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hungnguyen.laptop_shop.domain.User;
import com.hungnguyen.laptop_shop.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class DashboardController {

    private final UserService userService;
    

    public DashboardController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin")
    public String getDashboard(Model model){

        model.addAttribute("countUser", this.userService.countUser());
        model.addAttribute("countProduct", this.userService.countProduct());
        model.addAttribute("countOrder", this.userService.countOrder());
        return "admin/dashboard/index";
    }

    // @GetMapping("/order-history")
    // public String getOrderHistoryPage(Model model, HttpServletRequest request) {
    //     User currentUser = new User(); // null
    //     HttpSession session = request.getSession(false);
    //     long id = (long) session.getAttribute("id");
    //     currentUser.setId(id);

    //     List<Order> orders = this.orderService.fetchOrderByUser(currentUser);
    //     model.addAttribute("orders", orders);

    //     return "client/cart/order-history";
    // }

    
}

package com.example.ticketbookingrailwayapplication.controller.rest;

import com.example.ticketbookingrailwayapplication.model.Role;
import com.example.ticketbookingrailwayapplication.model.User;
import com.example.ticketbookingrailwayapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(User user, Model model) {
        User userFromDb = userService.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "User exists! try again");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ROLE_USER));
        userService.registerUser(user);
        model.addAttribute("message", "registered");

        return "redirect:/login";
    }
    @GetMapping("/details")
    public String addDetail(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", user);

        return "details";

    }
    @PostMapping("/details")
    public String addUserDetail(@AuthenticationPrincipal User authUser, User user,Model model) {
        int temp=0;
        if (user.getPassword()!=""){
            temp= userService.addUserDetail(authUser.getId(),user);
        }else {
            temp= userService.addUserDetailNoPass(authUser.getId(),user);
        }

       if (temp==1){
           return "redirect:/hello";
       }else {
           model.addAttribute("message","WRONG!!!");
           return "details";
       }


    }

}

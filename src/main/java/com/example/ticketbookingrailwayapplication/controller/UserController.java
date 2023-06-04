package com.example.ticketbookingrailwayapplication.controller;

import com.example.ticketbookingrailwayapplication.model.Role;
import com.example.ticketbookingrailwayapplication.model.User;
import com.example.ticketbookingrailwayapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;


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
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "User exists! try again");
        return "hello";
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


        return "redirect:/login";
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
//        User updatedUser = userService.updateUser(id, user);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }

//    @DeleteMapping("/{id}")
//    public String deleteUserById(@PathVariable long id) {
//        userService.deleteUser(id);
//        return "user "+ id+" deleted";
//    }


}

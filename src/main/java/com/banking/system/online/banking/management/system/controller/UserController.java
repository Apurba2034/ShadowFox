package com.banking.system.online.banking.management.system.controller;

import org.springframework.web.bind.annotation.*;
import com.banking.system.online.banking.management.system.dto.LoginRequest;
import com.banking.system.online.banking.management.system.dto.RegisterRequest;
import com.banking.system.online.banking.management.system.entity.User;
import com.banking.system.online.banking.management.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest dto){

        return userService.registerUser(dto);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest dto){

        return userService.loginUsers(dto);
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


}

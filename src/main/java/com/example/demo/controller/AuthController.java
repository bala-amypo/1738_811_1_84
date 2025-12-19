package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService ser;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return ser.register(user);
    }

    @GetMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        return ser.login(email, password);
    }
}

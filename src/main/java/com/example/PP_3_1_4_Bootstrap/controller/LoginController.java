package com.example.PP_3_1_4_Bootstrap.controller;

import com.example.PP_3_1_4_Bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public LoginController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    String login() {
        return "login";

    }

}

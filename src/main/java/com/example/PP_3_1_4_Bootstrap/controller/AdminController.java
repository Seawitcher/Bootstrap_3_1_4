package com.example.PP_3_1_4_Bootstrap.controller;


import com.example.PP_3_1_4_Bootstrap.model.User;
import com.example.PP_3_1_4_Bootstrap.service.RoleService;
import com.example.PP_3_1_4_Bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getUser(Model model, Authentication authentication) {
        model.addAttribute("userList", userService.getList());
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);

        model.addAttribute("roleList",roleService.getList());
        model.addAttribute("users", userService.getUser(user.getId()));

        return "admin_section";
    }

    @PostMapping("/newAddUserAdmin")
    public String saveNewUser(
            @ModelAttribute("user") User user
            ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);
        return "redirect:/admin";
    }

    @DeleteMapping("{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @PutMapping("/{id}/editUser")
    public String userSaveEdit(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.editUser(user);
        return "redirect:/admin";
    }

}

package com.example.PP_3_1_4_Bootstrap.controller;

import com.example.PP_3_1_4_Bootstrap.model.Role;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;



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
    public String getUser(Model model, Authentication authentication, Long id) {
        model.addAttribute("userList", userService.getList());
        User admin = (User) authentication.getPrincipal();
        model.addAttribute("user", admin);
        model.addAttribute("roleList",roleService.getList());
//        model.addAttribute("user", userService.getList());

        return "admin_section";
    }

    @GetMapping("/newAddUserAdmin")
    public String addNewUser(Model model) {
      User user = new User();
       model.addAttribute("user", user);

       List<Role> roles = roleService.getList();
        model.addAttribute("roleList", roles);

        return "user_new_admin";
    }

    @PostMapping("/newAddUserAdmin")
    public String saveNewUser(
            @ModelAttribute("user") User user
            ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {

        model.addAttribute("users", userService.getUser(id));
        model.addAttribute("roleList",roleService.getList());
        return "admin_section";
    }


    @PatchMapping("/editUser/{id}")
    public String userSaveEdit(User user) {
        userService.editUser(user);
        return "redirect:/admin";
    }

//    @PatchMapping("/{id}")
//    public String userSaveEdit(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
//        userService.editUser(user);
//        return "redirect:/admin";
//    }
//    @PatchMapping("/{id}")
//    public String userSaveEdit(@ModelAttribute("user") User user, @ModelAttribute("roles") String roles) {
//        userService.editUser(user, roles);
//        return "redirect:/admin";
//    }
}

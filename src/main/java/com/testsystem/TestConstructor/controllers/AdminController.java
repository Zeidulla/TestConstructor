package com.testsystem.TestConstructor.controllers;

import com.testsystem.TestConstructor.models.User;
import com.testsystem.TestConstructor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService; //без аннотации было бы: UserService userService = new UserService();

    @GetMapping("/admin")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users",  users);
        return "admin";
    }
    @GetMapping("/delete-user/{id}")
    public String delUser(@PathVariable Long id) {
        userService.deleteUsrById(id);
        return "redirect:/admin";
    }
}
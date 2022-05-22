package com.testsystem.TestConstructor.controllers;

import com.testsystem.TestConstructor.models.Test;
import com.testsystem.TestConstructor.models.User;
import com.testsystem.TestConstructor.repository.TestRepository;
import com.testsystem.TestConstructor.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {


    @Autowired
    TestRepository testRepository;


    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public String home(Model model) {
        List<Test> testList = testRepository.findAll();
        model.addAttribute("tests", testList);
        return "home";
    }

    @GetMapping("/find")
    public String find(Model model) {
        List<Test> testList = testRepository.findAll();
        model.addAttribute("tests", testList);
        return "find";
    }

    @GetMapping("/timer")
    public String timer(Model model) {
        List<Test> testList = testRepository.findAll();
        model.addAttribute("tests", testList);
        return "timer";
    }

    @GetMapping("/helper")
    public String helper(Model model) {
        return "helper";
    }


}

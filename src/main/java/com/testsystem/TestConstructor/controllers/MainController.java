package com.testsystem.TestConstructor.controllers;

import com.testsystem.TestConstructor.models.Test;
import com.testsystem.TestConstructor.models.User;
import com.testsystem.TestConstructor.repository.TestRepository;
import com.testsystem.TestConstructor.repository.UserRepository;
import com.testsystem.TestConstructor.service.EmailService;
import com.testsystem.TestConstructor.service.TestService;
import com.testsystem.TestConstructor.service.UserService;
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
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestRepository testRepository;
    @Autowired
    TestService testService;

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

    @GetMapping("/helper")
    public String helper(Model model) {
        return "helper";
    }

    @GetMapping("/timer")
    public String timer(Model model) {
        return "timer";
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@RequestParam String email, @RequestParam String username, @RequestParam String password,@RequestParam String role, Model model){
        int errorCheck = userService.registerUser(username,password,email,role);
        switch (errorCheck){
            case(-1): model.addAttribute("error", "E-mail занят!");
                break;
            case(-2): model.addAttribute("error", "Имя пользовтеля занято!");
                break;
            default:
                model.addAttribute("error", "Успешная регистрация! Письмо с инструкцией активации отправлено!");
        }
        return("register");
    }
    @GetMapping("/confirmRegistration/{activationCode}")
    public String confirmRegistration( @PathVariable String activationCode, Model model) {
        if(userService.confirmRegistration(activationCode)){
            model.addAttribute("msg", "Аккаунт успешно активирован");
        }
        else
        {
            model.addAttribute("msg", "Ошибка активации");
        }
        return "register";
    }
    @GetMapping("/recoverPass")
    public String recoverPassGet() {
        return "recoverPass";
    }
    @PostMapping("/recoverPass")
    public String recoverPassGet(@RequestParam String email, Model model) {
        if(userService.recoverPassword(email)) model.addAttribute("rec", "Ссылка для смены пароля отпралена на e-mail");
        else model.addAttribute("rec", "Аккаунт не существует");
        return "recoverPass";
    }
    @GetMapping("recoverPassword/{acrivationCode}")
    public String recoverGet(@PathVariable String acrivationCode, Model model) {
        if(!userService.findByAcrivationCode(acrivationCode)) model.addAttribute("error", "Ошибка восстановления");
        model.addAttribute("code", acrivationCode );
        return "recoverPassword";
    }
    @PostMapping("/recoverPassword/{acrivationCode}")
    public String recoverPost(@PathVariable String acrivationCode, @RequestParam String password, Model model){
        userService.recPassword(acrivationCode,password);
        model.addAttribute("recover", "Пароль восстановлен");
        return("login");
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails u, Model model) {
        User user = userRepository.findByUsername(u.getUsername());
        model.addAttribute("user", user);
        return ("profile");
    }

    @GetMapping("/changePassword")
    public String getChangePass(){
        return "changePassword";
    }
    @PostMapping("/changePassword")
    public String postChangePass(@AuthenticationPrincipal UserDetails u, @RequestParam String password){
        userService.changePass(u.getUsername(),password);
        return "redirect:/profile";
    }
    @GetMapping("/makeTest/{id}")
    public String getMakeTest(@PathVariable Long id, Model model)
    {
        Test test = testRepository.findById(id).get();
        model.addAttribute("test", test);
        return "makeTest";
    }
    @PostMapping("/makeTest/{id}")
    public String getMakeTest(@AuthenticationPrincipal UserDetails u,@PathVariable Long id, @RequestParam("answers") List<Long> answers , Model model)
    {
        Test test = testRepository.findById(id).get();
        int countQuests = test.getQuestion().size();
        int result= testService.getResult(id,answers);
        testService.newResult(u.getUsername(),id, (long) result);
        emailService.sendTestResult(u.getUsername(),test,result,countQuests);
        model.addAttribute("count", countQuests);
        model.addAttribute("result", result);
        return "result";
    }
    @GetMapping("/results")
    public String userResult(@AuthenticationPrincipal UserDetails user, Model model){
        User u = userRepository.findByUsername(user.getUsername());
        model.addAttribute("results", u.getResult());
        return "userResult";
    }
}
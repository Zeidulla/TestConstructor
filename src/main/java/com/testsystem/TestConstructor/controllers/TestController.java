package com.testsystem.TestConstructor.controllers;

import com.testsystem.TestConstructor.models.Question;
import com.testsystem.TestConstructor.models.Test;
import com.testsystem.TestConstructor.models.User;
import com.testsystem.TestConstructor.repository.QuestionRepository;
import com.testsystem.TestConstructor.repository.TestRepository;
import com.testsystem.TestConstructor.repository.UserRepository;
import com.testsystem.TestConstructor.service.EmailService;
import com.testsystem.TestConstructor.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    EmailService emailService;

    @Autowired
    TestRepository testRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/new")
    public String newTest(){
        return "newTest";
    }

    @PostMapping("/new")
    public String addNewTest(@AuthenticationPrincipal UserDetails u, @RequestParam String name, @RequestParam String description){
        Long testId=testService.addNewTest(u.getUsername(), name, description);
        return "redirect:/test/edit/"+testId;
    }

    @GetMapping("/edit/{id}")
    public String newTest(@AuthenticationPrincipal UserDetails user,@PathVariable Long id, Model model){
        if(!testService.isOwner(id, user.getUsername())) return "error/403";
        model.addAttribute("test", testRepository.findById(id).get());
        return "editTest";
    }

    @GetMapping("/edit/newQuestion/{id}")
    public String getNewQuest(@AuthenticationPrincipal UserDetails user,@PathVariable Long id, Model model){
        if(!testService.isOwner(id, user.getUsername())) return "error/403";
        model.addAttribute("testid", id);
        return "newQuestion";
    }
    @PostMapping("/edit/newQuestion/{id}")
    public String postNewQuest(@PathVariable Long id, @RequestParam("answer") List<String> answers, @RequestParam String question, @RequestParam Long trueAns){
        testService.addQuestion(id,question,answers,trueAns);
        return "redirect:/test/edit/"+id;
    }
    @GetMapping("/edit/deleteQuestion/{id}")
    public String deleteQuestion(@AuthenticationPrincipal UserDetails user,@PathVariable Long id)
    {
        if(!testService.isOwner(user.getUsername(),id)) return "error/403";
        Question quest = questionRepository.findById(id).get();
        Test test = quest.getTest();
        testService.deleteQuest(id);
        return "redirect:/test/edit/"+test.getId();
    }
    @GetMapping("/list")
    public String testList(@AuthenticationPrincipal UserDetails u, Model model)
    {
        User user = userRepository.findByUsername(u.getUsername());
        model.addAttribute("user", user);
        return "testList";
    }
    @GetMapping("/deleteTest/{id}")
    public String deleteTest(@AuthenticationPrincipal UserDetails user,@PathVariable Long id)
    {
        if(!testService.isOwner(id, user.getUsername())) return "error/403";
        testService.deleteTest(id);
        return "redirect:/test/list";
    }
    @GetMapping("/edit/publish/{id}")
    public String setPublish(@AuthenticationPrincipal UserDetails user,@PathVariable Long id){
        if(!testService.isOwner(id, user.getUsername())) return "error/403";
        testService.setPublic(id);
        return "redirect:/test/edit/"+id;
    }
    @GetMapping("/results/{id}")
    public String testResults(@AuthenticationPrincipal UserDetails user,@PathVariable Long id, Model model){
        if(!testService.isOwner(id, user.getUsername())) return "error/403";
        model.addAttribute("results", testRepository.findById(id).get().getResult());
        return "testResult";
    }
}

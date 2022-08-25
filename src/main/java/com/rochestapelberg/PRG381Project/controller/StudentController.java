package com.rochestapelberg.PRG381Project.controller;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.rochestapelberg.PRG381Project.Model.User;
import com.rochestapelberg.PRG381Project.auth.ApplicationUserService;

@Controller
@RequestMapping("student/api/")
public class StudentController {
    
    @Autowired
    private ApplicationUserService service;

    @GetMapping()
    public ModelAndView viewHomePage(@NotNull Model model, @AuthenticationPrincipal UserDetails currentUser){
        Optional<User> student = service.getStudentByUsername(currentUser.getUsername());
        ModelAndView mav = new ModelAndView("studentView");
        mav.addObject("student", student);
        return mav;
    }

    public String currentUserName(@AuthenticationPrincipal(expression = "username") String username) {
        return username;
    }
}

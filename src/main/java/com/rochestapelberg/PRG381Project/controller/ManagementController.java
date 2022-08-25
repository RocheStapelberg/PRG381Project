package com.rochestapelberg.PRG381Project.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.rochestapelberg.PRG381Project.Model.User;
import com.rochestapelberg.PRG381Project.auth.ApplicationUserService;

import java.util.List;

@Controller
@RequestMapping("admin/api/student/")
public class ManagementController {

    @Autowired
    private ApplicationUserService service;

    @GetMapping()
    public String viewHomePage(@NotNull Model model){
        List<User> studentList = service.loadStudents();
        model.addAttribute("studentList", studentList);
        return "adminView";
    }

    @GetMapping("/new")
    public String insert(Model model) {
        model.addAttribute("student", new User());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") User student) {
            student.setActive(true);
            student.setRoles("ROLE_STUDENT");
            service.saveStudent(student);
            return "redirect:/admin/api/student/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        User student = service.getStudentById(id);
        mav.addObject("student", student);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(name = "id") int id) {
        service.deleteStudent(id);
        return "redirect:/admin/api/student/";
    }
}

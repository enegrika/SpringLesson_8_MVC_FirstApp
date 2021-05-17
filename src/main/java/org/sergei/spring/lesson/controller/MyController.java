package org.sergei.spring.lesson.controller;

import org.sergei.spring.lesson.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/my")
    public String inde(Model model) {

        Person p = new Person(25, "Robert");
        model.addAttribute("p", p);
        return "myHtmlFile";
    }
}

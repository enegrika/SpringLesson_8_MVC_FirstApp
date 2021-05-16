package org.sergei.spring.lesson.controller;

import org.sergei.spring.lesson.DAO.PersonDAO;
import org.sergei.spring.lesson.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    private final PersonDAO personDAO;

    @Autowired
    public MyController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/my")
    public String anyMethod(Model model) {

        Person p1 = new Person(23, "Example Name",25,"example@dsfsd.com");

        model.addAttribute("obj", 2363246);
        model.addAttribute("person2", personDAO.getPersonById(2));
        model.addAttribute("personList",personDAO.getAllpersonsList());
        model.addAttribute("string","anyString.....");
        return "myhtml";
    }

}

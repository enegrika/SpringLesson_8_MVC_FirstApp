package org.sergei.spring.lesson.controller;

import org.sergei.spring.lesson.DAO.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String showAllpersonsList(Model model) {
        // get all persons from DAO and send it to Views

        model.addAttribute("personsList", personDAO.getAllpersonsList());
        return "persons/listPersons";
    }

    @GetMapping("/{id}") //in GET request в запросе от браузера если будем передавать id,
    // то с помощью аннотации PathVariable мы можем передать это число в наш метод шоуперсон по айди здесь же внедряем сущность модель для
    public String showPerson(@PathVariable("id") int id, Model model) {
        // get ONE person by ID from DAO and send it to Views

        model.addAttribute("person", personDAO.getPersonById(id));
        return "persons/showPerson";
    }


}

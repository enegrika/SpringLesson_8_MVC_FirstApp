package org.sergei.spring.lesson.controller;

import org.sergei.spring.lesson.DAO.PersonDAO;
import org.sergei.spring.lesson.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("personNew") Person person) {
//        эту строчку можно заменить аннотацией @ModelAttribute - которая кладет в Модель передаваемый объект Персона
//       public String newPerson(Model model) - model.addAttribute("personNew", new Person());
        return "/persons/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("personNew") Person person) {

        personDAO.save(person);

        return "redirect:/persons";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("personEdit", personDAO.getPersonById(id));
        return "persons/editPerson";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("person") Person person,
                       @PathVariable("id") int id,
                       Model model) {
        personDAO.edit(id, person);

        return "redirect:/persons/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id,
                         Model model) {
        personDAO.delete(id);
        return "redirect:/persons";
    }

}

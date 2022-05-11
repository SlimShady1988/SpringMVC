package org.springmvc.controllers;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springmvc.model.Person;
import org.springmvc.repository.PersonRepository;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonRepository personRepository;

    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/list")
    public String getPeople (Model model) throws SQLException {
        model.addAttribute("people", personRepository.getPeople());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String getPerson(Model model,  @PathVariable int id) throws ServletException, IOException, SQLException {
        model.addAttribute("person", personRepository.getPerson(id));

        return "people/person";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable int id) throws SQLException {
        model.addAttribute("person", personRepository.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@Valid @ModelAttribute("person")   Person person, BindingResult bindingResult, @PathVariable int id) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personRepository.updatePerson(person, id);

        return "redirect:/people/list";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());

        return "people/new";
    }

    @PostMapping()
    public String createPerson(@Valid @ModelAttribute("person")  Person person, BindingResult bindingResult) throws SQLException {
    if (bindingResult.hasErrors()) {
        System.out.println(bindingResult);
        return "people/new";
    }
        personRepository.createPerson(person);

        return "redirect:/people/list";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) throws SQLException {
        personRepository.deletePerson(id);

        return "redirect:/people/list";
    }
}

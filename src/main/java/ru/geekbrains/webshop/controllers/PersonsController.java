package ru.geekbrains.webshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.webshop.dto.PersonDto;
import ru.geekbrains.webshop.entity.Person;
import ru.geekbrains.webshop.services.personService.PersonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/persons")
public class PersonsController {
    private final PersonService personService;

    public PersonsController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/login")
    public String showMyLoginPage(){
        return "/login";
    }

    @GetMapping
    public String showAllPersons(Model model){
        model.addAttribute("persons", personService.findAll());
        return "persons/showPersons";
    }
    @GetMapping("/{id}")
    public String showPersonById(@PathVariable Long id, Model model){
        model.addAttribute("person", personService.findById(id));
        return "persons/idPerson";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "persons/newPerson";
    }

    @PostMapping
    public String addPerson(@ModelAttribute("person") @Valid PersonDto personDto, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "persons/newPerson";
        personService.createPerson(personDto);
        return "redirect:/persons";
    }
    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") Long id){
        model.addAttribute("person", personService.findById(id));
        return "persons/editPerson";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid PersonDto personDto,BindingResult bindingResult, @PathVariable Long id){
        if (bindingResult.hasErrors())
            return "persons/editPerson";
        personService.updatePerson(personDto, id);
        return "redirect:/persons";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        return "redirect:/persons";
    }
}

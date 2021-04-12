package ru.geekbrains.webshop.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.webshop.services.orderService.OrderService;
import ru.geekbrains.webshop.services.personService.PersonService;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrderService orderService;
    private final PersonService personService;


    public OrdersController(OrderService orderService, PersonService personService) {
        this.orderService = orderService;
        this.personService = personService;
    }

    @GetMapping("/{login}")
    public String showOrderByPerson(@PathVariable String login, Model model){
        model.addAttribute("order",orderService.showOrdersPerson(personService.findByLogin(login)));
        return "orders/showOrders";
    }

}

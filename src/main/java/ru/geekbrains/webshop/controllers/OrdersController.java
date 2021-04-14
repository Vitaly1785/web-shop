package ru.geekbrains.webshop.controllers;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.webshop.dto.OrderDto;
import ru.geekbrains.webshop.entity.Order;
import ru.geekbrains.webshop.services.orderService.OrderService;
import ru.geekbrains.webshop.services.personService.PersonService;
import ru.geekbrains.webshop.services.productService.ProductService;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrderService orderService;
    private final PersonService personService;
    private final ProductService productService;


    public OrdersController(OrderService orderService, PersonService personService, ProductService productService) {
        this.orderService = orderService;
        this.personService = personService;
        this.productService = productService;
    }

    @GetMapping("/{login}")
    public String showOrderByPerson(@PathVariable String login, Model model){
        model.addAttribute("order",orderService.showOrdersPerson(personService.findByLogin(login)));
        return "orders/showOrders";
    }
    @GetMapping("/newOrder")
    public String newOrder(Model model) {
        model.addAttribute("orders", new Order());
        return "orders/newOrder";
    }
    @PostMapping
    public String addProductToOrder(@ModelAttribute OrderDto orderDto){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        orderService.createOrder(personService.findByLogin(username), orderDto);
        return "redirect:/";
    }
}

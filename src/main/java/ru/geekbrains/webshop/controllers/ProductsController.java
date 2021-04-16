package ru.geekbrains.webshop.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.webshop.dto.OrderDto;
import ru.geekbrains.webshop.dto.ProductDto;
import ru.geekbrains.webshop.entity.Order;
import ru.geekbrains.webshop.entity.Product;
import ru.geekbrains.webshop.services.orderService.OrderService;
import ru.geekbrains.webshop.services.personService.PersonService;
import ru.geekbrains.webshop.services.productService.ProductService;


@Controller
@RequestMapping("/")
public class ProductsController {
    private final ProductService productService;
    private final PersonService personService;
    private final OrderService orderService;

    private String sortMethod = "ASC";

    public ProductsController(ProductService productService, PersonService personService, OrderService orderService) {
        this.productService = productService;
        this.personService = personService;
        this.orderService = orderService;
    }

    @GetMapping
    public String showAll(@PageableDefault Pageable pageable,
                          Model model) {
        Page<Product> page = sortProduct(pageable);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        model.addAttribute("person", username);
        model.addAttribute("page", page);
        return "products/show";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "products/id";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "products/new";
    }

    @PostMapping
    public String addProduct(@ModelAttribute("product") ProductDto productDto) {
        productService.createProduct(productDto);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.findById(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String updateProductById(@ModelAttribute("product") Product product, @PathVariable Long id) {
        productService.updateProductById(product, id);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @PostMapping("/{sorted}")
    public String getSortMethod(@PathVariable("sorted") String sorted) {
        sortMethod = sorted;
        return "redirect:/";
    }

    public Page<Product> sortProduct(Pageable pageable) {
        Page<Product> products = null;

        switch (sortMethod) {
            case "ASC":
                products = productService.getProductMaxPrice(pageable);
                break;
            case "DESC":
                products = productService.getProductsMinPrice(pageable);
                break;
            default:
                products = productService.showAll(pageable);
                break;
        }
        return products;
    }
}


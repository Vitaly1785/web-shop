package ru.geekbrains.webshop.services.orderService;

import ru.geekbrains.webshop.entity.Order;
import ru.geekbrains.webshop.entity.Person;

public interface OrderService {
    Iterable<Order> showOrdersPerson(Person person);
    Integer addProductQuantity(Long productId, Integer quantity, Person person);
}

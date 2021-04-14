package ru.geekbrains.webshop.services.orderService;

import ru.geekbrains.webshop.dto.OrderDto;
import ru.geekbrains.webshop.entity.Order;
import ru.geekbrains.webshop.entity.Person;
import ru.geekbrains.webshop.entity.Product;

public interface OrderService {
    Iterable<Order> showOrdersPerson(Person person);
    Order createOrder(Person person, OrderDto orderDto);
}

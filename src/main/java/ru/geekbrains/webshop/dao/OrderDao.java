package ru.geekbrains.webshop.dao;

import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.webshop.entity.Order;
import ru.geekbrains.webshop.entity.Person;
import ru.geekbrains.webshop.entity.Product;


public interface OrderDao extends CrudRepository<Order, Long> {
    Iterable<Order> findByPerson(Person person);
    Order findByPersonAndProduct(Person person, Product product);
}

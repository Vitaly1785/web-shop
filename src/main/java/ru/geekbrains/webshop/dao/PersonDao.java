package ru.geekbrains.webshop.dao;

import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.webshop.entity.Person;

import java.util.Optional;

public interface PersonDao extends CrudRepository<Person, Long> {
    Optional<Person> findByLogin(String login);
}

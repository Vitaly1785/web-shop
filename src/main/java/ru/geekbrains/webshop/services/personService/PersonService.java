package ru.geekbrains.webshop.services.personService;

import ru.geekbrains.webshop.dto.PersonDto;
import ru.geekbrains.webshop.entity.Person;
import java.util.List;



public interface PersonService {
    Iterable<Person> findAll();

    Iterable<Person> findAllById(List<Long> ids);

    Person findById(Long id);

    Person createPerson(PersonDto personDto);

    Person updatePerson(PersonDto personDto, Long id);

    void deletePerson(Long id);

    Person findByLogin(String login);
}

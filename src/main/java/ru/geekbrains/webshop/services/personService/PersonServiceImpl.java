package ru.geekbrains.webshop.services.personService;

import org.springframework.stereotype.Service;
import ru.geekbrains.webshop.configs.security.UserService;
import ru.geekbrains.webshop.dao.PersonDao;
import ru.geekbrains.webshop.dao.RoleDao;
import ru.geekbrains.webshop.dto.PersonDto;
import ru.geekbrains.webshop.entity.Person;
import ru.geekbrains.webshop.exceptions.PersonNotFoundException;
import ru.geekbrains.webshop.exceptions.RoleNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonDao personDao;
    private final RoleDao roleDao;
    private final UserService userService;

    public PersonServiceImpl(PersonDao personDao, RoleDao roleDao, UserService userService) {
        this.personDao = personDao;
        this.roleDao = roleDao;
        this.userService = userService;
    }

    @Override
    public Iterable<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public Iterable<Person> findAllById(List<Long> ids) {
        return personDao.findAllById(ids);
    }

    @Override
    public Person findById(Long id) {
        return personDao.findById(id).orElseThrow(() -> new PersonNotFoundException(String.format("Person with id %s not found", id)));
    }

    @Override
    public Person createPerson(PersonDto personDto) {
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setEmail(personDto.getEmail());
        person.setPhoneNumber(personDto.getPhoneNumber());
        Optional<Person> optionalPerson = personDao.findByLogin(personDto.getLogin());
        if (optionalPerson.isPresent()) {
            throw new PersonNotFoundException("Person with this Login already exist");
        }
        person.setLogin(personDto.getLogin());
        person.setPassword(personDto.getPassword());
        personDao.save(person);
        return person;
    }

    @Override
    public Person updatePerson(PersonDto personDto, Long id) {
        Person person = findById(id);
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setEmail(personDto.getEmail());
        person.setPhoneNumber(personDto.getPhoneNumber());
        person.setLogin(personDto.getLogin());
        person.setPassword(personDto.getPassword());
        person.setRole(roleDao.findByName(personDto.getRole()).orElseThrow(() -> new RoleNotFoundException("Role not found")));
        personDao.save(person);
        return person;
    }

    @Override
    public void deletePerson(Long id) {
        personDao.delete(personDao.findById(id).orElseThrow(()-> new PersonNotFoundException(String.format("Person was not deleted. Person with id %s not found", id))));
    }

    @Override
    public Person findByLogin(String login) {
        Person person = personDao.findByLogin(login).orElseThrow(()->new PersonNotFoundException("Person not found"));
        return person;
    }


}

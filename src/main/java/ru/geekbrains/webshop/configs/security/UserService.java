package ru.geekbrains.webshop.configs.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.webshop.dao.PersonDao;
import ru.geekbrains.webshop.entity.Person;


import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final PersonDao personDao;

    public UserService(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> personOptional = personDao.findByLogin(s);
        //оченю сожалею за "!" в условии, но в Java 8 нет метода isEmpty)
        if (!personOptional.isPresent()) {
            throw new UsernameNotFoundException("user with this username was not found");
        }
        Person person = personOptional.get();
        //и List.of тоже
        return new User(person.getLogin(), person.getPassword(), Collections.singleton(new SimpleGrantedAuthority(person.getRole().getName())));
    }
}

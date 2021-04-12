package ru.geekbrains.webshop.dao;

import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.webshop.entity.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleDao extends CrudRepository<Role, UUID> {
    Optional<Role> findByName(String name);
}

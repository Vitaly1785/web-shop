package ru.geekbrains.webshop.exceptions;

public class PersonNotFoundException extends NotFoundException{
    public PersonNotFoundException(String message) {
        super(message);
    }
}

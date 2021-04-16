package ru.geekbrains.webshop.exceptions;

public class OrderNotFoundException extends NotFoundException{
    public OrderNotFoundException(String message) {
        super(message);
    }
}

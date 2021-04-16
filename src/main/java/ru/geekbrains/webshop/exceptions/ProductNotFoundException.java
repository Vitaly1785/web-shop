package ru.geekbrains.webshop.exceptions;

public class ProductNotFoundException extends NotFoundException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}

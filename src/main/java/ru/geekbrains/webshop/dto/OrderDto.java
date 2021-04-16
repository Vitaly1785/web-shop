package ru.geekbrains.webshop.dto;

import ru.geekbrains.webshop.entity.Person;
import ru.geekbrains.webshop.entity.Product;

public class OrderDto {
    private Product product;
    private Person person;
    private int quantity;

    public OrderDto() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

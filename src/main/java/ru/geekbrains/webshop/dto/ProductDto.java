package ru.geekbrains.webshop.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

public class ProductDto {
    @NotBlank(message = "the \"title\" field should not be empty")
    private String title;
    @DecimalMin(value = "1")
    private int price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

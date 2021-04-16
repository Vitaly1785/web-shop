package ru.geekbrains.webshop.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonDto {
    private Long id;
    @NotBlank(message = "the \"firstName\" field should not be empty")
    private String firstName;
    @NotBlank(message = "the \"lastName\" field should not be empty")
    private String lastName;
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @NotEmpty(message = "this is a required field")
    @NumberFormat
    private String phoneNumber;
    @NotBlank(message = "this is a required field")
    private String login;
    @NotBlank(message = "this is a required field")
    private String password;
    private String role;

    public PersonDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

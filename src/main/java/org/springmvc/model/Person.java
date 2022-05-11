package org.springmvc.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @Min(value = 3, message = "age must be")
    private int age;

    @NotEmpty(message = "should not be empty")
    @Email(message = "should be valid email")
    private String email;

    @NotEmpty(message = "should not be empty")
    @Size(min = 2, max = 30, message = "should not 0 symbols")
    private String name;

    private String surname;

    public Person() {}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person(int id, int age, String email, String name) {
        this.id = id;
        this.age = age;
        this.email = email;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

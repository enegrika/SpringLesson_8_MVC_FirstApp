package org.sergei.spring.lesson.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;

    // ANNOTATIONS for CHECK VALIDITY for OUR FIELDS
    @NotEmpty(message = "name shouldn't be empty")
    @Size(min = 2,max = 30,message = "Name should be between 2 and 30 cheracters")
    private String name;

    @Min(value = 0, message = "Age should be greater than zero")
    private int age;

    @NotEmpty(message = "email shouldn't be empty")
    @Email(message = "email should be valid")
    private String email;

    public Person() {
    }

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

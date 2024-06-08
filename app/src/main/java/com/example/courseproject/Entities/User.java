package com.example.courseproject.Entities;
import com.example.courseproject.Managers.CartManager;

import java.util.HashMap;
public class User {
    private String name;
    private String surname;
    private String role;
    public User() {}
    public User(String name, String surname, String role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
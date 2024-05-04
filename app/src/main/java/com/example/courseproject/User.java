package com.example.courseproject;

import java.util.HashMap;

public class User {
    private int id;
    private String username;
    private String password;
    HashMap<Product, Integer> cart;
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cart = new HashMap<>();
    }
    public void add(Product product) {
        cart.merge(product, 1, Integer::sum);
    }
    public int getQuantity(Product product) {return (cart.get(product) != null) ? cart.get(product)  : 0;}
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
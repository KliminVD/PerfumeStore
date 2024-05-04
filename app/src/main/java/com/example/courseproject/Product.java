package com.example.courseproject;

public class Product {
    private int id;
    private String name;
    private double price;
    private String url;
    public Product() {}
    public Product(int id, String name, double price, String url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.url = url;
    }
    public int getId() {
        return id;
    }
    public String getName() {return name;}
    public double getPrice() {
        return price;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setUrl(String url) {this.url = url;}
    public String getUrl() {return url;}
}
package com.example.courseproject.Entities;

public class Product {
    private String id;
    private String name;
    private double price;
    private String image;
    private int quantity;
    public Product() {}
    public Product(String id, String name, double price, String image, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }
    public String getId() {
        return id;
    }
    public String getName() {return name;}
    public double getPrice() {
        return price;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public int getQuantity() {return quantity;}
    public void setImage(String image) {this.image = image;}
    public String getImage() {return image;}
}
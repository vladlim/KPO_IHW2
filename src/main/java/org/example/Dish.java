package org.example;

public class Dish{
    private String name;
    private double price;
    private int cookingTime;

    public Dish(String name, double price, int cookingTime) {
        this.name = name;
        this.price = price;
        this.cookingTime = cookingTime;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCookingTime() {
        return cookingTime;
    }

}

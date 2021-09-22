package com.example.springsocial.payload;
import javax.validation.constraints.NotBlank;

public class MenuRequest {
    @NotBlank
    private String restaurantID;

    @NotBlank
    private String dishID;

    @NotBlank
    private String dishName;

    @NotBlank
    private String dishPointer;

    @NotBlank
    private double costPerDish;

    public String getRestaurantID() { return restaurantID; }

    public void setRestaurantID(String restaurantID) { this.restaurantID = restaurantID; }

    public String getDishID() { return dishID; }

    public void setDishID(String dishID) { this.dishID = dishID; }

    public String getDishName() { return dishName; }

    public void setDishName(String dishName) { this.dishName = dishName; }

    public String getDishPointer() { return dishPointer; }

    public void setDishPointer(String dishPointer) { this.dishPointer = dishPointer; }

    public double getCostPerDish() { return costPerDish; }

    public void setCostPerDish(double costPerDish) { this.costPerDish = costPerDish; }

}
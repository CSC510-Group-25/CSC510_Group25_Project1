package com.example.springsocial.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Menu{

    @Column(nullable = false, name = "restaurant_id")
    private String restaurantId;

    @Id
    @Column(nullable = false, name = "dish_id")
    private String dishId;

    @Column(nullable = false, name = "dish_name")
    private String dishName;

    @Column(nullable = false, name = "dish_pointer")
    private String dishPointer;

    @Column(nullable = false, name = "cost_per_dish")
    private double costPerDish;

    public String getRestaurantId() { return restaurantId; }

    public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }

    public String getDishId() { return dishId; }

    public void setDishId(String dishId) { this.dishId = dishId; }

    public String getDishName() { return dishName; }

    public void setDishName(String dishName) { this.dishName = dishName; }

    public String getDishPointer() { return dishPointer; }

    public void setDishPointer(String dishPointer) { this.dishPointer = dishPointer; }

    public double getCostPerDish() { return costPerDish; }

    public void setCostPerDish(double costPerDish) { this.costPerDish = costPerDish; }

}
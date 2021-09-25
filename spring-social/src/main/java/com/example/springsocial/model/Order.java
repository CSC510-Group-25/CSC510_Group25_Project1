package com.example.springsocial.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")

public class Order {

    @Column(nullable = false, name = "restaurant_id")
    private String restaurantId;

    @Id
    @Column(nullable = false,  name = "order_id")
    private String orderId;

    @Column(nullable = false, name = "dish_id")
    private String dishId;

    @Column(nullable = false, name = "dish_name")
    private String dishName;

    @Column(nullable = false, name = "date")
    private Date date;

    @Column(nullable = false, name = "order_qty")
    private int orderQuantity;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }


}

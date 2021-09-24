package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

@Entity
@Table(name = "inventory")

public class Inventory {

    @Column(nullable = false, name = "restaurant_name")
    private String restaurantName;


    @Column(nullable = false, name = "restaurant_id")
    private String restaurantID;

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(nullable = false, name = "item_id")
    private String itemID;

    @Column(nullable = false, name = "item_name")
    private String itemName;


    @Column(nullable = false, name = "batch_id")
    private String batchID;

    @Column(nullable = false, name = "batch_qty")
    private int batchQty;

    @Column(nullable = false, name = "cost_per_item")
    private double costPerItem;

    @Column(name = "date_bought")
    private Date dateBought;

    @Column(name = "date_expired")
    private Date dateExpired;

    public String getRestaurantName(){
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName){
        this.restaurantName = restaurantName;
    }

    public String getRestaurantID(){
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID){
        this.restaurantID = restaurantID;
    }

    public String getItemID(){
        return itemID;
    }

    public void setItemID(String itemID){
        this.itemID = itemID;
    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public String getBatchID(){
        return batchID;
    }

    public void setBatchID(String batchID){
        this.batchID = batchID;
    }

    public int getBatchQty(){
        return batchQty;
    }

    public void setBatchQty(int batchQty){
        this.batchQty = batchQty;
    }

    public double getCostPerItem(){
        return costPerItem;
    }

    public void setCostPerItem(double costPerItem){
        this.costPerItem = costPerItem;
    }

    public Date getDateBought(){
        return dateBought;
    }

    public void setDateBought(Date dateBought){
        this.dateBought = dateBought;
    }

    public Date getDateExpired(){
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired){
        this.dateExpired = dateExpired;
    }
}
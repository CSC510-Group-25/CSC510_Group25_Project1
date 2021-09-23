package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;


import javax.persistence.*;

@Entity
@Table(name = "inventory")

public class Inventory {

    @Column(nullable = false, name = "restaurantName")
    private String restaurantName;


    @Column(nullable = false, name = "restaurantID")
    private String restaurantID;

    @Id
    @Column(nullable = false, name = "itemID")
    private String itemID;

    @Column(nullable = false, name = "itemName")
    private String itemName;


    @Column(nullable = false, name = "batchID")
    private String batchID;

    @Column(nullable = false, name = "batchQty")
    private int batchQty;

    @Column(nullable = false, name = "costPerItem")
    private double costPerItem;

    @Column(name = "dateBought")
    private Date dateBought;

    @Column(name = "dateExpired")
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

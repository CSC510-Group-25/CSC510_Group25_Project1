package com.example.springsocial.payload;

import java.util.Date;
import javax.validation.constraints.NotBlank;

/**
    *The payload contains the data that could be stored or modified
    */
public class InventoryRequest {
    @NotBlank
    private String restaurantName;

    @NotBlank
    private String restaurantID;

    @NotBlank
    private String itemID;

    @NotBlank
    private String itemName;

    @NotBlank
    private String batchID;

    private int batchQty;


    private double costPerItem;


    private Date dateBought;


    private Date dateExpired;

    /**
    * This method gets the name of the restaurant
     * @return returns the name of the restaurant as a String
     */
    public String getRestaurantName(){
        return restaurantName;
    }
    /**
     * This method sets the name of the restaurant
     * @param restaurantName
     */
    public void setRestaurantName(String restaurantName){
        this.restaurantName = restaurantName;
    }
    /**
     * This method gets the id of the restaurant
     * @return the id of the restaurant as a String
     */
    public String getRestaurantID(){
        return restaurantID;
    }
    /**
    * This method sets the id of the restaurant
    * @param restaurantID

    */
    public void setRestaurantID(String restaurantID){
        this.restaurantID = restaurantID;
    }
    /**
    * This method gets the id of the item
    * @return the id of the item as a String
    */
    public String getItemID(){
        return itemID;
    }
    /**
    * This method sets the item ID
    * @param itemID
    */
    public void setItemID(String itemID){
        this.itemID = itemID;
    }
    /**
    * This method gets the item name
    * @return the item name as a String
    */
    public String getItemName(){
        return itemName;
    }
    /**
    * Thid method sets the item name
    * @param itemName
    */
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    /**
    * This method gets the batch ID
    * @return the batch id as a String
    */
    public String getBatchID(){
        return batchID;
    }
    /**
    * This methid sets the batch id
    * @param batchID
    */
    public void setBatchID(String batchID){
        this.batchID = batchID;
    }
    /**
    * This method gets the batch quantity of an item
    * @return an int of number quantity left
    */
    public int getBatchQty(){
        return batchQty;
    }
    /**
    * This method sets the batch quantity
    * @param batchQty
    */
    public void setBatchQty(int batchQty){
        this.batchQty = batchQty;
    }
    /**
    * This method gets the cost per each item
    * @return cost per item as a double
    */
    public double getCostPerItem(){
        return costPerItem;
    }
    /**
    * This method sets the cost per item
    * @param costPerItem
    */
    public void setCostPerItem(double costPerItem){
        this.costPerItem = costPerItem;
    }
    /**
    * This method gets the date each item was bought
    * @return the Date
    */
    public Date getDateBought(){
        return dateBought;
    }
    /**
    * This method sets the date that each item was bought
    * @param dateBought
    */
    public void setDateBought(Date dateBought){
        this.dateBought = dateBought;
    }
    /**
    * This method gets the Date each item expires
    * @return Date
    */
    public Date getDateExpired(){
        return dateExpired;
    }
    /**
    * This method sets the date that the item expires
    * @param dateExpired
    */
    public void setDateExpired(Date dateExpired){
        this.dateExpired = dateExpired;
    }
}
package com.example.springsocial.payload;
//The payload contains the data that could be stored or modified
public class InventoryRequest {
    @NotBlank
    private String restaurantName;

    @NotBlank
    private int itemID;

    @NotBlank
    private String itemName;

    @NotBlank
    private int batchID;

    @NotBlank
    private int batchQty;

    @NotBlank
    private double costPerItem;

    @NotBlank
    private Date dateBought;

    @NotBlank
    private Date dateExpired;

    public String getRestaurantName(){
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName){
        this.restaurantName = restaurantName;
    }

    public String getItemID(){
        return itemID;
    }

    public void setItemID(int itemID){
        this.itemID = itemID;
    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public int getBatchID(){
        return batchID;
    }

    public void setBatchID(int batchID){
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

    public Date setDateExpired(Date dateExpired){
        this.dateExpired = dateExpired;
    }
}

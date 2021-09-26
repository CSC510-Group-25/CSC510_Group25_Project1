package com.qtycalc;


import com.github.cliftonlabs.json_simple.JsonObject;

/*
ITEM for DATABASE inventory

 */
public class Item {

    String itemName;
    String dbID;    // may not be necessary?

    /*
    String batchID; // to help with expiration dates. One dbID can be mapped to many batches.
    String expDate;
    */

    Double qty;    // Quantity of item stored in database.
    String db_unit;

    public Item(){
        // empty constructor
    }

    public Item(String itemName, String dbID, Double qty, String db_unit) {
        this.itemName = itemName;
        this.dbID = dbID;
        this.qty = qty;
        this.db_unit = db_unit;
    }

    // construct an item from a string
    // input example: [butter, 2001, 20, oz]
    public Item(String ingstr){

        String[] nuArr = stringTrimmr(ingstr);

        this.itemName = nuArr[0];
        this.dbID = nuArr[1];
        this.qty = Double.parseDouble(nuArr[2]);
        this.db_unit = nuArr[3];
    }

    public Item(JsonObject jo){
        this.itemName = (String) jo.get("name");
        this.dbID = (String) jo.get("ID");
        this.qty = Double.parseDouble(jo.get("quantity").toString());
        this.db_unit = (String) jo.get("unit");
    }

    public JsonObject itemAsJson(){

        JsonObject nu = new JsonObject();
        nu.put("name", this.itemName);
        nu.put("ID",this.dbID);
        nu.put("quantity", this.qty);
        nu.put("unit", this.db_unit);

        return nu;
    }


    public String asJsonString(){
        JsonObject jo = this.itemAsJson();
        return jo.toJson();
    }


    @Override
    public String toString(){
        String returnMe = "[" + itemName + ", " + dbID + ", " + qty + ", " + db_unit + "]";
        return returnMe;
    }


    // MAKE SURE SETTERS MODIFY JSON TOO.
    public String getItemName() { return itemName; }
    public String getDbID() { return dbID; }
    public Double getQty() { return qty; }
    public String getDbUnit() { return db_unit; }

    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setDbID(String dbID) { this.dbID = dbID; }
    public void setQty(Double qty) { this.qty = qty; }
    public void setDbUnit(String db_unit) { this.db_unit = db_unit; }


    //////////////////////////////////////
    // HELPERS BENEATH THIS LINE //

    //helper to trim strings and convert to array
    private String[] stringTrimmr(String ingstr){
        // [butter, 2001, 20, oz] --> butter, 2001, 20, oz
        String nuStr = ingstr.substring(1, ingstr.length() -1);
        String[] nuArr  = nuStr.split(",");
        // {butter, 2001, 20, oz}
        for(int i =0; i <= 3 ; i++){
            nuArr[i]=nuArr[i].trim();
        }
        return nuArr;
    }
}

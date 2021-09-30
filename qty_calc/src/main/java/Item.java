//package com.qtycalc;
import com.github.cliftonlabs.json_simple.JsonObject;

/**ITEM for DATABASE inventory
 */
public class Item {

    /**
     * Item name
     */
    String itemName;
    /**
     * this is the ID of the Item recorded in the database
     */
    String dbID;

    /*
    String batchID; // to help with expiration dates. One dbID can be mapped to many batches.
    String expDate;
    */

    /**
     * Quantity of item stored in database.
     */
    Double qty;
    /**
     * lbs, kgs, etc
     */
    String db_unit;

    public Item(){
        // empty constructor
    }

    /**
     * Constructor
     *
     * @param itemName String
     * @param dbID String
     * @param qty Double
     * @param db_unit String
     */
    public Item(String itemName, String dbID, Double qty, String db_unit) {
        this.itemName = itemName;
        this.dbID = dbID;
        this.qty = qty;
        this.db_unit = db_unit;
    }

    /**
     * construct an Item from a string<br>
     * input example: [butter, 2001, 20, oz]
     * @param ingstr the Item string
     */
    public Item(String ingstr){
        String[] nuArr = stringTrimmr(ingstr);
        this.itemName = nuArr[0];
        this.dbID = nuArr[1];
        this.qty = Double.parseDouble(nuArr[2]);
        this.db_unit = nuArr[3];
    }

    /**
     * Construct item from JsonObject<br>
     * TODO: ensure that JsonObject jo is an Item Json
     * @param jo JsonObject
     */
    public Item(JsonObject jo){
        this.itemName = (String) jo.get("name");
        this.dbID = (String) jo.get("ID");
        this.qty = Double.parseDouble(jo.get("quantity").toString());
        this.db_unit = (String) jo.get("unit");
    }

    /**
     * Method to return this Item as a JsonObject
     * @return JsonObject
     */
    public JsonObject itemAsJson(){
        JsonObject nu = new JsonObject();
        nu.put("name", this.itemName);
        nu.put("ID",this.dbID);
        nu.put("quantity", this.qty);
        nu.put("unit", this.db_unit);

        return nu;
    }

    /**
     * Returns a json representation of an Item
     *
     * @return String -- a really nasty String
     */
    public String asJsonString(){
        JsonObject jo = this.itemAsJson();
        return jo.toJson();
    }

    //TODO: override .equals
    @Override
    public String toString(){
        String returnMe = "[" + itemName + ", " + dbID + ", " + qty + ", " + db_unit + "]";
        return returnMe;
    }


    /**
     * A lazy 'override' of .equals(), but doesn't actually override anything.
     *
     * @param o Object
     * @return boolean
     */
    public boolean isEqual(Object o){
        if(o==null){ return false; }
        if (!(o instanceof Item)) { return false; }
        Item nu = (Item) o;

        if(this.itemName.equals(nu.getItemName())
                && this.dbID.equals(nu.getDbID())
                && this.db_unit.equals(nu.getDbUnit())
                && this.qty.equals(nu.getQty())) {
            return true;
        }
        else {
            return false;
        }
        // return (this.toString().equals(nu.toString()));
    }

    /**
     * @return itemName String
     */
    public String getItemName() { return itemName; }

    /**
     * @return dbID String
     */
    public String getDbID() { return dbID; }

    /**
     * @return qty Double
     */
    public Double getQty() { return qty; }

    /**
     * @return db_unit String
     */
    public String getDbUnit() { return db_unit; }

    // MAKE SURE SETTERS MODIFY JSON TOO.

    /**Setter
     * @param itemName String
     */
    public void setItemName(String itemName) { this.itemName = itemName; }

    /**Setter
     * @param dbID String
     */
    public void setDbID(String dbID) { this.dbID = dbID; }

    /**Setter
     * @param qty Double
     */
    public void setQty(Double qty) { this.qty = qty; }

    /**Setter
     * @param db_unit String
     */
    public void setDbUnit(String db_unit) { this.db_unit = db_unit; }

    /**
     * helper to trim strings and convert to array
     *<br>
     * "[butter, 2001, 20, oz]" to {butter, 2001, 20, oz}
     * @param ingstr String
     * @return String[]
     */
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

package com.company;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ingredient {

    String ingredientName; // itemName
    String dbID;           // retrieved from DB if it's there
    double local_qty;
    String local_unit;

    JsonObject ingredientJson;

    // constructor for testing
    public Ingredient(String ingredientName, String dbID, String local_unit, double local_qty, JsonObject ingredientJson) {
        this.ingredientName = ingredientName;
        this.dbID = dbID;
        this.local_unit = local_unit;
        this.local_qty = local_qty;
        this.ingredientJson = ingredientJson;
    }

    //
    public Ingredient(String ingredientName, String dbID, double local_qty, String local_unit) {
        this.ingredientName = ingredientName;
        this.dbID = dbID;
        this.local_qty = local_qty;
        this.local_unit = local_unit;

        /*
        this.ingredientJson = new JsonObject();
        this.ingredientJson.put("ingredientName", this.ingredientName);
        this.ingredientJson.put("ing_DBID", this.dbID);
        this.ingredientJson.put("local_qty", this.local_qty);
        this.ingredientJson.put("local_unit", this.local_unit);
        */
    }


    // construct from a json object
    public Ingredient(JsonObject ingredientJson) {
        this.ingredientJson = ingredientJson;

        this.ingredientName = (String) this.ingredientJson.get("ingredientName");
        this.dbID = (String) this.ingredientJson.get("ing_DBID");
        this.local_qty = (Double) this.ingredientJson.get("local_qty");
        this.local_unit = (String) this.ingredientJson.get("local_unit");


    }


    /*
    private void jHelper(){

        this.ingredientName = (String) this.ingredientJson.get("ingredientName");
        this.dbID = (String) this.ingredientJson.get("ing_DBID");
        this.local_qty = (Double) this.ingredientJson.get("local_qty");
        this.local_unit = (String) this.ingredientJson.get("local_unit");

    }*/


    // construct an ingredient from a string
    // input example: [butter, 2001, 20, oz]
    public Ingredient(String ingstr){

        String[] nuArr = stringTrimmr(ingstr);

        this.ingredientName = nuArr[0];
        this.dbID = nuArr[1];
        this.local_qty = Double.parseDouble(nuArr[2]);
        this.local_unit = nuArr[3];


        /*
        this.ingredientJson = new JsonObject();
        this.ingredientJson.put("ingredientName", this.ingredientName);
        this.ingredientJson.put("ing_DBID", this.dbID);
        this.ingredientJson.put("local_qty", this.local_qty);
        this.ingredientJson.put("local_unit", this.local_unit);
        */
    }


    //helper to trim strings and convert to array
    private String[] stringTrimmr(String ingstr){
        // [butter, 2001, 20, oz] --> butter, 2001, 20, oz
        String nuStr = ingstr.substring(1, ingstr.length() -1);
        String[] nuArr  = nuStr.split(",");
        // {butter, 2001, 20, oz}
        for(int i =0; i < 3 ; i++){
            nuArr[i]=nuArr[i].trim();
        }
        return nuArr;
    }


    // if no json object exists
    public JsonObject ingredientAsJson(){

        JsonObject jIng = new JsonObject();

        jIng.put("ingredientName", this.ingredientName);
        jIng.put("ing_DBID", this.dbID);
        jIng.put("local_qty", this.local_qty);
        jIng.put("local_unit", this.local_unit);

        return jIng;

    }



    @Override
    public String toString() {
        String returnMe = "[" + ingredientName + ", " + dbID + ", " + local_qty + ", " + local_unit + "]";

        return returnMe;


       /* return "Ingredient{" +
                "ingredientName='" + ingredientName + '\'' +
                ", dbID='" + dbID + '\'' +
                ", local_unit='" + local_unit + '\'' +
                ", local_qty=" + local_qty +
                '}'; */
    }
}
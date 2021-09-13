package com.company;

public class Ingredient {

    String ingredientName; // itemName
    String dbID;           // retrieved from DB if it's there
    double local_qty;
    String local_unit;



    public Ingredient(String ingredientName, String dbID, String local_unit, double local_qty) {
        this.ingredientName = ingredientName;
        this.dbID = dbID;
        this.local_unit = local_unit;
        this.local_qty = local_qty;
    }


    // construct an ingredient from a string
    // input example: [butter, 2001, 20, oz]
    public Ingredient(String ingstr){

        String[] nuArr = stringTrimmr(ingstr);

        this.ingredientName = nuArr[0];
        this.dbID = nuArr[1];
        this.local_qty = Double.parseDouble(nuArr[2]);
        this.local_unit = nuArr[3];
    }


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

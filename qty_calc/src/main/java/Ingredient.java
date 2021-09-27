//package com.qty_calc;
import com.github.cliftonlabs.json_simple.JsonObject;

//INGREDIENT class for Recipes.
//TODO: NULL CHECKS.
public class Ingredient {

    String ingredientName; // itemName
    String dbID;           // retrieved from DB if it's there. CONSIDER: possible bugs with batch numbers.
    double local_qty;     //TODO: CANNOT BE NEGATIVE. Ensure input check occurs when the user is adding items
                          // to the database AND when adding ingredients to recipes.

    String local_unit;
    JsonObject ingredientJson;

    public Ingredient(String ingredientName, String dbID, double local_qty,
                      String local_unit, JsonObject ingredientJson) {
        this.ingredientName = ingredientName;
        this.dbID = dbID;
        this.local_unit = local_unit;
        this.local_qty = local_qty;
        this.ingredientJson = ingredientJson;
    }

    public Ingredient(String ingredientName, String dbID, double local_qty, String local_unit) {
        this.ingredientName = ingredientName;
        this.dbID = dbID;
        this.local_qty = local_qty;
        this.local_unit = local_unit;
        this.ingredientJson = ingredientAsJson();
    }

    // construct from a json object
    public Ingredient(JsonObject ingredientJson) {
        this.ingredientJson = ingredientJson;
        this.ingredientName = (String) this.ingredientJson.get("ingredientName");
        this.dbID = (String) this.ingredientJson.get("ing_DBID");
        this.local_qty = Double.parseDouble(this.ingredientJson.get("local_qty").toString());
        this.local_unit = (String) this.ingredientJson.get("local_unit");
    }

    /**
     * construct an ingredient from a string
     * input example: [butter, 2001, 20, oz]
     * @param ingstr
     */
    public Ingredient(String ingstr){
        String[] nuArr = stringTrimmr(ingstr);
        this.ingredientName = nuArr[0];
        this.dbID = nuArr[1];
        this.local_qty = Double.parseDouble(nuArr[2]);
        this.local_unit = nuArr[3];
    }

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

    /**
     * Constructs and returns a JsonObject
     * @return
     */
    public JsonObject ingredientAsJson(){
        JsonObject jIng = new JsonObject();
        jIng.put("ingredientName", this.ingredientName);
        jIng.put("ing_DBID", this.dbID);
        jIng.put("local_qty", this.local_qty);
        jIng.put("local_unit", this.local_unit);

        return jIng;
    }

    public String getIngredientName() { return ingredientName; }
    public String getDbID() { return dbID; }
    public double getLocal_qty() { return local_qty; }
    public String getLocal_unit() { return local_unit; }
    public JsonObject getIngredientJson() { return ingredientJson; }

    public boolean isEqual(Object o){

        if(o==null){ return false; }

        if (!(o instanceof Ingredient)) { return false; }

        Ingredient ing = (Ingredient) o;

        /*
        if(ing.ingredientName.equals(this.ingredientName) && ing.dbID.equals(this.dbID)
            && ing.local_qty==this.local_qty && ing.local_unit.equals(this.local_unit)){
            return true;
        }
        else{
            return false;
        }*/

        // the lazy way
        return (this.toString().equals(ing.toString()));
    }

    @Override
    public String toString() {
        String returnMe = "[" + ingredientName + ", " + dbID + ", " + local_qty + ", " + local_unit + "]";
        return returnMe;
    }
}

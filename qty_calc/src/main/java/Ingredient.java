import com.github.cliftonlabs.json_simple.JsonObject;

/**INGREDIENT class for Recipes.
 *
 */
//TODO: NULL CHECKS.
public class Ingredient {

    /**
     * The name of the ingredient
     */
    String ingredientName;
    /**
     * The ingredient's ID in the database.
     *<br>
     * retrieve from DB if it's there -- future release<br>
     * CONSIDER: possible bugs with batch numbers.
     */
    String dbID;           //
    /**
     * How much of an ingredient is needed by a Recipe.<br>
     * TODO: CANNOT BE NEGATIVE. Ensure input check occurs when the user is adding items
     *                            to the database AND when adding ingredients to recipes.
     */
    double local_qty;

    /**
     * local_unit for the Ingredient; needed for Recipe<br>
     * i.e, oatmeal needs 8 grams of sugar<br>
     * local_unit: grams<br>
     * db_unit for sugar: lbs<br>
     */
    String local_unit;
    /**
     *The ingredient as a JsonObject
     */
    JsonObject ingredientJson;

    /**
     * Constructor
     * @param ingredientName String
     * @param dbID String
     * @param local_qty double
     * @param local_unit String
     * @param ingredientJson JsonObject
     */
    public Ingredient(String ingredientName, String dbID, double local_qty,
                      String local_unit, JsonObject ingredientJson) {
        this.ingredientName = ingredientName;
        this.dbID = dbID;
        this.local_unit = local_unit;
        this.local_qty = local_qty;
        this.ingredientJson = ingredientJson;
    }

    /**
     * Constructor
     * @param ingredientName String
     * @param dbID String
     * @param local_qty double
     * @param local_unit String
     */
    public Ingredient(String ingredientName, String dbID, double local_qty, String local_unit) {
        this.ingredientName = ingredientName;
        this.dbID = dbID;
        this.local_qty = local_qty;
        this.local_unit = local_unit;
        this.ingredientJson = ingredientAsJson();
    }

    /**
     * construct from a json object
     * @param ingredientJson JsonObject
     */
    public Ingredient(JsonObject ingredientJson) {
        this.ingredientJson = ingredientJson;
        this.ingredientName = (String) this.ingredientJson.get("ingredientName");
        this.dbID = (String) this.ingredientJson.get("ing_DBID");
        this.local_qty = Double.parseDouble(this.ingredientJson.get("local_qty").toString());
        this.local_unit = (String) this.ingredientJson.get("local_unit");
    }

    /**
     * construct an ingredient from a string<br>
     * input example: [butter, 2001, 20, oz]
     * @param ingstr the ingredient string
     */
    public Ingredient(String ingstr){
        String[] nuArr = stringTrimmr(ingstr);
        this.ingredientName = nuArr[0];
        this.dbID = nuArr[1];
        this.local_qty = Double.parseDouble(nuArr[2]);
        this.local_unit = nuArr[3];
    }

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

    /**
     * Constructs and returns a JsonObject for this ingredient.
     * @return JsonObject
     */
    public JsonObject ingredientAsJson(){
        JsonObject jIng = new JsonObject();
        jIng.put("ingredientName", this.ingredientName);
        jIng.put("ing_DBID", this.dbID);
        jIng.put("local_qty", this.local_qty);
        jIng.put("local_unit", this.local_unit);
        return jIng;
    }

    /**
     *
     * @return String
     */
    public String getIngredientName() { return ingredientName; }

    /**
     *
     * @return String
     */
    public String getDbID() { return dbID; }

    /**
     *
     * @return double
     */
    public double getLocal_qty() { return local_qty; }

    /**
     *
     * @return String
     */
    public String getLocal_unit() { return local_unit; }

    /**
     *
     * @return JsonObject
     */
    public JsonObject getIngredientJson() { return ingredientJson; }

    /**
     * Method to check if an Ingredient is equal
     *<br>
     * Basically, a lazy 'override' of .equals(), but doesn't actually override anything.
     *
     * @param o Object
     * @return boolean
     */
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

//package com.qtycalc;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO: ALLOW MODIFICATION OF RECIPES
//TODO: BADLY NEEDS TO BE CLEANED UP.
//TODO: ENSURE THAT EACH INGREDIENT IS UNIQUE.
// May benefit from a hashmap.
// ALLOW CREATION OF RECIPES THROUGH I/O

/**
 * Recipe class
 */
public class Recipe {

    /**
     * the recipe's name
     */
    private String recipeName;  // can also be used for filename
    /**
     * the recipe's ID
     */
    private String recipeID;   // used for file name
    private String filePath;  // is this necessary?

    // "recipe_folder/" + "recipe_" + this.recipeID + ".txt";
    // "recipe_folder/" + "recipe_" + this.recipeID + ".json";

    /**
     * the recipe as a JsonObject
     */
    private JsonObject recipeJson;
    /**
     * ArrayList of Ingredients
     */
    private ArrayList<Ingredient> ingredientList;

    /**
     * Constructor. Should not be used outside of testing.
     * @param recipeName String
     * @param recipeID String
     * @param filePath String
     * @param ingredientList ArrayList
     */
    public Recipe(String recipeName, String recipeID, String filePath,
                  ArrayList<Ingredient> ingredientList) {

        this.recipeName = recipeName;
        this.recipeID = recipeID;
        this.filePath = filePath;
        this.ingredientList = ingredientList;
    }

    /**
     * Constructor
     * @param recipeName String
     * @param recipeID String
     * @param ingredientList ArrayList
     */
    public Recipe(String recipeName, String recipeID, ArrayList<Ingredient> ingredientList) {
        this.recipeName = recipeName;
        this.recipeID = recipeID;
        this.ingredientList = ingredientList;
        buildJsonRecipe();
    }

    /**
     * Constructor. Should not be used outside of testing.
     * @param recipeName String
     * @param recipeID String
     * @param recipeJson JsonObject
     * @param ingredientList ArrayList
     */
    public Recipe(String recipeName, String recipeID, JsonObject recipeJson, ArrayList<Ingredient> ingredientList) {
        this.recipeName = recipeName;
        this.recipeID = recipeID;
        this.recipeJson = recipeJson;
        this.ingredientList = ingredientList;
    }

    // "recipe_folder/" + "recipe_" + this.recipeID + ".json";
    //TODO: ENSURE THAT FILEPATH CONTAINS "recipe_folder" !!!
    // This will be handled elsewhere (?)
    /**
     * Constructs a recipe from a given file.
     * ex)
     * "recipe_folder/recipe_0001.json"
     *
     * File must end with .txt or .json.
     *
     * @param filePath String
     * @throws IOException -
     * @throws JsonException -
     */
    public Recipe(String filePath) throws IOException, JsonException {
        //TODO: ADD NULL CHECK
        this.filePath = filePath;
        this.ingredientList = new ArrayList<>();
        if(filePath.endsWith(".txt")) {
            ConstructFromFile();
        } else if(filePath.endsWith(".json")){
            ConstructFromJsonFile();
        } else {
            System.out.println("Unsupported file type. Supported: .txt, .json");
        }
    }

    /**
     * Constructs a recipe from a json object.
     * @param jo JsonObject
     */
    public Recipe(JsonObject jo) {
        //TODO: CHECK NULL
        this.recipeJson = jo;
        this.recipeName = (String) jo.get("recipeName");
        this.recipeID = (String) jo.get("recipeID");
        this.ingredientList = new ArrayList<>();

        JsonArray things = (JsonArray) jo.get("ingredient_list");
        ArrayList<String> names = new ArrayList<>();

        if (things != null) {

            for (int i = 0; i < things.size(); i++) {
                JsonObject thing = (JsonObject) things.get(i);

                if (thing != null) {
                    Ingredient nuIngr = new Ingredient(thing);

                    if (names.contains(nuIngr.ingredientName)) {
                        //TODO: implement something that prevents duplicates
                        // maybe this should happen when a recipe is saved instead?
                    } else { // if ingredient not in list
                        this.ingredientList.add(nuIngr);
                        names.add(nuIngr.ingredientName);
                    }
                } else {
                    System.out.println("null json object");
                }
            }
        }
    }

    /**
     * Creates a JsonObject
     */
    private void buildJsonRecipe(){
        //TODO: ADD NULL CHECK
        this.recipeJson = new JsonObject();
        this.recipeJson.put("recipeName", this.recipeName);
        this.recipeJson.put("recipeID", this.recipeID);

        JsonArray ingjar = new JsonArray();
        for (Ingredient ing : this.ingredientList){
            JsonObject ingj = ing.ingredientAsJson();
            ingjar.add(ingj);
        }
        this.recipeJson.put("ingredient_list",ingjar);
    }


    /**
     * Packs a recipe for a menu.
     *<br><br>
     * tl;dr: creates and returns JsonObject for the recipe so that it can be easily packed into a JsonArray.
     *<br><br>
     * Used for reading/writing menus.
     *<br><br>
     * Example:
     *<br><br>
     * {"recipe":<br>
     * {"recipeName":"idek",<br>
     * "ingredient_list":<br>
     *   [{"ing_DBID":"9891","ingredientName":"thing1","local_qty":8.0,"local_unit":"lbs"},<br>
     *    {"ing_DBID":"7777","ingredientName":"thing2","local_qty":5.0,"local_unit":"kgs"},<br>
     *    {"ing_DBID":"1234","ingredientName":"thing3","local_qty":9000.0,"local_unit":"mL"}],<br>
     * "recipeID":"1009"}<br>
     * }<br>
     *
     * To access:<br><br>
     *
     * JsonObject jo = recipe.packForMenu();<br>
     * JsonObject contents = (JsonObject) jo.get("recipe");<br>
     * String recipeName = (String) contents.get("recipeName");<br>
     * String recipeID = (String) contents.get("recipeID");<br>
     * JsonArray ingredientsJsonArray = (JsonArray) contents.get("ingredient_list");
     *
     * @return JsonObject
     */
    public JsonObject packForMenu(){

        // if this.recipeJson == null, call buildJsonObject()

        JsonObject nuj = new JsonObject();
        JsonObject contents = new JsonObject();
        contents.put("recipeName", this.recipeName);
        contents.put("recipeID",this.recipeID);

        JsonArray ingjar = new JsonArray();
        for (Ingredient ing : this.ingredientList){
            JsonObject ingj = ing.ingredientAsJson();
            ingjar.add(ingj);
        }
        contents.put("ingredient_list", ingjar);
        nuj.put("recipe",contents);

        return nuj;
    }

    // May be better than the other packer
    /*
    public JsonObject packForMenu2(){

        // if this.recipeJson == null, call buildJsonObject()

        JsonObject nuj = new JsonObject();
        JsonObject contents = new JsonObject();
        contents.put("recipeName", this.recipeName);
        contents.put("recipeID",this.recipeID);

        JsonArray ingjar = new JsonArray();

        for (Ingredient ing : this.ingredientList){
            JsonObject ingj = ing.ingredientAsJson();
            ingjar.add(ingj);
        }

        contents.put("ingredient_list", ingjar);
        nuj.put(this.recipeName,contents);

        return nuj;
    }
    */

    /**
     * Constructs a recipe from a file
     * @throws IOException -
     */
    private void ConstructFromFile() throws IOException {
        // [name, id, ingr1, ingr2, ...]
        // [0] = name
        // [1] = id
        String[] recipeArr = readFile();
        ArrayList<String> names = new ArrayList<>();

        if (recipeArr != null){
            this.recipeName = recipeArr[0];
            this.recipeID = recipeArr[1];

            // add ingredients to the list
            for (int i = 2; i<recipeArr.length; i++){
                Ingredient nuIngr = new Ingredient(recipeArr[i]);

                if (names.contains(nuIngr.ingredientName)){
                    //TODO: implement something that prevents duplicates
                    // maybe this should happen when a recipe is saved instead?
                }
                else { // if ingredient not in list
                    this.ingredientList.add(nuIngr);
                    names.add(nuIngr.ingredientName);
                }
            }
            buildJsonRecipe();
        }
    }


    /**
     * Reads a file.
     * @return String[] contents of a file
     * @throws IOException -
     */
    private String[] readFile() throws IOException {

        List<String> recipe = new ArrayList<String>();
        String[] recipeArr;
        // TODO: ensure no ingredient repeats
        Path path = Paths.get(this.filePath).toAbsolutePath();
        Scanner sc = new Scanner(new File(String.valueOf(path)));
        while (sc.hasNextLine()) {
            recipe.add(sc.nextLine());
        }
        recipeArr = recipe.toArray(new String[0]);
        sc.close();
        return recipeArr;
    }


    //TODO:
    /**
     * Constructs a recipe from a .json file
     * @throws FileNotFoundException -
     * @throws JsonException -
     */
    private void ConstructFromJsonFile() throws FileNotFoundException, JsonException {

        Path path = Paths.get(this.filePath).toAbsolutePath(); // TODO: bug risk?
        FileReader fileReader = new FileReader(String.valueOf((path)));
        JsonObject recipe = (JsonObject) Jsoner.deserialize(fileReader);

        this.recipeJson = recipe;
        this.recipeName = (String) recipe.get("recipeName");
        this.recipeID = (String) recipe.get("recipeID");

        JsonArray things = (JsonArray) recipe.get("ingredient_list");
        ArrayList<String> names = new ArrayList<>();

        if (things != null) {

            for (int i = 0; i < things.size(); i++) {
                JsonObject thing = (JsonObject) things.get(i);

                if (thing != null) {
                    Ingredient nuIngr = new Ingredient(thing);

                    if (names.contains(nuIngr.ingredientName)) {
                        //TODO: implement something that prevents duplicates
                        // maybe this should happen when a recipe is saved instead?
                    } else { // if ingredient not in list
                        this.ingredientList.add(nuIngr);
                        names.add(nuIngr.ingredientName);
                    }
                } else{
                    System.out.println("null json object");
                }
            }
        } else{
            System.out.println("null json array");
        }
    }


    /**
     * @return recipeName String
     */
    public String getRecipeName() { return recipeName; }

    /**
     * @return recipeID String
     */
    public String getRecipeID() { return recipeID; }

    /**
     * @return String
     */
    public String getFilePath() { return filePath; }

    /**
     * @return JsonObject
     */
    public JsonObject getRecipeJson() { return recipeJson; }

    /**
     * @return ArrayList
     */
    public ArrayList<Ingredient> getIngredientList() { return ingredientList; }

    /**
     * @param recipeJson JsonObject
     */
    public void setRecipeJson(JsonObject recipeJson) {
        this.recipeJson = recipeJson;
    }

    /**
     * Adds an ingredient to the recipe.
     * @param ing Ingredient
     * @return boolean
     */
    public boolean addIngredient(Ingredient ing){
        for(Ingredient i : this.ingredientList){
            if (i.isEqual(ing)){ // identical in every way
                return false;
            }
            if (i.dbID.equals(ing.dbID)){ // just in case there's an error with names...
                return false;
            }
        }
        this.ingredientList.add(ing);
        return true;
    }

    /**
     * Removes an ingredient from the recipe.<br>
     * Be sure to call buildJsonRecipe() after you're done with additions/removals.
     * @param ing Ingredient
     * @return boolean
     */
    public boolean removeIngredient(Ingredient ing){
        for(int i=0; i<this.ingredientList.size(); i++){
            if (this.ingredientList.get(i).isEqual(ing)) {
                this.ingredientList.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * A lazy 'override' of .equals()
     *
     * @param o Object
     * @return boolean
     */
    public boolean isEqual(Object o){
        if(o==null){ return false; }
        if (!(o instanceof Recipe)) { return false; }
        Recipe r = (Recipe) o;
        // the lazy way
        return (this.toString().equals(r.toString()));
    }


    /**
     * Creates and returns a json object representation of the recipe.
     *
     * @return JsonObject
     */
    private JsonObject buildJson(){
        //TODO: ADD NULL CHECK
        JsonObject jo = new JsonObject();
        jo.put("recipeName", this.recipeName);
        jo.put("recipeID", this.recipeID);
        JsonArray ingjar = new JsonArray();
        for (Ingredient ing : this.ingredientList){
            JsonObject ingj = ing.ingredientAsJson();
            ingjar.add(ingj);
        }
        jo.put("ingredient_list",ingjar);
        return jo;
    }

    /**
     * Saves this recipe to the given destination folder.
     *
     * @param destinationFolder String
     * @throws IOException -
     */
    public void saveRecipeAsJson(String destinationFolder) throws IOException {
        JsonObject jo = buildJson();
        BuildFile.SaveRecipeJson(jo,destinationFolder);
    }

    /**
     * Include destination folder
     *<br>
     * This is to bypass IO for test cases.
     *
     * @param destinationFolder String
     * @throws IOException -
     */
    public void saveRecipeAsJsonTest(String destinationFolder) throws IOException {
        JsonObject jo = buildJson();
        BuildFile.SaveRecipeJsonForTesting(jo,destinationFolder);
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        String returnMe = this.recipeName + "\n" + this.recipeID;
        str.append(returnMe);

        for (Ingredient ingr : this.ingredientList){
            String nuStr = "\n" + ingr.toString();
            str.append(nuStr);
        }
        return str.toString();
    }

    ////////////////////////////////////////////////////

    public static void main(String[] args){
        try {
            Recipe r = new Recipe("recipe_folder/recipe_0001.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

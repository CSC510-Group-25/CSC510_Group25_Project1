package com.company;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.github.cliftonlabs.json_simple.Jsoner.prettyPrint;

//TODO:
/*
HAVE A SCRIPT OR SOMETHING THAT CREATES A FOLDER TO CONTAIN RECIPES.
That, or include an empty directory upon installation.
 */

//TODO: ALLOW MODIFICATION OF RECIPES
//TODO: BADLY NEEDS TO BE CLEANED UP.
//TODO: ENSURE THAT EACH INGREDIENT IS UNIQUE.

public class Recipe {

    private String recipeName;  // can also be used for filename
    private String recipeID;   // used for file name
    private String filePath;  // is this necessary?

    // "recipe_folder/" + "recipe_" + this.recipeID + ".txt";
    // "recipe_folder/" + "recipe_" + this.recipeID + ".json";

    private JsonObject recipeJson;
    private ArrayList<Ingredient> ingredientList;

    public Recipe(String recipeName, String recipeID, String filePath,
                  ArrayList<Ingredient> ingredientList) {

        this.recipeName = recipeName;
        this.recipeID = recipeID;
        this.filePath = filePath;
        this.ingredientList = ingredientList;
    }


    public Recipe(String recipeName, String recipeID, ArrayList<Ingredient> ingredientList) {
        this.recipeName = recipeName;
        this.recipeID = recipeID;
        this.ingredientList = ingredientList;
        buildJsonObject();
    }

    public Recipe(){
        this.recipeName = "";
        this.recipeID = "";
        this.filePath = "";
        this.ingredientList = new ArrayList<>();
        this.recipeJson = new JsonObject();
    }

    public Recipe(String recipeName, String recipeID, JsonObject recipeJson, ArrayList<Ingredient> ingredientList) {
        this.recipeName = recipeName;
        this.recipeID = recipeID;
        this.recipeJson = recipeJson;
        this.ingredientList = ingredientList;
    }

    // "recipe_folder/" + "recipe_" + this.recipeID + ".json";
    //TODO: ENSURE THAT FILEPATH CONTAINS "recipe_folder" !!!
    // This will be handled elsewhere
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


    //TODO
    //recipe from json object
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



    private void buildJsonObject(){

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
     *
     * tl;dr: creates and returns JsonObject for the recipe so that it can be easily packed into a JsonArray.
     *
     * Used for reading/writing menus.
     *
     * Example:
     *
     * {"recipe":
     * {"recipeName":"idek",
     * "ingredient_list":
     *   [{"ing_DBID":"9891","ingredientName":"thing1","local_qty":8.0,"local_unit":"lbs"},
     *    {"ing_DBID":"7777","ingredientName":"thing2","local_qty":5.0,"local_unit":"kgs"},
     *    {"ing_DBID":"1234","ingredientName":"thing3","local_qty":9000.0,"local_unit":"mL"}],
     * "recipeID":"1009"}
     * }
     *
     * To access:
     *
     * JsonObject jo = recipe.packForMenu();
     * JsonObject contents = (JsonObject) jo.get("recipe");
     * String recipeName = (String) contents.get("recipeName");
     * String recipeID = (String) contents.get("recipeID");
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

            buildJsonObject();
        }
    }



    /**
     *
     * @return String[] contents of a file
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



    // TODO: ??? is there a use for this?
    private void ConstructFromFile(String filePath) throws IOException {
        //throws FileNotFoundException {

        // [name, id, ingr1, ingr2, ...]
        // [0] = name
        // [1] = id

        String[] recipeArr = readFile(filePath);

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
        }
    }



    private String[] readFile(String filePath) throws IOException {

        List<String> recipe = new ArrayList<String>();
        String[] recipeArr;

        // TODO: ensure no ingredient repeats

        Path path = Paths.get(filePath).toAbsolutePath();

        Scanner sc = new Scanner(new File(String.valueOf(path)));
        while (sc.hasNextLine()) {
            recipe.add(sc.nextLine());
        }
        recipeArr = recipe.toArray(new String[0]);
        sc.close();
        return recipeArr;
    }



    public String getRecipeName() { return recipeName; }
    public String getRecipeID() { return recipeID; }
    public String getFilePath() { return filePath; }
    public JsonObject getRecipeJson() { return recipeJson; }
    public ArrayList<Ingredient> getIngredientList() { return ingredientList; }

    public void setRecipeJson(JsonObject recipeJson) {
        this.recipeJson = recipeJson;
    }

    //TODO: ENSURE EVERYTHING IS UPDATED, including .json, .txt, etc
    // ENSURE NO DUPLICATES ?
    public void addIngredient(Ingredient ing){
        this.ingredientList.add(ing);
    }



    //TODO
    // after adding/removing ingredients, use this and construct a new recipe json.
    public void updateRecipe(){ }


    /*
    Add a recipe to the database
    May need to be moved elsewhere
     */
    //TODO
    //PREVENT DUPLICATE RECIPES
    public void saveNewRecipe(){ }


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
    ///HELPERS GO HERE///


    ////////////////////////////////////////////////////

    public static void main(String[] args){

        try {
            Recipe r = new Recipe("recipe_folder/recipe_0001.txt");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

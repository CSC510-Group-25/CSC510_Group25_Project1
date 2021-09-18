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

//TODO:
/*
HAVE A SCRIPT OR SOMETHING THAT CREATES A FOLDER TO CONTAIN RECIPES.
That, or include an empty directory upon installation.
 */

public class Recipe {

    String recipeName;
    String recipeID;
    String filePath; // "recipe_folder/" + "recipe_" + this.recipeID + ".txt";

    // "recipe_folder/" + "recipe_" + this.recipeID + ".json";

    JsonObject recipeJson;
    ArrayList<Ingredient> ingredientList;

    public Recipe(String recipeName, String recipeID, String filePath,
                  ArrayList<Ingredient> ingredientList) {

        this.recipeName = recipeName;
        this.recipeID = recipeID;
        this.filePath = filePath;
        this.ingredientList = ingredientList;
    }



    // FOR MANUAL TESTING.
    public Recipe(String recipeName, String recipeID, ArrayList<Ingredient> ingredientList) {
        this.recipeName = recipeName;
        this.recipeID = recipeID;
        this.ingredientList = ingredientList;
    }

    // "recipe_folder/" + "recipe_" + this.recipeID + ".json";
    public Recipe(String filePath) throws IOException, JsonException {

        this.filePath = filePath;
        this.ingredientList = new ArrayList<>();

        if(filePath.endsWith(".txt")) {
            ConstructFromFile();

        } else if(filePath.endsWith(".json")){
            ConstructFromJson(filePath);
            // ConstructFromJson();

        } else {
            System.out.println("Unsupported file extension. Supported: .txt, .json");
        }

    }



    //TODO: relocate to BuildFile.java
    private void BuildJson(String filePath) throws IOException {

        this.recipeJson = new JsonObject();

        this.recipeJson.put("recipeName", this.recipeName);
        this.recipeJson.put("recipeID", this.recipeID);

        String dir = String.valueOf(Paths.get(this.filePath).toAbsolutePath().getParent());
        String fn = "recipe_" + this.recipeID +".json";

        String nuPath = dir + "/" + fn;

        JsonArray ingjar = new JsonArray();

        for (Ingredient ing : this.ingredientList){
            JsonObject ingj = ing.ingredientAsJson();
            ingjar.add(ingj);
        }

        this.recipeJson.put("ingredient_list",ingjar);

        try (FileWriter fileWriter = new FileWriter(nuPath)) {
            Jsoner.serialize(this.recipeJson, fileWriter);
        }

        //String jsn = this.recipeJson.toJson();
        //jsn = Jsoner.prettyPrint(jsn);
        //System.out.println(Jsoner.prettyPrint(jsn));


    }



    private void ConstructFromFile() throws IOException {
        //throws FileNotFoundException {

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


            String dir = String.valueOf(Paths.get(this.filePath).toAbsolutePath().getParent());
            String fn = "recipe_" + this.recipeID +".json";

            String nuPath = dir + "/" + fn;

            Path path = Paths.get(nuPath).toAbsolutePath();

            // check if file exists. if not, build a json!
            if(!Files.exists(path)) {
                BuildJson(filePath);
            }
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

        //} catch (FileNotFoundException e) {

        //  System.out.println("problem reading/locating file");
        // e.printStackTrace();
        // }

        return recipeArr;
    }


    //TODO: WIP
    private void ConstructFromJson(String filePath) throws FileNotFoundException, JsonException {

        Path path = Paths.get(filePath).toAbsolutePath();

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

            /*String thing1 = (String) thing.get("ingredientName");
            String thing2 = (String) thing.get("ing_DBID");
            Double thing3 = (Double) thing.get("local_qty");
            String thing4 = (String) thing.get("local_unit");*/

                    System.out.println(nuIngr.toString());

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

        //Scanner sc = new Scanner(new File(this.filePath));

        while (sc.hasNextLine()) {
            recipe.add(sc.nextLine());
        }
        recipeArr = recipe.toArray(new String[0]);

        sc.close();

        //} catch (FileNotFoundException e) {

        //  System.out.println("problem reading/locating file");
        // e.printStackTrace();
        // }

        return recipeArr;
    }


    /*
    Add a recipe to the database
    May need to be moved elsewhere
     */
    public void saveNewRecipe(){

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

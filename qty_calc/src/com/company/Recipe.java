package com.company;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

//TODO:
/*
HAVE A SCRIPT OR SOMETHING THAT CREATES A FOLDER TO CONTAIN RECIPES.
That, or include an empty directory upon installation.
 */

public class Recipe {

    String recipeName;
    String recipeID;
    String fileDirectory; // "recipe_folder/"
    String filePath; // "recipe_folder/" + "recipe_" + this.recipeID + ".txt";

    // "recipe_folder/" + "recipe_" + this.recipeID + ".json";

    JsonObject recipeJson;

    ArrayList<Ingredient> ingredientList;

    JsonArray ingredientListJson;

    public Recipe(String recipeName, String recipeID, String fileDirectory, String filePath,
                  ArrayList<Ingredient> ingredientList) {

        this.recipeName = recipeName;
        this.recipeID = recipeID;
        this.fileDirectory = fileDirectory;
        this.filePath = filePath;
        this.ingredientList = ingredientList;
    }



    // FOR MANUAL TESTING.
    public Recipe(String recipeName, String recipeID, ArrayList<Ingredient> ingredientList) {
        this.recipeName = recipeName;
        this.recipeID = recipeID;
        this.ingredientList = ingredientList;
    }


    /*
    Given a name and filepath, construct the recipe from the contents of the file.
     */
    public Recipe(String recipeName, String filePath) {
        this.filePath = filePath;

        // ConstructFromFile();

    }


    // "recipe_folder/" + "recipe_" + this.recipeID + ".txt";
    public Recipe(String filePath) throws FileNotFoundException, JsonException {

        this.filePath = filePath;
        this.ingredientList = new ArrayList<>();

        if(filePath.endsWith(".txt")) {
            ConstructFromFile();
        } else if(filePath.endsWith(".json")){
            ConstructFromJson(filePath);

        } else {

            System.out.println("Unsupported file extension. Supported: .txt, .json");
        }

    }


    //TODO:
    private void ConstructFromJson(String filePath) throws FileNotFoundException, JsonException {


        FileReader fileReader = new FileReader((filePath));

        //
        JsonObject recipe = (JsonObject) Jsoner.deserialize(fileReader);

        this.recipeJson = recipe;

        this.recipeName = (String) recipe.get("recipeName");
        this.recipeID = (String) recipe.get("recipeID");


        JsonArray things = (JsonArray) recipe.get("ingredient_list");

        //Iterator itr = things.iterator();

       // while(itr.hasNext()){ }

        for (int i = 0 ; i < things.size(); i++) {
            JsonObject thing = (JsonObject) things.get(i);

            Ingredient nuIngr = new Ingredient(thing);

            /*String thing1 = (String) thing.get("ingredientName");
            String thing2 = (String) thing.get("ing_DBID");
            Double thing3 = (Double) thing.get("local_qty");
            String thing4 = (String) thing.get("local_unit");*/

            System.out.println(nuIngr.toString());

        }


        //this.recipeJson.put("recipeID", this.recipeID);
       // this.recipeJson.put("file_location", this.filePath);


    }




    private void ConstructFromFile() {
        //throws FileNotFoundException {

        // [name, id, ingr1, ingr2, ...]
        // [0] = name
        // [1] = id

        String[] recipeArr = readFile();

        if (recipeArr != null){

            this.recipeName = recipeArr[0];
            this.recipeID = recipeArr[1];

            // add ingredients to the list
            for (int i = 1; i<recipeArr.length; i++){

                Ingredient nuIngr = new Ingredient(recipeArr[i]);
                this.ingredientList.add(nuIngr);
            }
        }
    }




    //TODO:
    private void asJ() throws FileNotFoundException {

        //File nuDir = new File(System.getProperty(...))
        //File nuFile = new File(nuDir.getAbsolutePath() + File.separator + "....");

        this.recipeJson = new JsonObject();


        this.recipeJson.put("recipeName", this.recipeName);
        this.recipeJson.put("recipeID", this.recipeID);
        this.recipeJson.put("file_location", this.filePath);


        /*
        PrintWriter pw = new PrintWriter(...);

        pw.write(this.recipeJson.toJson());
        pw.flush();
        pw.close();
        */

    }



    /**
     *
     * @return String[] contents of a file
     */
    private String[] readFile(){

        List<String> recipe = new ArrayList<String>();
        String[] recipeArr = null;


        try {
            Scanner sc = new Scanner(new File(this.filePath));

            while (sc.hasNextLine()) {
                recipe.add(sc.nextLine());
            }
            recipeArr = recipe.toArray(new String[0]);

            sc.close();

        } catch (FileNotFoundException e) {

            System.out.println("problem reading/locating file");
            // e.printStackTrace();
        }

        return recipeArr;
    }

}

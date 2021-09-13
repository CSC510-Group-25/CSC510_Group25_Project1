package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recipe {

    String recipeName;
    String recipeID;
    String fileDirectory; // "recipe_folder/"
    String filePath;

    // "recipe_folder/" + "recipe_" + this.recipeID + ".txt";

    //int numIngredients;
    ArrayList<Ingredient> ingredientList;

    public Recipe(String recipeName, String recipeID, String fileDirectory, String filePath,
                  int numIngredients, ArrayList<Ingredient> ingredientList) {

        this.recipeName = recipeName;
        this.recipeID = recipeID;
        this.fileDirectory = fileDirectory;
        this.filePath = filePath;
        //this.numIngredients = numIngredients;
        this.ingredientList = ingredientList;
    }



    // FOR MANUAL TESTING.
    public Recipe(String recipeName, String recipeID, ArrayList<Ingredient> ingredientList) {
        this.recipeName = recipeName;
        this.recipeID = recipeID;
        //this.numIngredients = numIngredients;
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
    public Recipe(String filePath) {

        this.filePath = filePath;
        this.ingredientList = new ArrayList<>();

        ConstructFromFile();

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

            for (int i = 1; i<recipeArr.length; i++){

                Ingredient nuIngr = new Ingredient(recipeArr[i]);
                this.ingredientList.add(nuIngr);
            }
        }
    }


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
            // e.printStackTrace();
        }

        return recipeArr;
    }

/*
    public ArrayList<Ingredient> populateList(){


        //ArrayList<Ingredient> nuList = new ArrayList<>(this.numIngredients);
    }
*/







}

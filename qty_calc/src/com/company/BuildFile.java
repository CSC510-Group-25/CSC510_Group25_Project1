package com.company;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * Class to build json files from .txt files, and .txt files from .json files.
 */
public class BuildFile {

    //TODO: add windows support


    /**
     * Checks if a given directory exists.
     *
     * @param filePath
     * @return boolean
     */
    public static boolean directoryExists(String filePath){

        Path path = Paths.get(filePath).toAbsolutePath();

        if(Files.exists(path) && Files.isDirectory(path)) {
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Checks if a json file exists for the given recipe ID.
     * If the filepath given leads to a text file, the method checks the directory of
     * the given file for the existence of the json file.
     *
     * ex) if filePath = "recipe_folder/recipe_0001.txt" and recipeID = "1234"
     *
     *     then the method will check if "recipe_1234.json" exists inside the "recipe_folder/" directory.
     *
     *     If filePath leads to a directory, then that directory will be checked.
     *
     * @param filePath
     * @param recipeID
     * @return boolean -- true if the file exists, false if not
     */
    public static boolean JsonExists(String filePath, String recipeID) { // TODO: BUG RISK, AM I REDUNDANT?

        String jsonFile = "recipe_" + recipeID + ".json";
        Path path1 = Paths.get(filePath).toAbsolutePath();

        if (Files.exists(path1)) {
            if (Files.isDirectory(path1)) {

                System.out.println("Searching directory...");

                String nuPath = String.valueOf(path1) + "/" + jsonFile;
                Path path = Paths.get(nuPath).toAbsolutePath();

                if (Files.exists(path) && !Files.isDirectory(path)) {
                    System.out.println("A .json file exists for recipe ID: " +recipeID);
                    return true;
                }
                // else{ System.out.println("Could not locate .json file for recipe ID: " +recipeID); }

            } else if (!Files.isDirectory(path1)) {

                System.out.println("The path given is to a file and not a directory. Searching parent directory...");

                // get directory
                String dir = String.valueOf(Paths.get(filePath).toAbsolutePath().getParent());
                String nuPath = dir + "/" + jsonFile;
                Path path = Paths.get(nuPath).toAbsolutePath();

                if (Files.exists(path) && !Files.isDirectory(path)) {
                    System.out.println("A .json file exists for recipe ID: " +recipeID);
                    return true;
                }
            }
        } else { System.out.println(path1 + " does not exist."); }

        System.out.println("Could not locate .json file for: " +recipeID+" with the given path: " + path1);

        return false;
    }



    private static String getJsonPath(String filePath, String recipeID){

        String jsonFile = "recipe_" + recipeID + ".json";

        Path path1 = Paths.get(filePath).toAbsolutePath();


        if (Files.exists(path1)) {

            if (Files.isDirectory(path1)) {


                String nuPath = String.valueOf(path1) + "/" + jsonFile;
                Path path = Paths.get(nuPath).toAbsolutePath();

                if (Files.exists(path)) {
                    System.out.println("A .json file already exists for recipe ID: " +recipeID);
                    return "";
                }

                else {
                    return path.toString();
                }

            } else if (!Files.isDirectory(path1)) {

                //System.out.println("The path given is to a file and not a directory. Searching parent directory...");

                // get directory
                String dir = String.valueOf(Paths.get(filePath).toAbsolutePath().getParent());
                String nuPath = dir + "/" + jsonFile;
                Path path = Paths.get(nuPath).toAbsolutePath();

                if (Files.exists(path) && !Files.isDirectory(path)) {
                    System.out.println("A .json file already exists for recipe ID: " +recipeID);
                    return "";
                }
                else {
                    return path.toString();
                }
            }
        }

        System.out.println(path1 + " does not exist.");

        //System.out.println("Could not locate .json file for: " +recipeID+" with the given path: " + path1);

        return "";
    }




    //TODO: ENSURE THAT FILEPATH CONTAINS "recipe_folder" !!!
    public static void BuildJsonFile(Recipe recipe, String filePath) throws IOException {

        String folder = "recipe_folder";

        if (filePath.toLowerCase().contains(folder)){
            //yay!
        } else {
            // String msg = "Please store your recipes in a folder named \"recipe_folder\"...
            // throw new Exception(msg)
        }

        // recipe must not be null

        JsonObject rj = recipe.getRecipeJson();

        // if(recipe.getRecipeID().equals(rj.get("recipeID").toString())){ }


        String rID = (String) rj.get("recipeID");
        String jpath = getJsonPath(filePath,rID);

        if(!jpath.isEmpty()){

            //Path path = Paths.get(jpath).toAbsolutePath();

            try (FileWriter fileWriter = new FileWriter(jpath)) {
                Jsoner.serialize(rj, fileWriter);
            }

        }
    }


    /*
Add a recipe to the database
May need to be moved elsewhere
*/
    // TODO
    public void saveNewRecipe(){ }



    /////////////////////////////////////
    // TESTING HAPPENS DOWN HERE //


    public static void main(String[] args) throws Exception {

        //Recipe r = new Recipe("recipe_folder/recipe_0001.txt");

        Recipe recipe_from_json = new Recipe("recipe_folder/recipe_0001.json");
        System.out.println(recipe_from_json.toString());


       /*
       Recipe r2 = new Recipe("recipe_folder/recipe_0002.txt");
        BuildJsonFile(r2, "recipe_folder");
        */


        /*
        ArrayList<Ingredient> ilist = new ArrayList<>();

        Ingredient cheese = new Ingredient("cheese","5534",8,"lbs");
        Ingredient rice = new Ingredient("rice","7881",5,"kgs");
        Ingredient milk = new Ingredient("milk","2003",9000,"mL");
        Ingredient butter = new Ingredient("butter","2001",20,"oz");
        Ingredient sardines = new Ingredient("blended sardines","0019",60,"fl.oz");

        ilist.add(cheese);
        ilist.add(rice);
        ilist.add(milk);
        ilist.add(butter);
        ilist.add(sardines);

        Recipe nuR = new Recipe("idek", "1009",ilist);

        BuildJsonFile(nuR,"recipe_folder");
        */

        // boolean b0 = JsonExists("nuFolder","9190");
        //  boolean b1 = JsonExists("recipe_folder", "0004");
        //  boolean b2 = JsonExists("recipe_folder/","0002");
        //  boolean b3 = JsonExists("recipe_folder/recipe_0001.txt","0001");

    }
}

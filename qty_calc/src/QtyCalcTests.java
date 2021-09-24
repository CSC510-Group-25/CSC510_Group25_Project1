package com.company;


import static com.company.BuildFile.BuildJsonFile;
import static org.junit.Assert.*;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import org.junit.*;

import java.io.IOException;
import java.util.*;


/**
 * Class for unit tests.
 *
 * Unimplemented methods will not be tested.
 */
public class QtyCalcTests {

    // tests the ingredient and recipe constructors
    @Test public void T0(){

        try {
            ArrayList<Ingredient> thing1 = new ArrayList<>();

            // construct ingredients
            Ingredient cheese = new Ingredient("cheese", "5534", 8, "lbs");
            Ingredient rice = new Ingredient("rice", "7881", 5, "kgs");
            Ingredient milk = new Ingredient("milk", "2003", 9000, "mL");
            Ingredient butter = new Ingredient("butter", "2001", 20, "oz");
            Ingredient sardines = new Ingredient("blended sardines", "0019", 60, "fl.oz");

            thing1.add(cheese);
            thing1.add(rice);
            thing1.add(milk);
            thing1.add(butter);
            thing1.add(sardines);

            Recipe r1 = new Recipe("horrific mush", "0001", thing1);
            Recipe r1t = new Recipe("recipe_folder/recipe_0001.txt");

            assertEquals(r1.toString(), r1t.toString());
        }catch (Exception e){
            fail("T0 fail, exception found");
            e.printStackTrace();
        }
    }

    // tests if the jsonobject constructed by the recipe matched the recipe string
    @Test public void T1() {

        try {
            Recipe r1t = new Recipe("recipe_folder/recipe_0001.txt");
            JsonObject r1_jo = r1t.getRecipeJson();

            // construct from JsonObject
            Recipe r1_from_jo = new Recipe(r1_jo);

            String r1t_str = r1t.toString();
            String r1_jostr = r1_from_jo.toString();

            assertEquals(r1t_str,r1_jostr); // somehow print message

            // check if the recipe is properly saved as .json
            BuildJsonFile(r1t, "recipe_folder");
            Recipe r1j = new Recipe("recipe_folder/recipe_0001.json");

            String r1j_str = r1j.toString();

            assertEquals(r1t_str,r1j_str);
            assertEquals(r1_jostr,r1j_str);

        }catch (Exception e){
            fail("T1 fail, found exception");
            e.printStackTrace();
        }
    }

    @Test public void T2() {

        try {
            //recipes from .txt
            Recipe r1 = new Recipe("recipe_folder/recipe_0001.txt");
            Recipe r9 = new Recipe("recipe_folder/recipe_9000.txt");
            Recipe r8 = new Recipe("recipe_folder/recipe_8000.txt");

            BuildJsonFile(r1, "recipe_folder");
            BuildJsonFile(r9, "recipe_folder");
            BuildJsonFile(r8, "recipe_folder");

            // recipes from .json
            Recipe r1j = new Recipe("recipe_folder/recipe_0001.json");
            Recipe r8j = new Recipe("recipe_folder/recipe_8000.json");
            Recipe r9j = new Recipe("recipe_folder/recipe_9000.json");

            // test if r1j == r1, etc

        }catch (Exception e){
            fail("T2 fail, found exception");
            e.printStackTrace();
        }

    }


    //TEST ORDER, ORDERTRACKER, MOCKDB, THEN QCALC
    @Test public void T3() {

        try {
            //recipes from .txt
            Recipe r1 = new Recipe("recipe_folder/recipe_0001.txt");
            Recipe r9 = new Recipe("recipe_folder/recipe_9000.txt");
            Recipe r8 = new Recipe("recipe_folder/recipe_8000.txt");

            BuildJsonFile(r1, "recipe_folder");
            BuildJsonFile(r9, "recipe_folder");
            BuildJsonFile(r8, "recipe_folder");

            // recipes from .json
            Recipe r1j = new Recipe("recipe_folder/recipe_0001.json");
            Recipe r8j = new Recipe("recipe_folder/recipe_8000.json");
            Recipe r9j = new Recipe("recipe_folder/recipe_9000.json");


        }catch (Exception e){
            fail("T3 fail, found exception");
            e.printStackTrace();
        }

    }






}

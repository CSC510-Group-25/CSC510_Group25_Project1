package com.company;

import static org.junit.Assert.*;

import com.github.cliftonlabs.json_simple.JsonException;
import org.junit.*;

import java.io.IOException;
import java.util.*;

/**
 * Tests for the Recipe and Ingredient classes
 */

public class RI_Tests {

    //TODO
    @Test
    public void test_ingredient_constructors(){

    }

    //TODO
    @Test
    public void test_ingredient_toString(){

    }



    //TODO
    @Test
    public void test_ingredient_toJSON(){

    }


    //TODO
    @Test
    public void test_ingredient_readerJSON(){

    }


    // tests .txt file reading
    // also tests Recipe class
    //TODO: FIX ME I'M A WRECK
    @Test
    public void test_ingredient_readerTXT1() throws IOException, JsonException {

        // public Ingredient(String ingredientName, String dbID, double local_qty, String local_unit)

        ArrayList<String> thing1 = new ArrayList<>();

        Ingredient cheese = new Ingredient("cheese","5534",8,"lbs");
        Ingredient rice = new Ingredient("rice","7881",5,"kgs");
        Ingredient milk = new Ingredient("milk","2003",9000,"mL");
        Ingredient butter = new Ingredient("butter","2001",20,"oz");
        Ingredient sardines = new Ingredient("blended sardines","0019",60,"fl.oz");

        /*String cheese = new Ingredient("cheese","5534",8,"lbs").toString();
        String rice = new Ingredient("rice","7781",5,"kgs").toString();
        String milk = new Ingredient("milk","2003",9000,"mL").toString();
        String butter = new Ingredient("butter","2001",20,"oz").toString();
        String sardines = new Ingredient("blended sardines","0019",60,"fl.oz").toString();*/

        thing1.add(cheese.toString());
        thing1.add(rice.toString());
        thing1.add(milk.toString());
        thing1.add(butter.toString());
        thing1.add(sardines.toString());

        String[] r1 = thing1.toArray(new String[0]);

        Recipe r = new Recipe("recipe_folder/recipe_0001.txt");

        ArrayList<String> thing2 = new ArrayList<>();

        ArrayList<Ingredient> rlist = r.getIngredientList();

        for(Ingredient ing : rlist){
            String thing3 = ing.toString();
            thing2.add(thing3);

        }

        assertEquals(thing2.size(), r.getIngredientList().size());
        // just for good measure
        assertEquals(thing1.size(), thing2.size());

        boolean yay = true;
        for(String str : thing2){

            boolean found=false;
            for(int i=0;i<r1.length; i++){
                if (str.equals(r1[i])){
                    found=true;
                    break;
                }
            }

            if (!found){
                yay=false;
                break;
            }
        }
        assertEquals(true, yay);
    }

    // BELOW THIS LINE ARE TESTS FOR THE RECIPE CLASS
    ////////////////////////////////////////////////////////



}

//package com.qtycalc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DOES NOT INCLUDE SAVING/READING FILES
 */
class RecipeTests {

    @Test
    void Recipe_isEqual_toString_tests() {
        Recipe r1 = constructRecipe1();
        Recipe r2 = constructRecipe1();
        Recipe r3 = constructRecipe2();

        assertEquals(r1.toString(),r2.toString());
        assertNotEquals(r1.toString(),r3.toString());

        assertTrue(r1.isEqual(r2));
        assertFalse(r1.isEqual(r3));
    }

    @Test
    public void addToRecipeTest_duplicate(){
        ArrayList<Ingredient> thing1 = generateList1();
        Recipe r1 = new Recipe("horrific mush", "0001", thing1);
        Ingredient duplicate = new Ingredient("cheese", "5534", 8, "lbs");
        boolean badAdd = r1.addIngredient(duplicate);
        assertFalse(badAdd);
    }

    @Test
    public void addToRecipeTest_badID(){
        ArrayList<Ingredient> thing1 = generateList1();
        Recipe r1 = new Recipe("horrific mush", "0001", thing1);
        Ingredient badID = new Ingredient("dogfood", "5534", 8, "lbs");
        boolean badAdd = r1.addIngredient(badID);
        assertFalse(badAdd);
    }

    @Test
    public void addToRecipeTest_good(){
        ArrayList<Ingredient> thing1 = generateList1();
        Recipe r1 = new Recipe("horrific mush", "0001", thing1);

        String old = r1.toString();

        Ingredient nuIng = new Ingredient("dogfood", "6789", 8, "lbs");
        boolean goodAdd = r1.addIngredient(nuIng);
        assertTrue(goodAdd);

        String nu = r1.toString();
        assertNotEquals(old, nu);
    }

    @Test
    void Recipe_remove_IngredientTests() {
        Recipe r1 = constructRecipe1();

        String old = r1.toString();

        Ingredient nuIng = new Ingredient("dogfood", "6789", 8, "lbs");
        assertFalse(r1.removeIngredient(nuIng));

        Ingredient cheese = new Ingredient("cheese", "5534", 8, "lbs");
        assertTrue(r1.removeIngredient(cheese));

        String nu = r1.toString();
        assertNotEquals(old, nu);
    }


    /// HELPERS

    private ArrayList<Ingredient> generateList1(){
        // construct ingredients
        ArrayList<Ingredient> thing1 = new ArrayList<>();

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

        return thing1;
    }

    private Recipe constructRecipe1(){

        ArrayList<Ingredient> thing1 = new ArrayList<>();

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

        return new Recipe("recipe1","1111",thing1);
    }

    private Recipe constructRecipe2() {

        ArrayList<Ingredient> thing1 = new ArrayList<>();

        Ingredient t1 = new Ingredient("thing1", "1234", 1, "lbs");
        Ingredient t2 = new Ingredient("thing2", "5678", 2, "kgs");
        Ingredient t3 = new Ingredient("thing3", "1098", 3, "mL");

        thing1.add(t1);
        thing1.add(t2);
        thing1.add(t3);

        return new Recipe("recipe2", "2222", thing1);
    }
}
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;

/*
 * MIT License
 *
 * Copyright (c) 2021. CSC510-Group-25
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**************************
 * Originally authored by: Asha Khatri
 * GitHub ID: ashakhatri007
 *
 * CSC510 Fall 2021
 * North Carolina State University
 *
 * File name: RecipeTests.java
 **************************/

/**
 * Class for unit tests.
 *
 * Unimplemented methods will not be tested.
 */

 public class RecipeTests {

     // in case we need to capture printed output
     private ByteArrayOutputStream output;
     private final InputStream stdIn = System.in;
     private final PrintStream stdOut = System.out;
     Util util = new Util();
 
     //@Before
     public void setupIO() {
         // to help manage IO
         output = new ByteArrayOutputStream();
         System.setOut(new PrintStream(output));
     }
 
     //@After
     public void restoreIO() {
         // restores standard input/output
         System.setIn(stdIn);
         System.setOut(stdOut);
     }
   
     @Test
    public void Recipe_isEqual_toString_tests() {
        Recipe r1 = util.constructRecipe1();
        Recipe r2 = util.constructRecipe1();
        Recipe r3 = util.constructRecipe2();

        assertEquals(r1.toString(),r2.toString());
        assertNotEquals(r1.toString(),r3.toString());

        assertTrue(r1.isEqual(r2));
        assertFalse(r1.isEqual(r3));
    }

    @Test
    public void addToRecipeTest_duplicate(){
        ArrayList<Ingredient> thing1 = util.generateList1();
        Recipe r1 = new Recipe("horrific mush", "0001", thing1);
        Ingredient duplicate = new Ingredient("cheese", "5534", 8, "lbs");
        boolean badAdd = r1.addIngredient(duplicate);
        assertFalse(badAdd);
    }

    @Test
    public void addToRecipeTest_badID(){
        ArrayList<Ingredient> thing1 = util.generateList1();
        Recipe r1 = new Recipe("horrific mush", "0001", thing1);
        Ingredient badID = new Ingredient("dogfood", "5534", 8, "lbs");
        boolean badAdd = r1.addIngredient(badID);
        assertFalse(badAdd);
    }

    @Test
    public void addToRecipeTest_good(){
        ArrayList<Ingredient> thing1 = util.generateList1();
        Recipe r1 = new Recipe("horrific mush", "0001", thing1);

        String old = r1.toString();

        Ingredient nuIng = new Ingredient("dogfood", "6789", 8, "lbs");
        boolean goodAdd = r1.addIngredient(nuIng);
        assertTrue(goodAdd);

        String nu = r1.toString();
        assertNotEquals(old, nu);
    }

    @Test
    public void Recipe_remove_IngredientTests() {
        Recipe r1 = util.constructRecipe1();

        String old = r1.toString();

        Ingredient nuIng = new Ingredient("dogfood", "6789", 8, "lbs");
        assertFalse(r1.removeIngredient(nuIng));

        Ingredient cheese = new Ingredient("cheese", "5534", 8, "lbs");
        assertTrue(r1.removeIngredient(cheese));

        String nu = r1.toString();
        assertNotEquals(old, nu);
    }

    // tests if the constructed .json matches the originally created recipe
    @Test
    public void saveRecipeTest() throws Exception {

        ArrayList<Ingredient> thing1 = util.generateList1();
        Recipe r1 = new Recipe("test recipe", "1234", thing1);

        if(BuildFile.RecipeExists("1234")){
            setupIO();
            String input = "y";
            ByteArrayInputStream passIn = new ByteArrayInputStream(input.getBytes());
            System.setIn(passIn);
            r1.saveRecipeAsJson("recipe_folder");
            restoreIO();
        }
        else {
            r1.saveRecipeAsJson("recipe_folder");
            assertTrue(BuildFile.RecipeExists("1234"));
        }

        String nuPath = "recipe_folder" + File.separator + "recipe_1234.json";
        Recipe rjson = new Recipe(nuPath);
        assertTrue(r1.isEqual(rjson));
    }

    @Test
    public void testSaveJsonObject() throws IOException, JsonException {

        ArrayList<Ingredient> thing1 = util.generateList1();
        Recipe r1 = new Recipe("savejsontest", "0567", thing1);

        JsonObject rjo = r1.getRecipeJson();

        if(BuildFile.RecipeExists("0567")){
            setupIO();
            String input = "y";
            ByteArrayInputStream passIn = new ByteArrayInputStream(input.getBytes());
            System.setIn(passIn);
            BuildFile.SaveRecipeJson(rjo, "recipe_folder");
            restoreIO();
        }
        else {
            BuildFile.SaveRecipeJson(rjo, "recipe_folder");
        }

        Recipe recipe_from_jo = new Recipe(rjo);
        String rjost = r1.toString();
        String jostr = recipe_from_jo.toString();

        assertTrue(BuildFile.RecipeExists("0567"));
        assertEquals(rjost,jostr);
    }
 
}

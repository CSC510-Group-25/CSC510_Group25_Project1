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
 * File name: IngredientTests.java
 **************************/

/**
 * Class for unit tests.
 *
 * Unimplemented methods will not be tested.
 */

 public class IngredientTests {

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
     public void ingredientAsJsontest() {
 
         Ingredient ing = new Ingredient("cheese", "5534", 8, "lbs");
         JsonObject jo = ing.ingredientAsJson();
         String name = (String) jo.get("ingredientName");
         String id = (String) jo.get("ing_DBID");
         String qty = (String) jo.get("local_qty").toString();
         String unit = (String) jo.get("local_unit");
 
         assertEquals("cheese",name);
         assertEquals("5534",id);
         assertEquals("8.0",qty);
         assertEquals("lbs",unit);
     }
 
     @Test
     public void Ingredient_getters() {
         Ingredient ing = new Ingredient("cheese", "5534", 8, "lbs");
         assertEquals("cheese",ing.getIngredientName());
         assertEquals("5534",ing.getDbID());
         assertEquals("8.0",String.valueOf(ing.getLocal_qty()));
         assertEquals("lbs",ing.getLocal_unit());
         assertNotEquals(null,ing.getIngredientJson());
     }
 
     @Test
     public void Ingredient_isEqual() {
         Ingredient ing1 = new Ingredient("cheese", "5534", 8, "lbs");
         Ingredient ing2 = new Ingredient("cheese", "5534", 8, "lbs");
         Ingredient ing3 = new Ingredient("rice", "7881", 5, "kgs");
         assertTrue(ing1.isEqual(ing2));
         assertFalse(ing1.isEqual(ing3));
     }
 
}

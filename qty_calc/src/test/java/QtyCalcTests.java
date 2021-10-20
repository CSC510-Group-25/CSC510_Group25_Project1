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
 * Originally authored by: Leila Moran
 * GitHub ID: snapcat
 *
 * CSC510 Fall 2021
 * North Carolina State University
 *
 * File name: QtyCalcTests.java
 **************************/

/**
 * Class for unit tests.
 *
 * Unimplemented methods will not be tested.
 */
public class QtyCalcTests {

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


    ///// INGREDIENT TESTS

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

    ////// ITEM TESTS

    @Test
    public void itemAsJsonTest() {
        Item i1 = new Item("thing1","9891",1000.0,"fl.oz");

        JsonObject jo = i1.itemAsJson();

        String name = (String) jo.get("name");
        String dbid = (String) jo.get("ID");
        String qty = (String) jo.get("quantity").toString();
        String dbu = (String) jo.get("unit");

        assertEquals("thing1",name);
        assertEquals("9891",dbid);
        assertEquals("1000.0",qty);
        assertEquals("fl.oz",dbu);
    }

    @Test
    public void Item_toString() {
        Item i1 = new Item("thing1","9891",1000.0,"fl.oz");
        Item i2 = new Item("thing1","9891",1000.0,"fl.oz");
        Item i3 = new Item("thing3","1234",100.0,"liters");
        assertTrue(i1.isEqual(i2));
        assertFalse(i1.isEqual(i3));
    }

    @Test
    public void Item_getters() {
        Item i1 = new Item("thing1","9891",1000.0,"fl.oz");
        assertEquals("thing1",i1.getItemName());
        assertEquals("9891",i1.getDbID());
        assertEquals("1000.0",i1.getQty().toString());
        assertEquals("fl.oz",i1.getDbUnit());
    }

    /////// RECIPE TESTS

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


    ///// ORDER TESTS

    @Test
    public void Order_getters() {
        Order o1 = new Order(util.constructRecipe1(),10,10);

        assertEquals("recipe1",o1.getRecipe().getRecipeName());
        assertEquals(10,o1.getNum_orders());
        assertEquals(10,o1.getNum_failures());
    }

    @Test
    public void Order_toString() {
        String thing = "recipe1: ID 1111\nNumber of successful orders: 10\nNumber of failed orders: 10";
        Order o = new Order(util.constructRecipe1(),10,10);

        assertEquals(thing,o.toString());
    }

    @Test
    public void Order_isEqual(){
        Order o1 = new Order(util.constructRecipe1(),10,10);
        Order o2 = new Order(util.constructRecipe1(),10,10);
        Order o3 = new Order(util.constructRecipe2(),5,5);

        assertTrue(o1.isEqual(o2));
        assertFalse(o1.isEqual(o3));
        assertFalse(o1.isEqual(10));
        assertFalse(o1.isEqual(null));
    }


    /////////////ORDERTRACKER TESTS

    @Test
    public void createOrder() {

        Recipe r1 = util.constructRecipe1();
        OrderTracker ot = new OrderTracker();
        
        setupIO();
        assertFalse(ot.createOrder(null,0,0));
        assertFalse(ot.createOrder(null,1,0));
        assertFalse(ot.createOrder(r1,0,0));
        assertFalse(ot.createOrder(r1,-1,0));
        assertTrue(ot.createOrder(r1,10,10));
        restoreIO();
    }

    @Test
    public void getOrders() {
        OrderTracker ot = new OrderTracker();
        ot.createOrder(util.constructRecipe1(),10,10);
        ot.createOrder(util.constructRecipe2(), 5, 0);

        HashMap<String, Order> orders = ot.getOrders();

        assertEquals("1111",orders.get("1111").getRecipe().getRecipeID());
        assertEquals("2222",orders.get("2222").getRecipe().getRecipeID());
    }

    @Test
    public void getKeys() {
        OrderTracker ot = new OrderTracker();
        ot.createOrder(util.constructRecipe1(),10,10);
        ot.createOrder(util.constructRecipe2(), 5, 0);

        ArrayList<String> keys = ot.getKeys();

        assertTrue(keys.contains("1111"));
        assertTrue(keys.contains("2222"));
    }

    @Test
    public void getOrdersArray() {
        OrderTracker ot = new OrderTracker();
        ot.createOrder(util.constructRecipe1(),10,10);
        ot.createOrder(util.constructRecipe2(), 5, 0);

        ArrayList<Order> os = ot.getOrdersArray();

        Order o1 = new Order(util.constructRecipe1(),10,10);
        Order o2 = new Order(util.constructRecipe2(),5,0);

        for(Order o : os){
            boolean thing = (o.isEqual(o1) || o.isEqual(o2));
            assertTrue(thing);
        }
    }

    // @Test void removeOrderByID() { }

    @Test
    public void OrderTracker_toString() {
        OrderTracker ot = new OrderTracker();
        ot.createOrder(util.constructRecipe1(),10,10);
        ot.createOrder(util.constructRecipe2(), 5, 0);
        String thing = "recipe1: ID 1111\nNumber of successful orders: 10\nNumber of failed orders: 10\n" +
                "recipe2: ID 2222\nNumber of successful orders: 5\nNumber of failed orders: 0";

        assertEquals(thing,ot.toString());
    }
    

    //////////////////

    // test adding to MockDB
    @Test
    public void MockDB_test_add(){
        Item i1 = new Item("cheese","5534",100.0,"lbs");
        MockDB testDB = new MockDB();
        assertTrue(testDB.addItem(i1));
        assertEquals("[cheese, 5534, 100.0, lbs]",testDB.toString());
    }

    // tests removing an item from the db
    @Test
    public void MockDB_test_remove(){
        Item i1 = new Item("cheese","5534",100.0,"lbs");
        MockDB testDB = util.constructMDB();

        String str1 = testDB.toString();

        setupIO();
        String input = "y";
        ByteArrayInputStream passIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(passIn);
        assertTrue(testDB.removeItem(i1));
        restoreIO();

        assertNotEquals(str1,testDB.toString());
    }


    @Test
    public void MockDB_test_fromTxt() throws IOException, JsonException {
        // String nuPath = "mock_dbs" + File.separator + "db1.json";
        String nuPath = "mock_dbs" + File.separator + "db1.txt";
        MockDB mdb = new MockDB(nuPath);
        MockDB tdb = util.constructMDB();
        assertEquals(mdb.toString(),tdb.toString());
    }

    // tests saving to json and building from json
    @Test
    public void MockDB_test_saveJson() throws IOException, JsonException {
        String nuPath = "mock_dbs" + File.separator + "db1.txt";
        MockDB mdb = new MockDB(nuPath);

        setupIO();

        mdb.saveAsJson("mock_dbs","db1.json");
        String jPath = "mock_dbs" + File.separator + "db1.json";
        MockDB jdb = new MockDB(jPath);

        restoreIO();

        assertEquals(mdb.toString(),jdb.toString());
    }

}

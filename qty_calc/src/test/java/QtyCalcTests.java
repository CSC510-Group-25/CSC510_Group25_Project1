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

    ////////// UNIT CONVERSION TESTS //////////

    @Test
    public void convertTo_testSame() {
        // msg1: "equal units, no conversion needed";
        assertEquals("msg1", UnitConverter.convertTo(1, "grams", "grams"));
        assertEquals("msg1", UnitConverter.convertTo(1, "kgs", "kgs"));
        assertEquals("msg1", UnitConverter.convertTo(1, "lbs", "lbs"));
        assertEquals("msg1", UnitConverter.convertTo(1, "oz", "oz"));
        assertEquals("msg1", UnitConverter.convertTo(1, "mL", "mL"));
        assertEquals("msg1", UnitConverter.convertTo(1, "liters", "liters"));
        assertEquals("msg1", UnitConverter.convertTo(1, "fl.oz", "fl.oz"));
        assertEquals("msg1", UnitConverter.convertTo(1, "gallons", "gallons"));
    }

    // tests routing AND liquid/dry handling
    @Test
    public void convertTo_test_oz() {
        String to_oz = UnitConverter.convertTo(1,"grams","oz");
        double val = Double.parseDouble(to_oz);
        String result = String.format("%.5f", val);
        assertEquals("0.03527",result);

        to_oz = UnitConverter.convertTo(1, "lbs", "oz");
        assertEquals("16.0",to_oz);

        to_oz = UnitConverter.convertTo(1, "kgs", "oz");
        assertEquals("35.274",to_oz);

        to_oz = UnitConverter.convertTo(1, "cups liquid", "oz");
        assertEquals("msg2",to_oz);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;

        to_oz = UnitConverter.convertTo(1, "cups dry", "oz");
        assertEquals("msg3",to_oz);
        // msg3: "NYI"

        to_oz = UnitConverter.convertTo(1, "mL", "oz");
        assertEquals("msg2",to_oz);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
    }

    // test to ensure correct routing in convertTo
    @Test
    public void convertTo_test_routing() {

        String to_oz = UnitConverter.convertTo(1, "lbs", "oz");
        assertEquals("16.0",to_oz);

        String to_lbs = UnitConverter.convertTo(16,"oz","lbs");
        assertEquals("1.0",to_lbs);

        String to_kg = UnitConverter.convertTo(1000, "grams", "kgs");
        assertEquals("1.0",to_kg);

        String to_g = UnitConverter.convertTo(1, "kgs", "grams");
        assertEquals("1000.0",to_g);

        String to_mL = UnitConverter.convertTo(1, "liters", "mL");
        assertEquals("1000.0",to_mL);

        String to_liters = UnitConverter.convertTo(1000, "mL", "liters");
        assertEquals("1.0",to_liters);

        String to_floz = UnitConverter.convertTo(1, "gallons", "fl.oz");
        assertEquals("128.0",to_floz);

        String to_gal = UnitConverter.convertTo(128, "fl.oz", "gallons");
        assertEquals("1.0",to_gal);
    }

    /////// WEIGHT CONVERSION TESTS //////

    @Test
    public void g_to_g(){
        String gtg = UnitConverter.grams(1,"grams");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }
    @Test
    public void g_to_oz() {
        String to_oz = UnitConverter.oz(1,"grams");
        double goz = Double.parseDouble(to_oz);
        String result = String.format("%.5f", goz);
        assertEquals("0.03527",result);
    }
    @Test
    public void g_to_kgs() {
        String to_kgs = UnitConverter.kgs(1000,"grams");
        assertEquals("1.0",to_kgs);
    }
    @Test
    public void g_to_lbs() {
        String to_lbs = UnitConverter.lbs(1,"grams");
        double val = Double.parseDouble(to_lbs);
        String result = String.format("%.5f", val);
        assertEquals("0.00220",result);
    }
    @Test
    public void oz_to_g() {
        String to_g = UnitConverter.grams(1,"oz");
        assertEquals("28.35",to_g);
    }
    @Test
    public void oz_to_oz(){
        String gtg = UnitConverter.oz(1,"oz");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }
    @Test
    public void oz_to_lbs() {
        String to_lbs = UnitConverter.lbs(16,"oz");
        // double tolbs = Double.parseDouble(to_lbs);
        assertEquals("1.0",to_lbs);
    }
    @Test
    public void oz_to_kgs() {
        String to_kgs = UnitConverter.kgs(10,"oz");
        double tok = Double.parseDouble(to_kgs);
        String result = String.format("%.5f", tok);
        assertEquals("0.28349",result);
    }
    @Test
    public void lbs_to_oz() {
        String to_oz = UnitConverter.oz(1,"lbs");
        assertEquals("16.0",to_oz);
    }
    @Test
    public void lbs_to_g() {
        String to_g = UnitConverter.grams(1,"lbs");
        assertEquals("454.0",to_g);
    }
    @Test
    public void lbs_to_lbs(){
        String gtg = UnitConverter.lbs(1,"lbs");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }
    @Test
    public void lbs_to_kgs() {
        String to_kgs = UnitConverter.kgs(1,"lbs");
        double tok = Double.parseDouble(to_kgs);
        String result = String.format("%.5f", tok);
        assertEquals("0.45351",result);
    }
    @Test
    public void kgs_to_oz() {
        String to_oz = UnitConverter.oz(1,"kgs");
        assertEquals("35.274",to_oz);
    }
    @Test
    public void kgs_to_g() {
        String to_g = UnitConverter.grams(1,"kgs");
        assertEquals("1000.0",to_g);
    }
    @Test
    public void kgs_to_lbs(){
        String to_lbs = UnitConverter.lbs(1,"kgs");
        assertEquals("2.205",to_lbs);
    }
    @Test
    public void kgs_to_kgs(){
        String gtg = UnitConverter.kgs(1,"kgs");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }

    ////// THESE CONVERSIONS HAVE NOT BEEN IMPLEMENTED YET //////
    @Test
    public void tsp_to_kgs(){
        String gtg = UnitConverter.kgs(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tsp_to_g(){
        String gtg = UnitConverter.grams(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tsp_to_lbs(){
        String gtg = UnitConverter.lbs(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tsp_to_oz(){
        String gtg = UnitConverter.oz(1,"tsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tbsp_to_kgs(){
        String gtg = UnitConverter.kgs(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tbsp_to_g(){
        String gtg = UnitConverter.grams(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tbsp_to_lbs(){
        String gtg = UnitConverter.lbs(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void tbsp_to_oz(){
        String gtg = UnitConverter.oz(1,"tbsps");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void cup_to_kgs(){
        String gtg = UnitConverter.kgs(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void cup_to_g(){
        String gtg = UnitConverter.grams(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void cup_to_lbs(){
        String gtg = UnitConverter.lbs(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }
    @Test
    public void cup_to_oz(){
        String gtg = UnitConverter.oz(1,"cups");
        assertEquals("msg2",gtg);
        // msg2: "cannot convert from: " + local_unit + " to: " + db_unit;
        // msg3: "NYI"
    }

    ///// VOLUME CONVERSION TESTS ////////

    @Test
    public void tsp_to_mL(){
        String convert = UnitConverter.mL(1,"tsps");
        assertEquals("4.929",convert);
    }
    @Test
    public void tsp_to_floz(){
        String convert = UnitConverter.floz(6,"tsps");
        assertEquals("1.0",convert);
    }
    @Test
    public void tsp_to_gal(){
        String convert = UnitConverter.gal(100,"tsps");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.13021",result);
    }

    @Test
    public void tsp_to_liter(){
        String convert = UnitConverter.liters(100,"tsps");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.49261",result);
    }
    @Test
    public void tbsp_to_mL(){
        String convert = UnitConverter.mL(1,"tbsps");
        assertEquals("14.787",convert);
    }
    @Test
    public void tbsp_to_floz(){
        String convert = UnitConverter.floz(1,"tbsps");
        assertEquals("0.5",convert);
    }
    @Test
    public void tbsp_to_gal(){
        String convert = UnitConverter.gal(256,"tbsps");
        assertEquals("1.0",convert);
    }
    @Test
    public void tbsp_to_liter(){
        String convert = UnitConverter.liters(1,"tbsps");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.01479",result);
    }
    @Test
    public void cup_to_mL(){
        String convert = UnitConverter.mL(1,"cups");
        assertEquals("237.0",convert);
    }
    @Test
    public void cup_to_floz(){
        String convert = UnitConverter.floz(1,"cups");
        assertEquals("8.0",convert);
    }
    @Test
    public void cup_to_gal(){
        String convert = UnitConverter.gal(1,"cups");
        assertEquals("0.0625",convert);
    }
    @Test
    public void cup_to_liter(){
        String convert = UnitConverter.liters(1,"cups");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.23657",result);
    }
    @Test
    public void pint_to_mL(){
        String convert = UnitConverter.mL(1,"pints");
        assertEquals("568.0",convert);
    }
    @Test
    public void pint_to_floz(){
        String convert = UnitConverter.floz(1,"pints");
        assertEquals("16.0",convert);
    }
    @Test
    public void pint_to_gal(){
        String convert = UnitConverter.gal(1,"pints");
        assertEquals("0.125",convert);
    }
    @Test
    public void pint_to_liter(){
        String convert = UnitConverter.liters(1,"pints");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.47326",result);
    }
    @Test
    public void quart_to_mL(){
        String convert = UnitConverter.mL(1,"quarts");
        assertEquals("946.0",convert);
    }

    @Test
    public void quart_to_floz(){
        String convert = UnitConverter.floz(1,"quarts");
        assertEquals("32.0",convert);
    }

    @Test
    public void quart_to_gal(){
        String convert = UnitConverter.gal(1,"quarts");
        assertEquals("0.25",convert);
    }

    @Test
    public void quart_to_liter(){
        String convert = UnitConverter.liters(1,"quarts");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.94607",result);
    }

    @Test
    public void floz_to_mL(){
        String convert = UnitConverter.mL(1,"fl.oz");
        assertEquals("29.574",convert);
    }

    @Test
    public void floz_to_floz(){
        String gtg = UnitConverter.floz(1,"fl.oz");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }

    @Test
    public void floz_to_gal(){
        String convert = UnitConverter.gal(128,"fl.oz");
        assertEquals("1.0",convert);
    }
    @Test
    public void floz_to_liter(){
        String convert = UnitConverter.liters(1,"fl.oz");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.02957",result);
    }

    @Test
    public void gal_to_mL(){
        String convert = UnitConverter.mL(1,"gallons");
        assertEquals("3785.0",convert);
    }
    @Test
    public void gal_to_floz(){
        String convert = UnitConverter.floz(1,"gallons");
        assertEquals("128.0",convert);
    }

    @Test
    public void gal_to_gal(){
        String gtg = UnitConverter.gal(1,"gallons");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }

    @Test
    public void gal_to_liter(){
        String convert = UnitConverter.liters(1,"gallons");
        assertEquals("3.785",convert);
    }

    @Test
    public void liter_to_mL(){
        String convert = UnitConverter.mL(1,"liters");
        assertEquals("1000.0",convert);
    }

    @Test
    public void liter_to_floz(){
        String convert = UnitConverter.floz(1,"liters");
        assertEquals("33.814",convert);
    }
    @Test
    public void liter_to_gal(){
        String convert = UnitConverter.gal(1,"liters");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.26420",result);
    }

    @Test
    public void liter_to_liter(){
        String gtg = UnitConverter.liters(1,"liters");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }

    @Test
    public void mL_to_mL(){
        String gtg = UnitConverter.mL(1,"mL");
        assertEquals("msg1",gtg);
        // msg1: "equal units, no conversion needed";
    }

    @Test
    public void mL_to_floz(){
        String convert = UnitConverter.floz(10,"mL");
        double val = Double.parseDouble(convert);
        String result = String.format("%.5f", val);
        assertEquals("0.33813",result);
    }
    @Test
    public void mL_to_gal(){
        String convert = UnitConverter.gal(4000,"mL");
        double val = Double.parseDouble(convert);
        String result = String.format("%.4f", val);
        assertEquals("1.0568",result);
    }

    @Test
    public void mL_to_liter(){
        String convert = UnitConverter.liters(1000,"mL");
        assertEquals("1.0",convert);
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
    public void Recipe_remove_IngredientTests() {
        Recipe r1 = constructRecipe1();

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

        ArrayList<Ingredient> thing1 = generateList1();
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

        ArrayList<Ingredient> thing1 = generateList1();
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
        Order o1 = new Order(constructRecipe1(),10,10);

        assertEquals("recipe1",o1.getRecipe().getRecipeName());
        assertEquals(10,o1.getNum_orders());
        assertEquals(10,o1.getNum_failures());
    }

    @Test
    public void Order_toString() {
        String thing = "recipe1: ID 1111\nNumber of successful orders: 10\nNumber of failed orders: 10";
        Order o = new Order(constructRecipe1(),10,10);

        assertEquals(thing,o.toString());
    }

    @Test
    public void Order_isEqual(){
        Order o1 = new Order(constructRecipe1(),10,10);
        Order o2 = new Order(constructRecipe1(),10,10);
        Order o3 = new Order(constructRecipe2(),5,5);

        assertTrue(o1.isEqual(o2));
        assertFalse(o1.isEqual(o3));
        assertFalse(o1.isEqual(10));
        assertFalse(o1.isEqual(null));
    }


    /////////////ORDERTRACKER TESTS

    @Test
    public void createOrder() {

        Recipe r1 = constructRecipe1();
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
        ot.createOrder(constructRecipe1(),10,10);
        ot.createOrder(constructRecipe2(), 5, 0);

        HashMap<String, Order> orders = ot.getOrders();

        assertEquals("1111",orders.get("1111").getRecipe().getRecipeID());
        assertEquals("2222",orders.get("2222").getRecipe().getRecipeID());
    }

    @Test
    public void getKeys() {
        OrderTracker ot = new OrderTracker();
        ot.createOrder(constructRecipe1(),10,10);
        ot.createOrder(constructRecipe2(), 5, 0);

        ArrayList<String> keys = ot.getKeys();

        assertTrue(keys.contains("1111"));
        assertTrue(keys.contains("2222"));
    }

    @Test
    public void getOrdersArray() {
        OrderTracker ot = new OrderTracker();
        ot.createOrder(constructRecipe1(),10,10);
        ot.createOrder(constructRecipe2(), 5, 0);

        ArrayList<Order> os = ot.getOrdersArray();

        Order o1 = new Order(constructRecipe1(),10,10);
        Order o2 = new Order(constructRecipe2(),5,0);

        for(Order o : os){
            boolean thing = (o.isEqual(o1) || o.isEqual(o2));
            assertTrue(thing);
        }
    }

    // @Test void removeOrderByID() { }

    @Test
    public void OrderTracker_toString() {
        OrderTracker ot = new OrderTracker();
        ot.createOrder(constructRecipe1(),10,10);
        ot.createOrder(constructRecipe2(), 5, 0);
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
        MockDB testDB = constructMDB();

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
        MockDB tdb = constructMDB();
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

    ///// HELPERS

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

    private Order constructOrder1(){
        return new Order(constructRecipe1(),10,10);
    }

    private Order constructOrder2(){

        return new Order(constructRecipe2(),5,0);
    }

    private MockDB constructMDB(){

        Item i1 = new Item("cheese","5534",100.0,"lbs");
        Item i2 = new Item("rice","7881",100.0,"kgs");
        Item i3 = new Item("milk","2003",100.0,"liters");
        Item i4 = new Item("butter","2001",100.0,"lbs");
        Item i5 = new Item("blended sardines","0019",100.0,"gallons");
        Item i6 = new Item("thing1","9891",1000.0,"fl.oz");
        Item i7 = new Item("thing2","7777",100.0,"kgs");
        Item i8 = new Item("thing3","1234",100.0,"liters");
        Item i9 = new Item("thing4","9999",100.0,"gallons");

        MockDB testDB = new MockDB();

        testDB.addItem(i1);
        testDB.addItem(i2);
        testDB.addItem(i3);
        testDB.addItem(i4);
        testDB.addItem(i5);
        testDB.addItem(i6);
        testDB.addItem(i7);
        testDB.addItem(i8);
        testDB.addItem(i9);

        return testDB;
    }
}

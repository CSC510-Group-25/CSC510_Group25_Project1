//package com.qtycalc;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class OrderTrackerTests {
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

    @Test
    void createOrder() {

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
    void getOrders() {
        OrderTracker ot = new OrderTracker();
        ot.createOrder(constructRecipe1(),10,10);
        ot.createOrder(constructRecipe2(), 5, 0);

        HashMap<String,Order> orders = ot.getOrders();

        assertEquals("1111",orders.get("1111").getRecipe().getRecipeID());
        assertEquals("2222",orders.get("2222").getRecipe().getRecipeID());
    }

    @Test
    void getKeys() {
        OrderTracker ot = new OrderTracker();
        ot.createOrder(constructRecipe1(),10,10);
        ot.createOrder(constructRecipe2(), 5, 0);

        ArrayList<String> keys = ot.getKeys();

        assertTrue(keys.contains("1111"));
        assertTrue(keys.contains("2222"));
    }

    @Test
    void getOrdersArray() {
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
    void OrderTracker_toString() {
        OrderTracker ot = new OrderTracker();
        ot.createOrder(constructRecipe1(),10,10);
        ot.createOrder(constructRecipe2(), 5, 0);
        String thing = "recipe1: ID 1111\nNumber of successful orders: 10\nNumber of failed orders: 10\n" +
                "recipe2: ID 2222\nNumber of successful orders: 5\nNumber of failed orders: 0";

        assertEquals(thing,ot.toString());
    }

    ////////private helper methods

    private Order constructOrder1(){
       return new Order(constructRecipe1(),10,10);
    }

    private Order constructOrder2(){
        return new Order(constructRecipe2(),5,0);
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

    private Recipe constructRecipe2(){

        ArrayList<Ingredient> thing1 = new ArrayList<>();

        Ingredient t1 = new Ingredient("thing1", "1234", 1, "lbs");
        Ingredient t2 = new Ingredient("thing2", "5678", 2, "kgs");
        Ingredient t3 = new Ingredient("thing3", "1098", 3, "mL");

        thing1.add(t1);
        thing1.add(t2);
        thing1.add(t3);

        return new Recipe("recipe2","2222",thing1);
    }
}
